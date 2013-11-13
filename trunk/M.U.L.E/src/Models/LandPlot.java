package Models;

import java.awt.Color;
import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.EnumMap;

/**
 * The LandPlot class represents the model object for land tiles.
 * LandPlots are characterized by their type, their owner, and their mule.
 * LandPlots do not need to have mules installed, nor do they need to be owned.
 * Owners and installed mules can change over time, but their type remains the
 * same for a single game.
 * 
 * @author Max
 *
 */
public class LandPlot implements Serializable {
    
    /** Table of production values based on land type, resource type. */
    private static final Map<LandPlotType, Map<Resource, Integer>> productionTable;
    
    /** Initializes the productionTable */
    static {
        Map<Resource, Integer> riverMap = new EnumMap<>(Resource.class);
        riverMap.put(Resource.FOOD, 4);
        riverMap.put(Resource.ENERGY, 2);
        riverMap.put(Resource.ORE, 0);
        
        Map<Resource, Integer> plainMap = new EnumMap<>(Resource.class);
        plainMap.put(Resource.FOOD, 2);
        plainMap.put(Resource.ENERGY, 3);
        plainMap.put(Resource.ORE, 1);
        
        Map<Resource, Integer> m1Map = new EnumMap<>(Resource.class);
        m1Map.put(Resource.FOOD, 1);
        m1Map.put(Resource.ENERGY, 1);
        m1Map.put(Resource.ORE, 2);
        
        Map<Resource, Integer> m2Map = new EnumMap<>(Resource.class);
        m2Map.put(Resource.FOOD, 1);
        m2Map.put(Resource.ENERGY, 1);
        m2Map.put(Resource.ORE, 3);
        
        Map<Resource, Integer> m3Map = new EnumMap<>(Resource.class);
        m3Map.put(Resource.FOOD, 1);
        m3Map.put(Resource.ENERGY, 1);
        m3Map.put(Resource.ORE, 4);
        
        Map<LandPlotType, Map<Resource, Integer>> table = new EnumMap<>(LandPlotType.class);
        table.put(LandPlotType.PLAIN, plainMap);
        table.put(LandPlotType.RIVER, riverMap);
        table.put(LandPlotType.MTN_1, m1Map);
        table.put(LandPlotType.MTN_2, m2Map);
        table.put(LandPlotType.MTN_3, m3Map);
        
        productionTable = Collections.unmodifiableMap(table);
    }
    
    
	
    /** The owner of the land plot.  Null if no owner. */
	private Player owner;
	
	/** The type of land that this land plot represents. */
    private LandPlotType landType;
    
    /** The mule installed on the land plot.  Null if no mule installed. */
    private Mule mule;
    
    /** Holds the number of resource produced most recently by the land plot. */
    private int lastAmountProduced;
    
    /**
     * Creates a LandPlot object with a specified LandPlotType.
     * 
     * @param landType The LandPlotType of the land plot
     */
    public LandPlot(LandPlotType landType) {
        this.landType = landType;
    }
    
    /**
     * Gets the owner of the land plot.
     * 
     * @return The owner of the land plot
     */
    public Player getOwner() {
    	return owner;
    }
    
    /**
     * Sets the owner of the land plot to the given Player.
     * 
     * @param player The new owner of the land plot
     */
    public void setOwner(Player player) {
    	this.owner = player;
    }

    /**
     * Gets the land type for this land plot.
     * 
     * @return The LandPlotType for this land plot
     */
    public LandPlotType getLandType() {
        return landType;
    }

    /**
     * Gets the border color for this land plot.
     * 
     * @return Color of the land plot's border
     */
    public Color getBorderColor() {
        if (owner == null)
            return null;
        return owner.getColor();
    }

    /**
     * A land plot is available to be purchased (or selected using
     * a land grant) as long as it has no owner and it is not the town.
     * 
     * @return True if the land plot can still be claimed by a Player,
     * false otherwise
     */
    public boolean isAvailable() {
        return (owner == null && landType != LandPlotType.TOWN);
    }

    /**
     * Checks whether or not a land plot is the town.
     * 
     * @return True if the land plot is the town.
     */
    public boolean isTown() {
        return landType == LandPlotType.TOWN;
    }
    
