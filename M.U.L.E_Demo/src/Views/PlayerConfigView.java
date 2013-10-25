package Views;

import javax.swing.*;

import Models.RaceType;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
/**
 * This class allows for configuration of the player by allowing the user to
 * choose a race, name, and color.
 * 
 * @author Erica Pramer
 * @version 1
 */
public class PlayerConfigView extends JPanel{
	/** Displays which player is being configured. */
    private JLabel curPlayerLabel;
    
    /** Container that allows the user to enter a name. */
	private NamePanel name;
	
	/** Container that allows the user to choose a race. */
	private RaceButtonPanel race;
	
	/** Container that allows the user to choose a color. */
	private ColorPanel color;
	
	/** Button allowing the user to finish configuring the current player. */
	private JButton next;
	

	
	
	/**
	 * Create a PlayerConfigView object.
	 * 
	 * All required inner-panels are created and added automatically.
	 */
	public PlayerConfigView()
	{
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setPreferredSize(new Dimension(500,300));
		
		curPlayerLabel = new JLabel();
		name = new NamePanel();
		race = new RaceButtonPanel();
		next = new JButton("next");
		color = new ColorPanel();
		
		next.setMaximumSize( next.getPreferredSize() );
		
		add(curPlayerLabel);
		add(name);
		add(race);
		add(color);
		add(next);
	}

	/**
	 * Get the text that was entered for the player's name.
	 * 
	 * @return The chosen name of the current player.
	 */
	public String getText()
	{
		return name.getName();
	}
	
	/**
	 * Get the color that was chosen for the current player.
	 * 
	 * @return The current player's chosen color.  Null if no color selected.
	 */
	public Color getColor()
	{
		return color.getChosenColor();
	}
	
	/**
	 * Returns the RaceType selected by the user.
	 * 
	 * @return The user's selected RaceType.  null if no race was chosen.
	 */
	public RaceType getRace()
	{
		return race.getChosenRace();
	}
	
	/**
	 * Sets the number of the current player to be configured.
	 * 
	 * @param num Number between 1 and total number of players (inclusive)
	 * representing the number of the current player to be configured
	 */
	public void setPlayerNum(int num)
	{
		curPlayerLabel.setText("Player " + num);
	}
	
	/**
	 * Sets disabled colors in the View to manually prevent the same color
	 * from being chosen twice.
	 * 
	 * @param disabledColors List of colors to disable in the view
	 */
	public void setDisabledColorOptions(List<Color> disabledColors) {
	    color.setDisabledColorOptions(disabledColors);
	}
	
	/**
	 * Adds an action listener to respond to the action event fired when the
	 * user presses the next button to finish configuring the current player.
	 * 
	 * @param finishedListener ActionListener to respond to the next button
	 * being pressed
	 */
    public void addFinishedListener(ActionListener finishedListener) {
        next.addActionListener(finishedListener);
    }
}
