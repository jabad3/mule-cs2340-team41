import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
/**
 * This class allows the user to choose their color
 * @author Erica Pramer
 * @version 1
 */
//
//testing
public class ColorPanel extends JPanel{
	
	JButton red,green,blue,yellow;
	JLabel color;
	Color chosenColor;
	
	/**
	 * Constructor, adds actionlisteners to
	 * the buttons and buttons to the JPanel
	 */
	public ColorPanel(){
		
	red = new JButton("red");
	green = new JButton("green");
	yellow = new JButton("yellow");
	blue = new JButton("blue");
	
	color = new JLabel("Player Color");
	color.setOpaque(true);
	
	ButtonListener listener = new ButtonListener();
	red.addActionListener(listener);
	green.addActionListener(listener);
	blue.addActionListener(listener);
	yellow.addActionListener(listener);
	
	add(color);
	add(red);
	add(green);
	add(yellow);
	add(blue);
	
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
	 * Private inner class defines what happens upon button click.
	 */	
	private class ButtonListener implements ActionListener
	{
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
		}
	}
}
