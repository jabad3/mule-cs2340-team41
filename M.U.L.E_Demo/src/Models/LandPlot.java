package Models;

import java.awt.Color;

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
public class LandPlot {
	
    /** The owner of the land plot.  Null if no owner. */
	private Player owner;
	
	/** The type of land that this land plot represents. */
    private LandPlotType landType;
    
    /** The mule installed on the land plot.  Null if no mule installed. */
    private Mule mule;
    
    /** Holds the number of resource produced most recently by the land plot. */
    private int productionRate;
    
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
    
    @Override
    public String toString() {
        return landType.name();
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
    		totalPlotScore = mule.getScoreValue();
    	return totalPlotScore;
    }
}