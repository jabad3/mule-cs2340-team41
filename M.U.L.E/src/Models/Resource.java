package Models;

/**
 * Resources for the game of MULE are items (excluding land plots) that can
 * be exchanged.  The different types of Resources, then, are:  Food, Energy,
 * Ore, Money, and Mule.
 * 
 * Mule objects in the game are outfitted using Resources, so Resources must
 * also contain scoring-related information.
 * 
 * @author Max
 *
 */
public enum Resource {
    FOOD(25),
    ENERGY(50),
    ORE(75),
    MONEY(0),
    MULE(0);
    
    /** The score bonus for mules configured to produce this resource. */
    private int muleTypeScore;
    
    /**
     * Create a Resource enum.
     * 
     * @param muleTypeScore The score bonus for mules that have been configured
     * to this particular resource
     */
    private Resource(int muleTypeScore) {
        this.muleTypeScore = muleTypeScore;
    }
    
    /**
     * Gets the muleTypeScore for a particular Resource.
     * 
     * @return The muleTypeScore for the Resource
     */
    public int getMuleTypeScore() {
        return muleTypeScore;
    }
}
