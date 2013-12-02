package RandomEvents;

import Models.Player;
import Models.Resource;

/**
* RatEvent is a subclass of RandomEvent that implements an action method
* that performs this event's action
* 
* Event description:
* YOU FOUND A DEAD MOOSE RAT AND SOLD THE HIDE FOR $2*m.
* 
* @author kevin
*
*/
public class RatEvent extends RandomEvent {

    /** Price multiplier for this event. */
    private int m;
    
    /** The value that the hide was sold for after the last execute() call. */
    private int hidePrice;
    
    public RatEvent(Player player, int m) {
        super(player);
        this.m = m;
    }
    
	@Override
	public void execute() {
	    hidePrice = 2 * m;
		player.addResource(Resource.MONEY, hidePrice);
	}

    @Override
    public String getResultMessage() {
        String result = "You found a dead moose rat and sold the hide for $" 
                        + hidePrice;
        return result;
    }

}
