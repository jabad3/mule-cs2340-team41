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
	JLabel color;
	Color chosenColor;
	
	/**
	 * Constructor, adds actionlisteners to
	 * the buttons and buttons to the JPanel
	 */
	public ColorPanel(){
		
    	/*
    	red = new JButton("red");
    	green = new JButton("green");
    	yellow = new JButton("yellow");
    	blue = new JButton("blue");
    	*/
    	color = new JLabel("Player Color");
    	color.setOpaque(true);
    	
    	/*
    	ButtonListener listener = new ButtonListener();
    	red.addActionListener(listener);
    	green.addActionListener(listener);
    	blue.addActionListener(listener);
    	yellow.addActionListener(listener);
    	*/
    	
    	add(color);
    	/*
    	add(red);
    	add(green);
    	add(yellow);
    	add(blue);
    	*/
	
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
	    System.out.println("I was called");
	    for (String colorName: colorNames) {
	        JButton colorButton = new JButton(colorName);
	        colorButton.addActionListener(new ColorButtonListener(colorName));
	        buttonList.add(colorButton);
	        this.add(colorButton);
	        System.out.println("Added a button");
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
		    color.setBackground(chosenColor);
		}
	    /* old actionperformed
	    public void actionPerformed(ActionEvent event)
		{
			Object source = event.getSource();
			if(source == null)
			{
				
			}
			else if (source == red)
			{
				chosenColor = Color.red;
				color.setBackground(chosenColor);
				color.setForeground(Color.black);
			}
			else if(source == blue)
			{
				chosenColor = Color.blue;
				color.setBackground(chosenColor);
				color.setForeground(Color.white);
			}
			else if(source == green)
			{
				chosenColor = Color.green;
				color.setBackground(chosenColor);
				color.setForeground(Color.black);
			}
			else if(source == yellow)
			{
				chosenColor = Color.yellow;
				color.setBackground(chosenColor);
				color.setForeground(Color.black);
			}
		}*/
	}
}
