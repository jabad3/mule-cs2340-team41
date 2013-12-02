package RandomEvents;
import java.util.Random;

import Models.Player;

/**
 * RandomEventFactory object that builds RandomEvent objects at the request 
 * of a client.
 * 
 * @author kevin
 *
 */
public class RandomEventFactory {
	Random rand = new Random();
	
	/**
	 * builds a random event from the set of "good" events
	 * 
	 * @param player The player the random event will affect
	 * @param m The multiplier for the event
	 * @return the newly instantiated "good" event that is chosen
	 */
	public RandomEvent buildGoodEvent(Player player, int m) {
		switch (rand.nextInt(4)) {
			case(0) : return new AlumniEvent(player);
		
			case(1) : return new WandererEvent(player);
		
			case(2) : return new MuseumEvent(player, m);
		
			default : return new RatEvent(player, m);
		}
		
	}
	
	/**
	 * builds a random event from the set of all events
	 * 
	 * @param player The player the event will affect
	 * @param m The multiplier for the event
	 * @return the newly instantiated event that is chosen
	 */
	public RandomEvent buildFromAllEvent(Player player, int m) {
		switch (rand.nextInt(7)) {
		case(0) : return new AlumniEvent(player);
	
		case(1) : return new WandererEvent(player);
	
		case(2) : return new MuseumEvent(player, m);
	
		case(3) : return new RatEvent(player, m);
		
		case(4) : return new CatBugsEvent(player, m);
		
		case(5) : return new UGAEvent(player);
		
		default : return new MessEvent(player, m);
		}
	}
	
	
}
