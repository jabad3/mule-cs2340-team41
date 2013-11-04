package Models;

/**
* MuseumEvent is a subclass of RandomEvent that implements an action method
* that performs this event's action
* 
* @author kevin
*
*/
public class MuseumEvent extends RandomEvent {

	@Override
	public void eventAction() {
		player.addResource(Resource.MONEY, 4); //placeholder! m-value needs to be implemented!
	}

}
