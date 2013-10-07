import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class generates the default MULE map
 * @author Erica
 */
   public class MapFactory
   {
   	
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
       * @return The 2D LandPlotType array in row-major order
       */
      public static LandPlotType[][] generateDefaultMap()
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
          /*LandPlotType[][] map = new LandPlotType[5][9];
      
         for (int i = 0; i < map.length; i++) 
         {
               map[i][4] = LandPlotType.RIVER;
         }
         
         map[3][1] = LandPlotType.MTN_1;
         map[4][2] = LandPlotType.MTN_2;
         map[3][6] = LandPlotType.MTN_2;
         map[4][8] = LandPlotType.MTN_2;
         map[0][2] = LandPlotType.MTN_1;
         map[1][1] = LandPlotType.MTN_1;
         map[2][8] = LandPlotType.MTN_1;
         map[0][6] = LandPlotType.MTN_3;
         map[1][8] = LandPlotType.MTN_3;
         map[2][0] = LandPlotType.MTN_3;

         for(int row = 0; row <map.length; row++) //fills in the remaining plots with plains
         {
             for(int col = 0; col < map[row].length; col++)
             {
                 if (map[row][col] == null)
                     map[row][col] = LandPlotType.PLAIN;
             }
         }
         
         map[2][4] = LandPlotType.TOWN;  // town is the center
        
         return map;
      } */
      
      /**
       * This method generates a randomized 2D array of land plots similar
       * to generateDefaultMap().
       * 
       * The center row will still be the same as the default map
       *  (i.e. it will have the river plots plus the town in the center)
       * 
       * @return A randomized 2D LandPlotType array
       */
      public static LandPlotType[][] generateRandomMap() {
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