    /**
     * Calculate the score value of a given land plot.
     * If there is no mule on a land plot, it's score value is 500 by default.
     * If there is a mule on a land plot, its score is determined solely
     * by the installed mule.
     * 
     * @return Score value for the land plot
     */
    public int calculateScore() {
    	int totalPlotScore = 0;
    	if(mule == null)
    		totalPlotScore = 500;
    	else
    		totalPlotScore = 500 + mule.getScoreValue();
    	return totalPlotScore;
    }

    /**
     * Checks whether or not the given Player is the owner of this land plot.
     * 
     * @param player The player we are checking for ownership of the plot
     * @return Return true if the given Player is the owner
     */
    public boolean isOwnedBy(Player player) {
        return player.equals(owner);
    }

    /**
     * Gets the mule on this LandPlot.
     * 
     * @return The Mule on the LandPlot
     */
    public Mule getMule() {
        return mule;
    }
    
    /**
     * Sets the mule on this LandPlot to the given mule.
     * 
     * @param mule The Mule to place on the LandPlot
     */
    public void setMule(Mule mule) {
        this.mule = mule;
    }
    
    /**
     * Checks whether or not the LandPlot has a mule.
     * 
     * @return True if there is a mule on this LandPlot
     */
    public boolean hasMule() {
        return mule != null;
    }
    
    /**
     * Gets the resource that this landplot will produce.  Null if the land
     * plot cannot produce anything (i.e. land plot has no mule).
     * 
     * @return The Resource that this land plot will produce
     * (null if the land plot cannot produce anything)
     */
    public Resource getResourceForProduction() {
        return mule == null ? null : mule.getMuleType();
    }
    
    @Override
    public String toString() {
        return landType.name();
    }

    /**
     * Land plot produces its resource, increasing its owner's total.
     * Production occurs by:
     *  1) Powering up the mule
     *  2) Determine production results (# of resource that was produced)
     *  3) Adding resources to the owner's inventory
     *  
     * No production occurs if
     *  a) Plot is unowned
     *  b) Plot has no mule
     *  c) the plot's mule cannot be powered
     *       (NOTE:  energy mules do NOT require energy units to function)
     */
    public void produce() {
        if (owner == null || mule == null || isTown() || mule.getMuleType() == null) {  // no mule, no production
            lastAmountProduced = 0;
            return;
        }
        
        Resource resourceToProduce = mule.getMuleType();
        if (canProduce(resourceToProduce))
            giveProductionToOwner(resourceToProduce);
        else
            lastAmountProduced = 0;
    }
    
    /**
     * Checks whether the land plot can produce a resource this turn.
     * 
     * Pre-condition:  owner and mule are both non-null
     * 
     * Land plots with mules can only produce if the mule is an energy mule,
     * or if the mule is non-energy and has consumed an energy unit from
     * its owner
     * 
     * @param resourceToProduce The resource that the land plot is configured
     * to produce
     * @return True if this land plot can produce this turn
     */
    private boolean canProduce(Resource resourceToProduce) {
        boolean hasEnergyMule = resourceToProduce == Resource.ENERGY;
        boolean muleCanBePowered = owner.getEnergy() > 0;
        return hasEnergyMule || muleCanBePowered;
    }
    
    /**
     * Determine the amount to produce, then add to the owner's inventory
     * @param resourceToProduce The resource to be produced
     */
    private void giveProductionToOwner(Resource resourceToProduce) {
        if (resourceToProduce != Resource.ENERGY)
            powerMuleByConsumingEnergy();
        lastAmountProduced = productionTable.get(landType).get(resourceToProduce);
        owner.addResource(resourceToProduce, lastAmountProduced);
    }
    
    /**
     * Powers a mule by consuming one energy unit from the owner.
     * 
     * Pre-conditions:  land plot has a mule and owner, and owner has enough
     * energy units to power a mule.
     * 
     * @return True if the mule on this land plot consumed an energy unit, 
     * making it powered
     */
    private boolean powerMuleByConsumingEnergy() {
        try {
            owner.removeResource(Resource.ENERGY, 1);
            return true;
        } catch (FailedTransactionException e) {
            return false;
        }
    }
    
    /**
     * Gets the last amount produced by this land plot.
     * 
     * @return The last amount produced by the land plot
     */
    public int getLastAmountProduced() {
        return lastAmountProduced;
    }
    
}
