package RandomEvents;

import Models.FailedTransactionException;
import Models.Player;
import Models.Resource;

/**
* MessEvent is a subclass of RandomEvent that implements an action method
* that performs this event's action
* 
* Event description:
* YOUR SPACE GYPSY INLAWS MADE A MESS OF THE TOWN. IT COST YOU $6*m TO CLEAN IT UP.
* 
* @author kevin
*
*/
public class MessEvent extends RandomEvent {
    
    /** The multiplier associated with clean-up cost of this event. */
    private int m;
    
    /** Value of the clean-up cost from the last eventAction */
    private int cleanupCost;
    
    /**
     * Create a MessEvent.
     * 
     * @param player The player this event affects
     * @param m The multiplier associated with clean-up cost of the event.
     */
    public MessEvent(Player player, int m) {
        super(player);
        this.m = m;
    }

	@Override
	public void execute() {
	    cleanupCost = 6 * m;
		try {
			player.removeResource(Resource.MONEY, cleanupCost);
		} catch (FailedTransactionException e) {
			player.deductAll(Resource.MONEY);  // take it all if they don't have enough
		}
	}

    @Override
    public String getResultMessage() {
        String result = "Your space gypsy in-laws made a mess of the town!  "
                        + "It cost you $" + cleanupCost + " to clean it up.";
        return result;
    }
}