
public class LandPlot {
	
	private Player player;
    private LandPlotType landType;
    
    public LandPlot(LandPlotType landType) {
        this.landType = landType;
    }
    
    public Player getOwner() {
    	return player;
    }
    
    public void setOwner(Player player) {
    	this.player = player;
    }
    
    /* for temporary "testing" */
    public String toString() {
        return landType.name();
    }
}
