package Models;

/**
* MessEvent is a subclass of RandomEvent that implements an action method
* that performs this event's action
* 
* @author kevin
*
*/
public class MessEvent extends RandomEvent {

	@Override
	public void eventAction() {
		try {
			player.removeResource(Resource.MONEY, 6); //placeholder! is really 6 * m-value
		} catch (FailedTransactionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
