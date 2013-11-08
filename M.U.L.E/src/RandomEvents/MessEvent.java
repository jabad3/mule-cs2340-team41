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
    
    /** Value of the clean-up cost from the last eventAction */
    private int lastCleanupCost;

	@Override
	public void eventAction(Player player, int m) {
	    int cleanupCost = 6 * m;
		try {
			player.removeResource(Resource.MONEY, cleanupCost); //placeholder! is really 6 * m-value
		} catch (FailedTransactionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lastCleanupCost = cleanupCost;
	}

    @Override
    public String getResultMessage() {
        String result = "Your space gypsy in-laws made a mess of the town!  "
                        + "It cost you $" + lastCleanupCost + " to clean it up.";
        return result;
    }

}
