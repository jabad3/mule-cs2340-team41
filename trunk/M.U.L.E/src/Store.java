/**
 * The store is responsible for selling mules inside the town, selling land
 * during land auctions, 
 * 
 * The store can also play the role of a buyer buy purchasing land when
 * 
 * @author Max
 *
 */
public class Store extends Trader {

    
    /**
     * Creates a store based on its intial quantities of each resource.
     * 
     * @param food Initial food quantity
     * @param energy Initial energy quantity
     * @param ore Initial ore quantity
     * @param money Initial money quantity
     * @param mules Initial mule quantity
     */
    public Store(int food, int energy, int ore, int money, int mules) {
        super(food, energy, ore, money, mules);
    }
    
    /**
     * Creates a store based on a difficulty.
     * 
     * @param difficulty
     */
    public Store(Difficulty difficulty) {
        if (difficulty == Difficulty.BEGINNER)
            inventory = new Inventory(16, 16, 0, Integer.MAX_VALUE / 2, 0);
        else  // Standard or Tournament
            inventory = new Inventory(8, 8, 8, Integer.MAX_VALUE / 2, 8);
    }
    
    @Override
    public void buyMuleFromSeller(Trader seller, Resource muleConfig, int price)
            throws FailedTransactionException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void buyLandFromSeller(Trader seller, int price)
            throws FailedTransactionException {
        // TODO Auto-generated method stub
        
    }
    
    public void buildMulesWithOre() {
        // TODO
    }

}
