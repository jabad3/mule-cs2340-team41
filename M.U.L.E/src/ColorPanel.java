import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
/**
 * This class allows the user to choose their color
 * @author Erica Pramer
 * @version 1
 */
//
//testing
public class ColorPanel extends JPanel{
	
	//JButton red,green,blue,yellow;
    List<JButton> buttonList = new ArrayList<>();
	JLabel colorLabel;
	Color chosenColor;
	
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
	 * Gets the color selected by the user
	 * 
	 * @return the color
	 * 
	 */
	public Color getColor()
	{
		return chosenColor;
	}
	
	/**
	 * This method creates buttons for all of the possible colors.
	 * These buttons are added to the buttonsList field.
	 * These buttons are then added to the ColorPanel to be displayed.
	 * 
	 * @param colorNames An array of color names for all possible colors in the game
	 */
	public void setAllColorOptions(String[] colorNames) {
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
	 * @param colorNames An array of color names for all disabled colors
	 */
	public void setDisabledColorOptions(List<Color> colors) {
	    for (Color color: colors) {
	        for (JButton button: buttonList) {
	            String buttonColorString = button.getText();
	            Color buttonColor = Color.decode(buttonColorString);
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
		    chosenColor = Color.decode(colorName);
		    colorLabel.setForeground(chosenColor);
		}
	}
}
