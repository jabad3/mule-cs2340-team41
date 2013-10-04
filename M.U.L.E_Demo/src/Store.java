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
     * Creates a Store object with the proper initial inventory.
     * The initial inventory depends on the difficulty.
     * 
     * @param difficulty The game's difficulty
     * @return A Store object with proper inventory
     */
    public static Store buildStore(Difficulty difficulty) {
        return new Store(difficulty);
    }
    
    /**
     * Creates a store based on a difficulty.
     * 
     * @param difficulty
     */
    public Store(Difficulty difficulty) {
        /* Integer.MAX_VALUE / 2 is an arbitrarily large number
         * for the Store's initial money count.
         * 
         * We use a large money count to simulate the Store's infinite supply
         * of money because we still want the Store to function like a Trader.
         * A Trader must pull money out of their inventory to pay another
         * Trader.
         * 
         * The alternative approach is that the Store pays Traders without
         * removing money from its Inventory, but in this case, Store's
         * behavior deviates too far from how we expect a Trader to behave
         * (i.e. possible violation of LSP)
         */
        int food = difficulty.storeFoodSetting();
        int energy = difficulty.storeEnergySetting();
        int ore = difficulty.storeOreSetting();
        int money = Integer.MAX_VALUE / 2;
        int mules = difficulty.storeMuleSetting();
        
        inventory = new Inventory(food, energy, ore, money, mules);
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

    
    
    /* for temporary "testing" */
    public String toString() {
        return inventory.toString();
    }

}
