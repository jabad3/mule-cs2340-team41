package RandomEvents;

import Models.FailedTransactionException;
import Models.Player;
import Models.Resource;

/**
* UGAEvent is a subclass of RandomEvent that implements an action method
* that performs this event's action
* 
* Event description:
* MISCHIEVOUS UGA STUDENTS BROKE INTO YOUR STORAGE SHED AND STOLE HALF YOUR FOOD.
* 
* @author kevin
*
*/
public class UGAEvent extends RandomEvent {

	@Override
	public void eventAction(Player player, int m) {
		int curPlayerFood = player.getFood();
		try {
			player.removeResource(Resource.FOOD, curPlayerFood / 2); //get approx. half with integer division, then remove from player
		} catch (FailedTransactionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    @Override
    public String getResultMessage() {
        String result = "Mischievous U[sic]GA students broke into your storage shed and stole half your food.";
        return result;
    }

}
