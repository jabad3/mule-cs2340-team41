package Stages;
import Models.Player;
import RandomEvents.RandomEvent;
import RandomEvents.RandomEventFactory;
import java.awt.Component;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JOptionPane;

/**
 * Service that performs a RandomEvent on a player.  
 * Instantiation of RandomEvents is done by RandomEventFactory
 * 
 * @author kevin
 *
 */
public class RandomEventService {
	
	/** HashMap for mValues, which are multipliers based on the current turn */
	private HashMap<Integer, Integer> mValues;
	
	/**
	 * constructor for RandomEventService, builds mValues map 
	 * 
	 */
	public RandomEventService() {
		this.buildmValues();
	}
	
	/** 
	 * Build a Map with mvalues (values) for each round (keys). 
	 */
	private void buildmValues() {
		mValues = new HashMap<>();
		mValues.put(1, 25);
		mValues.put(2, 25);
		mValues.put(3, 25);
		mValues.put(4, 50);
		mValues.put(5, 50);
		mValues.put(6, 50);
		mValues.put(7, 50);
		mValues.put(8, 75);
		mValues.put(9, 75);
		mValues.put(10, 75);
		mValues.put(11, 75);
		mValues.put(12, 100);
	}
	
	/**
	 * Starts RandomEventService for given player, will 
	 * determine if an event is going to occur and then execute appropriate
	 * type of event, displaying the result in the specified Component.
	 * 
	 * @param player the player to run service on
	 * @param currentRound the current round of the game
	 * @param isInLastPlace whether the player is in last place or not
	 * @param component the parent Component to use to draw showMessageDialog
	 */
	public void startService(Player player, int currentRound, boolean isInLastPlace, Component component) { 
	    if (!eventIsHappening()) {
	        return;  // do nothing if no event should occur
	    }
	    

	    RandomEventFactory factory = new RandomEventFactory();
	    int m = mValues.get(currentRound);
		
	    RandomEvent event;
	    if (isInLastPlace) {
	        event = factory.buildGoodEvent(player, m);
	    } else {
	        event = factory.buildFromAllEvent(player, m);
	    }
	    
	    event.execute();
	    JOptionPane.showMessageDialog(component, event.getResultMessage());
	}
    
    /**
     * determines if an event is going to occur.  There is a 27%
     * chance of an event occuring.
     * 
     * @return true if an event is to occur, false otherwise
     */
    private boolean eventIsHappening() {
        Random rand = new Random();
        return rand.nextInt(101) <= 27;
    }
	
}
