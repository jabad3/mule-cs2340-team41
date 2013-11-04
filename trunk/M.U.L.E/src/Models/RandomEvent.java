package Models;

/**
 * superclass for RandomEvents, events that perform an action on a given player
 * 
 * @author kevin
 *
 */
public abstract class RandomEvent {
	Player player;
	
	public abstract void eventAction(); 
}
