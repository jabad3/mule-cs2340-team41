package RandomEvents;

import Models.FailedTransactionException;
import Models.Player;
import Models.Resource;

/**
* CatBugsEvent is a subclass of RandomEvent that implements an action method
* that performs this event's action
* 
* Event description:
* FLYING CAT-BUGS ATE THE ROOF OFF YOUR HOUSE. REPAIRS COST $4*m.
* 
* @author kevin
*
*/
public class CatBugsEvent extends RandomEvent {
    
    /** The multiplier for the repair cost. */
    private int m;
    
    /** Value of the repair cost from the last eventAction */
    private int repairCost;
    
    /**
     * Create a CatBugsEvent.
     * 
     * @param player The player this event affects
     * @param m Multiplier for the repair cost associated with this event.
     */
    public CatBugsEvent(Player player, int m) {
        super(player);
        this.m = m;
    }

	@Override
	public void execute() {
	    repairCost = 4 * m;  //placeholder! is really 4 * m_value!
		try {
			player.removeResource(Resource.MONEY, repairCost);
		} catch (FailedTransactionException e) {
			player.deductAll(Resource.MONEY);
			e.printStackTrace();
		}
	}

    @Override
    public String getResultMessage() {
        String result = "Flying cat-bugs ate the roof off your house!  Repairs cost $" + repairCost;
        return result;
    }

}
