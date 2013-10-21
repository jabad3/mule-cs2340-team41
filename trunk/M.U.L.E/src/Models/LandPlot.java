package Models;

import java.awt.Color;


public class LandPlot {
	
	private Player owner;
    private LandPlotType landType;
    private Mule mule;
    private int totalPlotScore;
    private Resource resource;
    private int resourceCount;
    private int productionRate;
    
    public LandPlot(LandPlotType landType) {
        this.landType = landType;
    }
    
    public Player getOwner() {
    	return owner;
    }
    
    public void setOwner(Player player) {
    	this.owner = player;
    }
    
    /* for temporary "testing" */
    public String toString() {
        return landType.name();
    }

    public LandPlotType getLandType() {
        return landType;
    }

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

    public boolean isTown() {
        return landType == LandPlotType.TOWN;
    }
    
    private void calculateTotalPlotScore() {
    	if(mule == null) {
    		totalPlotScore = 500;
    	}
    	else
    		totalPlotScore = mule.getScoreValue();
    }
    
    public int getScore() {
    	calculateTotalPlotScore();
    	return totalPlotScore;
    }
}
