package Views;

import javax.swing.*;

import Models.RaceType;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Map;
/**
 * This class allows for configuration of the player including race, name, and color
 * @author Erica Pramer
 * @version 1
 */

public class PlayerConfigView extends JPanel{
	private JLabel curPlayerLabel;
	private NamePanel name;
	private RaceButtonPanel race;
	private ColorPanel color;
	private JButton next;
	

	
	
	/**
	 * Instantiates all instance data, sets layout
	 */
	public PlayerConfigView()//ActionListener L, PlayerConfig controller)
	{
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setPreferredSize(new Dimension(1000,400));
		
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

	public String getText()
	{
		return name.getName();
	}
	
	public Color getColor()
	{
		return color.getChosenColor();
	}
	
	/**
	 * Returns the RaceType selected by the user.
	 * @return The user's selected RaceType.  null if no race was chosen.
	 */
	public RaceType getRace()
	{
		return race.getChosenRace();
	}
	
	public void setPlayerNum(int num)
	{
		curPlayerLabel.setText("Player " + num);
	}
	
	public void setDisabledColorOptions(List<Color> disabledColors) {
	    color.setDisabledColorOptions(disabledColors);
	}
	
    public void addFinishedListener(ActionListener finishedListener) {
        next.addActionListener(finishedListener);
    }
}
