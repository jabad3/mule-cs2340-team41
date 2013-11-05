package Models;

/**
 * superclass for RandomEvents, events that perform an action on a given player
 * 
 * @author kevin
 *
 */
public abstract class RandomEvent {
	
    /**
     * Performs the event action on the given Player.
     * 
     * @param player The player to be affected by the random event
     */
	public abstract void eventAction(Player player);
	
	/**
	 * Returns a string-representation of the result of the last eventAction().
	 * 
	 * @return The results of the last eventAction()
	 */
	public abstract String getResultMessage();
}
