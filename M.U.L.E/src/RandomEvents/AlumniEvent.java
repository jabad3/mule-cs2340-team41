package RandomEvents;

import Models.Player;
import Models.Resource;

/**
 * AlumniEvent is a subclass of RandomEvent that implements an action method
 * that performs this event's action
 * 
 * Event description:
 * YOU JUST RECEIVED A PACKAGE FROM THE GT ALUMNI CONTAINING 3 FOOD AND 2 ENERGY UNITS.
 * 
 * @author kevin
 *
 */
public class AlumniEvent extends RandomEvent {
    
    /**
     * Create an AlumniEvent.
     * 
     * @param player The player that will have the action take place
     */
    public AlumniEvent(Player player) {
        super(player);
    }
	
	@Override
	public void execute() {
		player.addResource(Resource.FOOD, 3);
		player.addResource(Resource.ENERGY, 2);
	}

    @Override
    public String getResultMessage() {
        String result = "You just received a package from the GT Alumni"
                        + " containing 3 food and 2 energy units!";
        return result;
    }

}
