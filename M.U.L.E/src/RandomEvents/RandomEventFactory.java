package RandomEvents;

import java.util.Random;

/**
 * RandomEventFactory object that builds RandomEvent objects at the request of a
 * client.
 * 
 * @author kevin
 * 
 */
public class RandomEventFactory {
    Random rand = new Random();

    /**
     * builds a random event from the set of "good" events
     * 
     * @return the newly instantiated "good" event that is chosen
     */
    public RandomEvent buildGoodEvent() {
	switch (rand.nextInt(4)) {
	case (0):
	    return new AlumniEvent();

	case (1):
	    return new WandererEvent();

	case (2):
	    return new MuseumEvent();

	default:
	    return new RatEvent();
	}

    }

    /**
     * builds a random event from the set of all events
     * 
     * @return the newly instantiated event that is chosen
     */
    public RandomEvent buildFromAllEvent() {
	switch (rand.nextInt(7)) {
	case (0):
	    return new AlumniEvent();

	case (1):
	    return new WandererEvent();

	case (2):
	    return new MuseumEvent();

	case (3):
	    return new RatEvent();

	case (4):
	    return new CatBugsEvent();

	case (5):
	    return new UGAEvent();

	default:
	    return new MessEvent();
	}
    }

}
