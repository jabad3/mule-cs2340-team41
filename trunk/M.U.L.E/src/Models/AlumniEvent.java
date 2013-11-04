package Models;

/**
 * AlumniEvent is a subclass of RandomEvent that implements an action method
 * that performs this event's action
 * 
 * @author kevin
 *
 */
public class AlumniEvent extends RandomEvent {
	
	@Override
	public void eventAction() {
		player.addResource(Resource.FOOD, 3);
		player.addResource(Resource.ENERGY, 2);
	}

}
