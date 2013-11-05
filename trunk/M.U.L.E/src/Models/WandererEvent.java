package Models;

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

	@Override
	public void eventAction(Player player, int m) {
		player.addResource(Resource.ORE, 2);
	}

    @Override
    public String getResultMessage() {
        String result = "A wandering Tech student repaid your hospitality by leaving 2 bars of ore.";
        return result;
    }

}
