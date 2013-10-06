import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.*;
/**
 * This class allows the user to choose their color
 * @author Erica Pramer
 * @version 1
 */
//
//testing
public class ColorPanel extends JPanel{
	
    List<JButton> buttonList = new ArrayList<>();
	JLabel colorLabel;
	String chosenColorName;
	Map<String, Color> nameColorMap;
	
	/**
	 * Constructor, adds actionlisteners to
	 * the buttons and buttons to the JPanel
	 */
	public ColorPanel(){
		
    	colorLabel = new JLabel("Player Color");
    	colorLabel.setOpaque(true);
    	
    	add(colorLabel);
	
	}
	
	/**
	 * Gets the color selected by the user in the form of a String
	 * 
	 * @return the color name
	 * 
	 */
	public Color getColor()
	{
		return nameColorMap.get(chosenColorName);
	}
	
	/**
	 * This method creates buttons for all of the possible colors.
	 * These buttons are added to the buttonsList field.
	 * These buttons are then added to the ColorPanel to be displayed.
	 * 
	 * @param nameColorMap A mapping of color names to Color objects for
	 * all colors that need to be displayed
	 */
	public void setAllColorOptions(Map<String, Color> nameColorMap) {
	    this.nameColorMap = nameColorMap;
	    Set<String> colorNames = nameColorMap.keySet();
	    for (String colorName: colorNames) {
	        JButton colorButton = new JButton(colorName);
	        colorButton.addActionListener(new ColorButtonListener(colorName));
	        buttonList.add(colorButton);
	        this.add(colorButton);
	    }
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
	 * Private inner class defines what happens upon button click.
	 */	
	private class ColorButtonListener implements ActionListener
	{
		String colorName;
		
		public ColorButtonListener(String colorName) {
		    this.colorName = colorName;
		}
		
		/**
		 * Sets chosen color to the color corresponding to this button
		 */
		public void actionPerformed(ActionEvent event) {
		    chosenColorName = colorName;
		    colorLabel.setForeground(nameColorMap.get(colorName));
		}
	}
}
