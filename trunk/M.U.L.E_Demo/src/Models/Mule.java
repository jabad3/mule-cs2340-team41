package Models;

/**
 * This class represents the Mule model objects in a game of MULE.
 * Mules are responsible for producing 
 * @author Max
 *
 */
public class Mule {
    
    /** The resource that the mule is configured to produce. */
	private Resource muleType;
	
	/**
	 * Create and outfit a Mule using the given resource type.
	 * 
	 * @param resource The resource that the mule will produce
	 */
	public Mule(Resource resource) {
		this.outfit(resource);
	}

	/**
	 * Configures a mule to produce the given resource.
	 * 
	 * @param resource The resource that the mule will produce.
	 */
	public void outfit(Resource resource) {
		muleType = resource;
	}

	/**
	 * Returns the score value provided by the mule.
	 * A mule's score value is determined by its current outfitting.
	 * 
	 * @return The score value of the mule
	 */
	public int getScoreValue() {
	    return (muleType == null) ? 0 : muleType.getMuleTypeScore();
	}

	/**
	 * Gets the type of resource that this mule has been set to produce.
	 * 
	 * @return The Resource that this mule has been outfitted to produce
	 */
    public Resource getMuleType() {
        return muleType;
    }
}
