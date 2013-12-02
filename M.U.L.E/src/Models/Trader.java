package Models;

import java.io.Serializable;

/**
 * A Trader represents an object in the game that has the ability
 * to buy and sell goods to others who are also Traders
 * 
 * @author Max
 *
 */
public abstract class Trader implements Serializable {
	
    /** The inventory for this Trader object */
    protected Inventory inventory;
    
    /**
	 * finds the amount of food in a trader's inventory
	 * 
	 * @return The amount of food resource in the trader's inventory
	 */
	public int getFood() {
		int food = this.inventory.getResourceCount(Resource.FOOD);
		return food; 
	}
	
	/**
	 * finds the amount of energy in a trader's inventory
	 * 
	 * @return The amount of energy resource in the trader's inventory
	 */
	public int getEnergy() {
		int energy = this.inventory.getResourceCount(Resource.ENERGY);
		return energy; 
	}
	
	/**
	 * finds the amount of ore in a trader's inventory
	 * 
	 * @return The amount of ore resource in the trader's inventory
	 */
	public int getOre() {
		int ore = this.inventory.getResourceCount(Resource.ORE);
		return ore; 
	}
	
	/**
	 * finds the amount of money in a trader's inventory
	 * 
	 * @return The amount of money in the trader's inventory
	 */
	public int getMoney() {
		int money = this.inventory.getResourceCount(Resource.MONEY);
		return money; 
	}
	
	/**
	 * finds the amount of mules in a trader's inventory
	 * 
	 * @return The amount of mules in the trader's inventory
	 */
	public int getMules() {
		int mules = this.inventory.getResourceCount(Resource.MULE);
		return mules; 
	}
    
    /**
     * This method is used to carry out a transaction
     * between a buyer (this) and a seller involving a resource.
     * 
     * Possible Buyer-Seller Pairs:
     *      Player-Player
     *      Player-Store
     *      Store-Player
     *
     * @param seller Trader who is selling the resource
     * @param rType The type of resource that is being traded
     * @param unitPrice The price of a single unit of resource that is being traded
     * @throws FailedTransactionException
     *          Thrown when a buyer (this) does not have enough money,
     *          or when the seller does not have the resource in stock.
     */
    public void buyResourceFromSeller(Trader seller, Resource rType, int unitPrice, int resourceCount) throws FailedTransactionException {
        
        try {
            int grandTotal = unitPrice * resourceCount;
            
            seller.removeResource(rType, resourceCount);
            this.paySeller(seller, grandTotal);
            this.addResource(rType, resourceCount);
        }
        catch (FailedTransactionException e) {
            throw e;
        }
    }
    
    /**
     * Decrements the amount of the given resource that the Trader has by one.
     * 
     * @param rType The type of Resource to remove
     * @throws FailedTransactionException
     *          Thrown when the Trader does not have any units of the resource,
     *          in which case it is impossible to remove a unit of the resource.
     */
    public void removeResource(Resource rType, int resourceCount) throws FailedTransactionException {
        if (inventory.getResourceCount(rType) < resourceCount) 
            throw new FailedTransactionException("Not in stock.");
        else
            inventory.removeResource(rType, resourceCount);
    }
    
    /**
     * Increments the amount of the given resource that the Trader has by one.
     * 
     * @param rType The type of Resource to add
     */
    public void addResource(Resource rType, int resourceCount) {
        inventory.addResource(rType, resourceCount);
    }

    /**
     * Transfer a specified amount of money from the buyer (this Trader) to
     * another Trader who acts as the seller.
     * 
     * @param seller The Trader who will receive the payment
     * @param price The amount that the seller will receive
     * @throws FailedTransactionException
     *          Thrown when the buyer (this) does not have enough money to
     *          make the payment.
     */
    public void paySeller(Trader seller, int price) throws FailedTransactionException {
        // TODO
        int currentFunds = inventory.getResourceCount(Resource.MONEY);
        if (currentFunds < price)
            throw new FailedTransactionException("Sorry, not enough money.");
        else {
            this.inventory.withdrawMoney(price);
            seller.depositMoney(price);
        }
    }
    /**
     * deducts all of given resource from Trader's inventory
     * 
     * @param resource the resource to deduct
     */
    public void deductAll(Resource resource) {
    	inventory.deductAll(resource);
    }
    
    /**
     * Increases this Trader's money count by the given amount.
     * 
     * @param amount The total funds to add to the Trader's money balance
     */
    protected void depositMoney(int amount) {
        inventory.depositMoney(amount);
    }
    
    /**
     * Carries out a mule sale transaction between this Trader (the buyer) and
     * another Trader (the seller).
     * 
     * @param seller The Trader selling the mule
     * @param muleConfig The ResourceType that the mule has been configured to produce
     * @param price The amount that the mule is being purchased for
     * @throws FailedTransactionException
     *          Thrown if the buyer does not have enough money
     *          to pay for the mule, or if the seller has no mules to sell
     */
}