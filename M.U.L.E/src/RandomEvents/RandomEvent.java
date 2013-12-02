package RandomEvents;

import Models.Player;

/**
 * superclass for RandomEvents, events that perform an action on a given player
 * 
 * @author kevin
 *
 */
public abstract class RandomEvent {
	
    /** The player that the RandomEvent affects. */
    protected Player player;
    
    /**
     * Create a RandomEvent.
     * 
     * @param player The player that this random event will affect
     */
    public RandomEvent(Player player) {
        this.player = player;
    }
    
    /**
     * Executes the action associated with this RandomEvent.
     */
	public abstract void execute();
	
	/**
	 * Returns a string-representation of the result of the last eventAction().
	 * 
	 * @return The results of the last eventAction()
	 */
	public abstract String getResultMessage();
}
