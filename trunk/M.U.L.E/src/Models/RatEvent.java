package Models;

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

    /** The value that the hide was sold for after the last eventAction() call. */
    int lastHidePrice;
    
	@Override
	public void eventAction(Player player, int m) {
	    int hidePrice = 2 * m;
		player.addResource(Resource.MONEY, hidePrice); //placeholder!, is really 2 * m-value!
		lastHidePrice = hidePrice;
	}

    @Override
    public String getResultMessage() {
        String result = "You found a dead moose rat and sold the hide for $" 
                        + lastHidePrice;
        return result;
    }

}
