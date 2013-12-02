package RandomEvents;

import Models.Player;
import Models.Resource;

/**
* MuseumEvent is a subclass of RandomEvent that implements an action method
* that performs this event's action
* 
* Event description:
* THE MUSEUM BOUGHT YOUR ANTIQUE PERSONAL COMPUTER FOR $ 8*m.
* 
* @author kevin
*
*/
public class MuseumEvent extends RandomEvent {
    
    /** The multiplier associated with this event. */
    private int m;
    
    /** The computer price determined by last execution of the event. */
    private int computerPrice;
    
    /**
     * Create a MuseumEvent.
     * 
     * @param player The player this random event affects
     * @param m The price multiplier for this event
     */
    public MuseumEvent(Player player, int m) {
        super(player);
        this.m = m;
    }

	@Override
	public void execute() {
	    computerPrice = 8 * m;
		player.addResource(Resource.MONEY, computerPrice);
	}

    @Override
    public String getResultMessage() {
        String result = "The museum bought your antique personal computer for $"
                        + computerPrice + "!";
        return result;
    }

}
