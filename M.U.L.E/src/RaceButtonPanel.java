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
	private RaceType race;
	
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
		
		bonzoid = new JButton("bonzoid");
		flapper = new JButton("flapper");
		humanoid = new JButton("humanoid");
		buzzite = new JButton("buzzite");
		ugaite = new JButton("ugaite");
		
		ButtonListener listener = new ButtonListener();
		bonzoid.addActionListener(listener);
		flapper.addActionListener(listener);
		humanoid.addActionListener(listener);
		buzzite.addActionListener(listener);
		ugaite.addActionListener(listener);
		
		add(iconLabel);
		add(bonzoid);
		add(flapper);
		add(humanoid);
		add(buzzite);
		add(ugaite);
	}
	
	/**
	 * Gets the race type selected by the user
	 * 
	 * @return the race
	 * 
	 */
	/*public RaceType getRace()
	{
		return race;
	}*/
	
	//temporary method
	public int getRace()
	{
		return 0;
	}
	

/**
 * Private inner class defines what happens upon button click.
 */	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			Object source = event.getSource();
			if (source == bonzoid)
					{
				       iconLabel.setIcon(new ImageIcon("bonzoid.png","race")); 
					}
			else if (source == flapper) 
				{
				    iconLabel.setIcon(new ImageIcon("flapper.png","race"));
				}
			else if (source == humanoid)
				{
				iconLabel.setIcon(new ImageIcon("humanoid.png","race"));
				}
			else if (source == buzzite)
			{
				iconLabel.setIcon(new ImageIcon("buzzite.png","race"));
			}
			else if (source == ugaite)
			{
				iconLabel.setIcon(new ImageIcon("ugaite.png","race"));
			}

		}
	}
}
