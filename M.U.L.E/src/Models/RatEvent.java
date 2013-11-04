package Models;

/**
* RatEvent is a subclass of RandomEvent that implements an action method
* that performs this event's action
* 
* @author kevin
*
*/
public class RatEvent extends RandomEvent {

	@Override
	public void eventAction() {
		player.addResource(Resource.MONEY, 2); //placeholder!, is really 2 * m-value!

	}

}
