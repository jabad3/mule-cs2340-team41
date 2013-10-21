package Models;

/**
 * A Trader represents an object in the game that has the ability
 * to buy and sell goods to others who are also Traders
 * 
 * @author Max
 *
 */
public abstract class Trader {
	
    /** The inventory for this Trader object */
    protected Inventory inventory;
    
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
     * @param price The price of the resource that is being traded
     * @throws FailedTransactionException
     *          Thrown when a buyer (this) does not have enough money,
     *          or when the seller does not have the resource in stock.
     */
    public void buyResourceFromSeller(Trader seller, Resource rType, int price, int resourceCount) throws FailedTransactionException {
        try {
            seller.removeResource(rType, resourceCount);
            this.paySeller(seller, price);
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
        if (inventory.getResourceCount(rType) == 0) 
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
    protected void paySeller(Trader seller, int price) throws FailedTransactionException {
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
    public abstract void buyMuleFromSeller(Trader seller, Resource muleConfig, int price) throws FailedTransactionException;
    
    /**
     * Carries out the transaction when a Trader purchases a LandPlot.
     * LandPlot sales can occur during Land Auctions or during Development
     * by visiting the Land Office.
     * 
     * @param seller The Trader who is selling the land
     * @param price The amount this Trader (the buyer) must pay
     * @throws FailedTransactionException
     *          Thrown if the buyer cannot afford the price
     */
    public abstract void buyLandFromSeller(Trader seller, int price) throws FailedTransactionException; 
    /* DO WE WANT THIS? */
}