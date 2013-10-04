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
		return color.getBackground();
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
				color.setBackground(Color.red);
				color.setForeground(Color.black);
			}
			else if(source == blue)
			{
				color.setBackground(Color.blue);
				color.setForeground(Color.white);
			}
			else if(source == green)
			{
				color.setBackground(Color.green);
				color.setForeground(Color.black);
			}
			else if(source == yellow)
			{
				color.setBackground(Color.yellow);
				color.setForeground(Color.black);
			}
		}
	}
}
