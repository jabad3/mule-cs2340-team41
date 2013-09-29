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
	private JButton spheroid, mechtron,leggie,packer,humanoid,flapper,bonzoid,gollumer;
	private JLabel iconLabel;
	
/**
 * Constructor, sets layout and size of JPanel, adds actionlisteners to
 * the buttons and buttons to the JPanel
 */
	public RaceButtonPanel()
	{
		setLayout(new GridLayout(1,9));
		setPreferredSize(new Dimension(1000,100));
		
		iconLabel = new JLabel();
		
		bonzoid = new JButton("bonzoid");
		flapper = new JButton("flapper");
		gollumer = new JButton("gollumer");
		humanoid = new JButton("humanoid");
		leggie = new JButton("leggie");
		mechtron = new JButton("mechtron");
		packer = new JButton("packer");
		spheroid = new JButton("spheroid");
		
		ButtonListener listener = new ButtonListener();
		bonzoid.addActionListener(listener);
		flapper.addActionListener(listener);
		gollumer.addActionListener(listener);
		humanoid.addActionListener(listener);
		leggie.addActionListener(listener);
		mechtron.addActionListener(listener);
		packer.addActionListener(listener);
		spheroid.addActionListener(listener);
		
		add(iconLabel);
		add(bonzoid);
		add(flapper);
		add(gollumer);
		add(humanoid);
		add(leggie);
		add(mechtron);
		add(packer);
		add(spheroid);
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
			else if (source == gollumer) 
				{
				iconLabel.setIcon(new ImageIcon("gollumer.png","race"));
				}
			else if (source == humanoid)
				{
				iconLabel.setIcon(new ImageIcon("humanoid.png","race"));
				}
			else if (source == leggie)
				{
				iconLabel.setIcon(new ImageIcon("leggie.png","race"));
				}
			else if (source == mechtron)
			{
				iconLabel.setIcon(new ImageIcon("mechtron.png","race"));
			}
			else if (source == packer)
			{
				iconLabel.setIcon(new ImageIcon("packer.png","race"));
			}
			else if (source == spheroid)
			{
				iconLabel.setIcon(new ImageIcon("spheroid.png","race"));
			}

		}
	}
}
