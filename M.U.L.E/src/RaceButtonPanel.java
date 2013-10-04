import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
/**
 * This class lets the player choose their race
 * @author Erica Pramer
 * @version 1
 */
public class RaceButtonPanel extends JPanel
{
	private JButton buzzite,ugaite,humanoid,flapper,bonzoid;
	private JLabel iconLabel;
	private int race = -1;
	
/**
 * Constructor, sets layout and size of JPanel, adds actionlisteners to
 * the buttons and buttons to the JPanel
 */
	public RaceButtonPanel()
	{
		setLayout(new GridLayout(1,9));
		setPreferredSize(new Dimension(1000,100));
		
		//race = new RaceType();
		iconLabel = new JLabel();
		
		humanoid = new JButton("humanoid");
		flapper = new JButton("flapper");
		bonzoid = new JButton("bonzoid");
		ugaite = new JButton("ugaite");
		buzzite = new JButton("buzzite");
		
		ButtonListener listener = new ButtonListener();
		humanoid.addActionListener(listener);
		flapper.addActionListener(listener);
		bonzoid.addActionListener(listener);
		ugaite.addActionListener(listener);
		buzzite.addActionListener(listener);
		
		add(iconLabel);
		add(humanoid);
		add(flapper);
		add(bonzoid);
		add(ugaite);
		add(buzzite);
	}
	
	/**
	 * Gets the race type selected by the user
	 * 
	 * @return the race
	 * 
	 */
	public int getRace()
	{
		return race;
	}
	

/**
 * Private inner class defines what happens upon button click.
 */	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			Object source = event.getSource();
			if (source == humanoid)
					{
				       iconLabel.setIcon(new ImageIcon("humanoid.png","race")); 
				       race = 0;
					}
			else if (source == flapper) 
				{
				    iconLabel.setIcon(new ImageIcon("flapper.png","race"));
				    race = 1;
				}
			else if (source == bonzoid)
				{
				iconLabel.setIcon(new ImageIcon("bonzoid.png","race"));
				race = 2;
				}
			else if (source == ugaite)
			{
				iconLabel.setIcon(new ImageIcon("ugaite.png","race"));
				race = 3;
			}
			else if (source == buzzite)
			{
				iconLabel.setIcon(new ImageIcon("buzzite.png","race"));
				race = 4;
			}

		}
	}
}
