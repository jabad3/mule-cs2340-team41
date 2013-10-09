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
	private JLabel iconLabel;
	private RaceType chosenRace;
	
/**
 * Constructor, sets layout and size of JPanel, adds actionlisteners to
 * the buttons and buttons to the JPanel
 */
	public RaceButtonPanel()
	{
		setLayout(new GridLayout(1,9));
		setPreferredSize(new Dimension(1000,100));
		
		/* add icon to panel first so it appears at the far left */
		iconLabel = new JLabel();
		this.add(iconLabel);
		
		addRaceButtons();
	}
	
	/** 
     * Create buttons corresponding to all possible races in RaceType
     * and add them to the panel
     */
	private void addRaceButtons() {
        RaceType[] raceChoices = RaceType.values();
        for (RaceType race: raceChoices) {
            String raceName = race.toString();
            JButton raceButton = new JButton(raceName);
            raceButton.addActionListener(new RaceButtonListener(race));
            this.add(raceButton);
        }
	}
	
	/**
	 * Gets the race type selected by the user
	 * 
	 * @return the race
	 * 
	 */
	public RaceType getChosenRace()
	{
		return chosenRace;
	}
	

    /**
     * Private inner class defines what happens upon button click.
     * Each race button should have a separate instance of this class
     * listening for an ActionEvent.
     */	
	private class RaceButtonListener implements ActionListener
	{
	    /**
	     * The race that is associated with the button that the
	     * RaceButtonListener is listening to.
	     */
	    RaceType raceOfButton;
	    
	    /**
	     * Creates a RaceButtonListener object.
	     * 
	     * @param race The race that should be chosen when the button
	     *             this listner is assigned to is pressed
	     */
		public RaceButtonListener(RaceType race) {
		    this.raceOfButton = race;
		}
	    
		/**
		 * When a race button is clicked, display the stock image associated
		 * with that particular race.
		 */
	    public void actionPerformed(ActionEvent event)
		{
			iconLabel.setIcon(raceOfButton.getStockImageIcon());
			chosenRace = raceOfButton;
		}
	}
}
