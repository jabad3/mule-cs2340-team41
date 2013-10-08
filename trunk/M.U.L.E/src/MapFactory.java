import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class is responsible for creating Map objects based on a given type of
 * map.
 * 
 * The two types of maps that the MapFactory can create are:
 *      1) "Default"
 *      2) "Random"
 *      
 * The difference between the two maps is only in the arrangement of Plain
 * and Mountain LandPlots in the map.
 *      
 * If MapFactory is asked to build a map type that it cannot identify, it
 * will build a default map object.
 * 
 * @author Erica
 */
   public class MapFactory
   {
   	   /**
   	    * Creates the map object depending on mapType.  The String mapType
   	    * should be either "Default" or "Random".  Any other String, including
   	    * null, will result in the default map being returned.
   	    * 
   	    * @param mapType String specifying the map type
   	    * @return A map object with LandPlots arranged according to mapType
   	    */
       public static Map buildMap(String mapType) {
           
           LandPlotType[][] typeMap;
           LandPlot[][] landPlots;
           
           if ("Random".equals(mapType))
               typeMap = generateRandomMap();
           else // Default
               typeMap = generateDefaultMap();
           
           landPlots = new LandPlot[typeMap.length][typeMap[0].length];
           for (int row = 0; row < typeMap.length; row++) {
               for (int col = 0; col < typeMap[0].length; col++) {
                   LandPlotType currentType = typeMap[row][col];
                   landPlots[row][col] = new LandPlot(currentType);
               }
           }
           
           return new Map(landPlots);
       }
      
      /**
       * The method generates a 2D array of land plot types.
       * The type at each (row,col) coordinate corresponds to the
       * type that the actual land plot at that positions will have.
       * 
       * The arrangement of LandPlotTypes is based on the specification
       * for the default map.
       * 
       * @return The 2D LandPlotType array in row-major order
       */
      private static LandPlotType[][] generateDefaultMap()
      {
         LandPlotType p = LandPlotType.PLAIN;
         LandPlotType r = LandPlotType.RIVER;
         LandPlotType t = LandPlotType.TOWN;
         LandPlotType m1 = LandPlotType.MTN_1;
         LandPlotType m2 = LandPlotType.MTN_2;
         LandPlotType m3 = LandPlotType.MTN_3;
         
         LandPlotType[][] defaultMap =  { { p, p, m1, p, r, p, m3, p, p },
                                          { p, m1, p, p, r, p, p, p, m3 },
                                          { m3, p, p, p, t, p, p, p, m1 },
                                          { p, m2, p, p, r, p, m2, p, p },
                                          { p, p, m2, p, r, p, p, p, m2 } };
         return defaultMap;
     }
      
      /**
       * This method generates a randomized 2D array of land plots similar
       * to generateDefaultMap().
       * 
       * The center row will still be the same as the default map
       *  (i.e. it will have the river plots plus the town in the center)
       *  
       * The random map will have the same number of each LandPlotType that
       * the default map has.
       * 
       * @return A randomized 2D LandPlotType array
       */
      private static LandPlotType[][] generateRandomMap() {
          LandPlotType[][] typeMap = generateDefaultMap();
          
          // Allocate an array big enough to hold all positions except
          // center column
          List<LandPlotType> positionsToShuffle = new ArrayList<>(9 * 4);
          
          // add appropriate values to be shuffled
          for (int row = 0; row < typeMap.length; row++) {
              for (int col = 0; col < typeMap[0].length; col++) {
                  // Leave middle column untouched
                  if (col != 4)
                      positionsToShuffle.add(typeMap[row][col]);
              }
          }
          
          Collections.shuffle(positionsToShuffle);
          
          // add back into the original array
          for (int row = 0; row < typeMap.length; row++) {
              for (int col = 0; col < typeMap[0].length; col++) {
                  // Leave middle column untouched
                  if (col != 4) {
                      typeMap[row][col] = positionsToShuffle.remove(positionsToShuffle.size() - 1);
                  }
              }
          }
          return typeMap;
      }
      
   }