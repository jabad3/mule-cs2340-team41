package Models;

/**
* WandererEvent is a subclass of RandomEvent that implements an action method
* that performs this event's action
* 
* @author kevin
*
*/
public class WandererEvent extends RandomEvent {

	@Override
	public void eventAction() {
		player.addResource(Resource.ORE, 2);
	}

}
