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
    public static Map buildMap(String mapType) {
        /* use if we stick with a char map, otherwise delete */
        /*final char MOUNTAIN_1 = '1';
        final char MOUNTAIN_2 = '2';
        final char MOUNTAIN_3 = '3';
        final char PLAIN = 'p';
        final char RIVER = 'r';
        final char TOWN = 't';
        
        char[][] charMap;*/
        
        LandPlotType[][] typeMap;
        LandPlot[][] landPlots;
        
        if ("Random".equals(mapType))
            typeMap = MapGenerator.generateRandomMap();
        else // Default
            typeMap = MapGenerator.generateDefaultMap();
        
        landPlots = new LandPlot[typeMap.length][typeMap[0].length];
        for (int row = 0; row < typeMap.length; row++) {
            for (int col = 0; col < typeMap[0].length; col++) {
                LandPlotType currentType = typeMap[row][col];
                landPlots[row][col] = new LandPlot(currentType);
            }
        }
        
        return new Map(landPlots);
    }
    
    
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
}
