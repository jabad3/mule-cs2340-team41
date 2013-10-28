package Models;

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
    
	
	public static final int foodPrice = 30;
	public static final int energyPrice = 25;
	public static final int orePrice = 50;
	private Difficulty difficulty;
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
        this.difficulty = difficulty;
        
        inventory = new Inventory(food, energy, ore, money, mules);
    }
    
    
    /**
     * Increases the Stores quantity of mules by consuming the appropriate
     * number of ore units.
     */
    public void buildMulesWithOre() {
		for(int i = inventory.getResourceCount(Resource.ORE); i < difficulty.storeMuleSetting(); i++) {
			if(inventory.getResourceCount(Resource.ORE) >= 2) {
				inventory.addResource(Resource.MULE, 1);
				inventory.removeResource(Resource.ORE, 2);
		    }
	    }
    }
    
    /**
     * Prints the Store's inventory information.
     * Intended to be printed to the console for testing.
     * 
     * @return String representation of the Store's inventory
     */
    public String toString() {
        return inventory.toString();
    }

}
