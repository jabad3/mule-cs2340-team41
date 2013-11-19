package RandomEvents;

import Models.Player;

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
     * @param player
     *            The player to be affected by the random event
     * @param m
     *            A multiplier used by events
     */
    public abstract void eventAction(Player player, int m);

    /**
     * Returns a string-representation of the result of the last eventAction().
     * 
     * @return The results of the last eventAction()
     */
    public abstract String getResultMessage();
}
