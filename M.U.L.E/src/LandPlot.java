import java.awt.Color;


public class LandPlot {
	
	private Player owner;
    private LandPlotType landType;
    
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
}
