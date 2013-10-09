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
     * Creates a store with the proper initial inventory based on a difficulty.
     * The store is given an arbitrarily large number for its initial money
     * balance.
     * 
     * This is done in order to simulate the Store's
     * infinite supply of money while still having the Store interact with its
     * inventory as all Traders should, as a Store must still pull money out of
     * its inventory to pay another Trader.
     * 
     * @param difficulty
     */
    public Store(Difficulty difficulty) {
        /* Integer.MAX_VALUE / 2 is an arbitrarily large number
         * for the Store's initial money count.
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
    
    /**
     * Increases the Stores quantity of mules by consuming the appropriate
     * number of ore units.
     */
    public void buildMulesWithOre() {
        // TODO
    }

    /**
     * Prints the Store's inventory information.
     * Intended to be printed to the console for testing.
     */
    public String toString() {
        return inventory.toString();
    }

}
