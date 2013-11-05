package Models;
import java.util.Random;

/**
 * RandomEventFactory object that builds RandomEvent objects at the request 
 * of a client.
 * 
 * @author kevin
 *
 */
public class RandomEventFactory {
	Random rand = new Random();
	
	public RandomEventFactory() {
		//TODO constructor
	}
	
	public RandomEvent buildGoodEvent() {
		switch (rand.nextInt(4)) {
			case(0) : return new AlumniEvent();
		
			case(1) : return new WandererEvent();
		
			case(2) : return new MuseumEvent();
		
			default : return new RatEvent();
		}
		
	}
	
	public RandomEvent buildFromAllEvent() {
		switch (rand.nextInt(7)) {
		case(0) : return new AlumniEvent();
	
		case(1) : return new WandererEvent();
	
		case(2) : return new MuseumEvent();
	
		case(3) : return new RatEvent();
		
		case(4) : return new CatBugsEvent();
		
		case(5) : return new UGAEvent();
		
		default : return new MessEvent();
		}
	}
	
	
}
