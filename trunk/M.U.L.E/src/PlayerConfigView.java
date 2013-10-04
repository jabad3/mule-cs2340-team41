import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
/**
 * This class allows for configuration of the player including race, name, and color
 * @author Erica Pramer
 * @version 1
 */

public class PlayerConfigView extends JPanel{
	
	private PlayerConfig controller;
	private NamePanel name;
	private RaceButtonPanel race;
	private ColorPanel color;
	private JButton next;
	
	
	/**
	 * Instantiates all instance data, sets layout, adds action listener to the buttons
	 * 
	 * @param L
	 *            action listener passed in by the DeckPanel to facilitate
	 *            movement between jpanels using buttons
	 */
	public PlayerConfigView()//ActionListener L, PlayerConfig controller)
	{
		this.controller = controller;

	setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	setPreferredSize(new Dimension(1000,400));
	
	name = new NamePanel();
	race = new RaceButtonPanel();
	next = new JButton("next");
	color = new ColorPanel();
	
	next.setMaximumSize( next.getPreferredSize() );
	
	add(name);
	add(race);
	add(color);
	add(next);
	
	//next.addActionListener(L);
	
	}
	
	/**
	 * This method returns the next button on the player configuration panel
	 * 
	 * @return a next button to go to the next jpanel
	 */
	public JButton getNextButton()
	{
		return next;
	}
	
//	public static void main(String[] args){
//		JFrame temp = new JFrame("temp");
//		PlayerConfigPanel temp2 = new PlayerConfigPanel();
//		temp.add(temp2);
//		temp.pack();
//		temp.setVisible(true);
//		System.out.println("finished");
//	}
//	
}