package RandomEvents;

import Models.Player;
import Models.Resource;

/**
* WandererEvent is a subclass of RandomEvent that implements an action method
* that performs this event's action
* 
* Event description:
* A WANDERING TECH STUDENT REPAID YOUR HOSPITALITY BY LEAVING TWO BARS OF ORE.
* 
* @author kevin
*
*/
public class WandererEvent extends RandomEvent {

    /**
     * Create a WandererEvent.
     * 
     * @param player The player this event affects
     */
    public WandererEvent(Player player) {
        super(player);
    }
	@Override
	public void execute() {
		player.addResource(Resource.ORE, 2);
	}

    @Override
    public String getResultMessage() {
        String result = "A wandering Tech student repaid your hospitality by leaving 2 bars of ore.";
        return result;
    }

}
