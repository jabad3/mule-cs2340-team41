
public class LandPlot {

    private LandPlotType landType;
    
    public LandPlot(LandPlotType landType) {
        this.landType = landType;
    }
    
    /* for temporary "testing" */
    public String toString() {
        return landType.name();
    }
}
