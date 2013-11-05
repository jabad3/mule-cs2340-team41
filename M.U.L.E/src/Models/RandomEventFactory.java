package Models;

/**
 * RandomEventFactory object that builds RandomEvent objects at the request 
 * of a client.
 * 
 * @author kevin
 *
 */
public class RandomEventFactory {
	
	public RandomEventFactory() {
		//TODO constructor
	}
	
	public RandomEvent buildGoodEvent() {
		//TODO
		return new AlumniEvent(); //temporary!
	}
	
	public RandomEvent buildFromAllEvent() {
		//TODO
		return new RatEvent(); //temporary!
	}
	
	
}
