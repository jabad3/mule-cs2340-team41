package Models;

/**
* UGAEvent is a subclass of RandomEvent that implements an action method
* that performs this event's action
* 
* @author kevin
*
*/
public class UGAEvent extends RandomEvent {

	@Override
	public void eventAction() {
		int curPlayerFood = player.getFood();
		try {
			player.removeResource(Resource.FOOD, curPlayerFood / 2); //get approx. half with integer division, then remove from player
		} catch (FailedTransactionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
