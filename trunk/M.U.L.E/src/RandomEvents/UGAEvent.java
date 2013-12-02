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

    /**
     * Create a new UGAEvent.
     * 
     * @param player The player that this event affects.
     */
    public UGAEvent(Player player) {
        super(player);
    }
    
	@Override
	public void execute() {
		int curPlayerFood = player.getFood();
		try {
			player.removeResource(Resource.FOOD, curPlayerFood / 2);
		} catch (FailedTransactionException e) {
			player.deductAll(Resource.FOOD);  // take all if they don't have enough
		}
	}

    @Override
    public String getResultMessage() {
        String result = "Mischievous U[sic]GA students broke into your storage shed and stole half your food.";
        return result;
    }

}
