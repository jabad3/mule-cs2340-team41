/**
 * The Map class holds a 9x5 grid of LandPlots
 * @author Max
 *
 */
public class Map {

    /**
     * A factory method for producing two kinds of Maps:
     *   1)  the Default map
     *   2)  a randomized map
     *   
     * Every map will always have the river running down the middle, as
     * well as have a Town in the center LandPlot.
     * 
     * This method should be called with either the string "Default" or
     * the string "Random".  Any other string entry will produce
     * the default map object.
     */
    
    private LandPlot[][] landPlots;
    
    public Map(LandPlot[][] landPlots) {
        this.landPlots = landPlots;
    }
    
    /* for temporary "testing" */
    public String toString() {
        String result = "\n";
        for (int row = 0; row < landPlots.length; row++) {
            for (int col = 0; col < landPlots[0].length; col++) {
                result = result + landPlots[row][col].toString() + " ";
            }
            result += "\n";
        }
        return result;
    }

    public LandPlot[][] landPlotArray() {
        return landPlots;
    }
}
