import java.util.EnumMap;

/**
 * The inventory class keeps track of the number of resources it currently has.
 * 
 * @author Max
 *
 */
public class Inventory {

    /**
     * ResourceCount will hold quantities for:
     *   Food, Energy, Ore, Money, Mule
     */
    EnumMap<Resource, Integer> resourceCounts;
    
    public Inventory(int food, int energy, int ore, int money, int mules) {
        resourceCounts.put(Resource.FOOD, food);
        resourceCounts.put(Resource.ENERGY, energy);
        resourceCounts.put(Resource.ORE, ore);
        resourceCounts.put(Resource.MONEY, money);
        resourceCounts.put(Resource.MULE, mules);
    }
    
    /**
     * Decrements the count of the given resource type by one.
     * 
     * Precondition:  The current resource count is > 0
     * Postcondition:  The final resource count is >= 0
     * 
     * @param rType The resource to decrement
     */
    public void removeResource(Resource resource) {
        int currentCount = resourceCounts.get(resource);
        resourceCounts.put(resource, currentCount--);
    }

    /**
     * Increments the count of the given resource type by one.
     * 
     * @param rType The resource to increment
     */
    public void addResource(Resource resource) {
        // TODO Auto-generated method stub
        
    }
    
    /**
     * Returns the current count of the given resource
     * @param resource The resource type we care about
     * @return The count of the given resource type
     */
    public int getResourceCount(Resource resource) {
        return resourceCounts.get(resource);
    }
    
    /**
     * Subtracts the amount of money withdrawn from the original balance
     * 
     * Precondition:  Current money count must be >= amount
     * Postcondition:  Current money count must be >= 0
     * 
     * @param amount The amount of money to be withdrawn
     */
    public void withdrawMoney(int amount) {
        int currentBalance = resourceCounts.get(Resource.MONEY);
        int newBalance = currentBalance - amount;
        resourceCounts.put(Resource.MONEY, newBalance);
    }
    
    /**
     * Adds the amount of money deposited to the original balance
     * 
     * @param amount The amount of money to be deposited
     */
    public void depositMoney(int amount) {
        int currentBalance = resourceCounts.get(Resource.MONEY);
        int newBalance = currentBalance + amount;
        resourceCounts.put(Resource.MONEY,  newBalance);
    }
}
