/**
 * A Trader represents an object in the game that has the ability
 * to buy and sell goods to others who are also Traders
 * 
 * @author Max
 *
 */
public abstract class Trader {
    protected Inventory inventory;
    
    public Trader(int food, int energy, int ore, int money, int mules) {
        inventory = new Inventory(food, energy, ore, money, mules);
    }
    
    public Trader() {}
    
    
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
    public void buyResourceFromSeller(Trader seller, Resource rType, int price) throws FailedTransactionException {
        try {
            seller.removeResource(rType);
            this.paySeller(seller, price);
            this.addResource(rType);
        }
        catch (FailedTransactionException e) {
            throw e;
        }
    }
    
    public void removeResource(Resource rType) throws FailedTransactionException {
        if (inventory.getResourceCount(rType) == 0) 
            throw new FailedTransactionException("Not in stock.");
        else
            inventory.removeResource(rType);
    }
    
    public void addResource(Resource rType) {
        inventory.addResource(rType);
    }

    // maybe make this abstract, and have "Store-version" be unable to throw OutOfMoneyException
    // or handle store's infinite funds in another way
    private void paySeller(Trader seller, int price) throws FailedTransactionException {
        // TODO
        /*int currentFunds = inventory.getMoneyCount();
        if (currentFunds < price)
            throw new FailedTransactionException("Sorry, not enough money.");
        else {
            this.inventory.withdrawMoney(price);
            seller.depositMoney(price);
        }*/
    }
    
    /**
     * Trader increases their money count by the given amount
     * 
     * @param amount
     */
    protected void depositMoney(int amount) {
        inventory.depositMoney(amount);
    }
    
    // in context of Mule sales in town
    // Player-Store
    //      note:  create a Mule to "follow" the player
    //
    // Store-Player
    //      note:  destroy the mule that is "following" the player
    //
    // Because players can't sell mules to other players, it probably is
    // okay to implement the above cases in the Player and Store subclasses
    //  (maybe we could have some Trust Boundaries and do instanceOf checking if needed)
    public abstract void buyMuleFromSeller(Trader seller, Resource muleConfig, int price) throws FailedTransactionException;
    
    /* DO WE WANT THIS? */
    
    // In context of Land auctions
    //  Player - Store
    //      note:  someone needs to set the landplot's owner
    //
    // and in context of Land Store sales
    //  Store - Player   (someone needs to delete landplot's owner)
    //  Player - Store   (someone needs to set landplot's owner)
    //
    // Similarly to above, we can use polymorphism to accou
    public abstract void buyLandFromSeller(Trader seller, int price) throws FailedTransactionException; 

}