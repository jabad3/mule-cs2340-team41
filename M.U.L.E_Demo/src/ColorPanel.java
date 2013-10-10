import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.*;
/**
 * This class allows the user to choose their color
 * @author Erica Pramer
 */
public class ColorPanel extends JPanel{
	
    /**
     * List of all buttons that will be displayed.
     * A list must be kept so that we can disable buttons that have been
     * added to the panel.
     */
    List<JButton> buttonList = new ArrayList<>();
    
    /**
     * Holds text that will change color depending on the user's selection.
     */
	JLabel colorLabel;
	
	/**
	 * The name of the color that was chosen by the user.
	 */
	String chosenColorName;
	
	/**
     * Contains a mapping of color names to Color objects for all
     * colors that the View will offer to the user.
     */
    private Map<String, Color> nameColorMap = new HashMap<>();
	
	/**
	 * Creates the ColorPanel, adding ActionListeners to
	 * the buttons and buttons to the JPanel
	 */
	public ColorPanel(){
		
    	colorLabel = new JLabel("Player Color");
    	colorLabel.setOpaque(true);
    	
    	add(colorLabel);
    	
        // name-color map needs to be setup
        nameColorMap.put("Red", Color.red);
        nameColorMap.put("Orange", Color.decode("0xFF8000"));  // darker orange
        nameColorMap.put("Green", Color.decode("0x01DF01"));  // darker green
        nameColorMap.put("Blue", Color.blue);
	
        // add buttons
        Set<String> colorNames = nameColorMap.keySet();
        for (String colorName: colorNames) {
            JButton colorButton = new JButton(colorName);
            colorButton.addActionListener(new ColorButtonListener(colorName));
            buttonList.add(colorButton);
            this.add(colorButton);
        }
	}
	
	/**
	 * Gets the color selected by the user in the form of a String
	 * 
	 * @return the Color object corresponding to the color's name
	 * 
	 */
	public Color getChosenColor()
	{
		return nameColorMap.get(chosenColorName);
	}
	
	/**
	 * This method disables any buttons corresponding to the given color names.
	 * Color choices are disabled so that no two players share the same color.
	 * 
	 * @param colors A list of color objects whose corresponding buttons
	 * need to be disabled
	 */
	public void setDisabledColorOptions(List<Color> colors) {
	    for (Color color: colors) {
	        for (JButton button: buttonList) {
	            String buttonColorString = button.getText();
	            Color buttonColor = nameColorMap.get(buttonColorString);
	            if (color.equals(buttonColor))
	                button.setEnabled(false);
	        }
	    }
	}
	
	/**
	 * Private inner class defines what happens when a color button is pressed.
	 * Each color button should have a unique ColorButtonListener listening
	 * for it to fire an ActionEvent.
	 */	
	private class ColorButtonListener implements ActionListener
	{
	    /** 
	     * The String representation of the color that is associated with
	     * the button that the ColorButtonListener will listen to
	     */
	    String colorName;
		
	    /**
	     * Creates a ColorButtonListener.
	     * 
	     * @param colorName The String representation of the color that will
	     * be selected when the ColorButtonListener responds to an ActionEvent
	     */
		public ColorButtonListener(String colorName) {
		    this.colorName = colorName;
		}
		
		/**
		 * Sets chosen color to the color corresponding to the pressed button
		 */
		public void actionPerformed(ActionEvent event) {
		    chosenColorName = colorName;
		    colorLabel.setForeground(nameColorMap.get(colorName));
		}
	}
}
