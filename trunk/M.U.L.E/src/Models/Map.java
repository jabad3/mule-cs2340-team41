package Models;

/**
 * The Map class holds a 9x5 grid of LandPlots and methods to act on the
 * LandPlots as a whole
 * 
 * @author Max
 *
 */
public class Map {
    
    /** A 9x5 array of all landPlots in the map in row-major order. */
    private LandPlot[][] landPlots;
    
    /**
     * Creates the map model object.
     * 
     * @param landPlots The instantiated 9x5 array of landPlots
     */
    public Map(LandPlot[][] landPlots) {
        this.landPlots = landPlots;
    }

    /**
     * Returns the array of landPlots held inside the map.
     * 
     * @return 9x5 array of LandPlots in row-major order
     */
    public LandPlot[][] landPlotArray() {
        return landPlots;
    }
    
    /**
     * Calls all land plots to produce for their owner.
     */
    public void produceAll() {
        for (int i = 0; i < landPlots.length; i++) {
            for (int j = 0; j < landPlots[0].length; j++) {
                LandPlot currentPlot = landPlots[i][j];
                currentPlot.produce();
            }
        }
    }
    
    /**
     * Returns a String containing a grid of LandTypes that are in the map.
     * Intended for use in print-testing.
     */
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
}
