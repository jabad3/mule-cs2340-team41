import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class generates the default MULE map
 * @author Erica
 * @Version 1
 */
   public class MapGenerator
   {
   	/**
    	 * Generates a character map
    	 * @param m
    	 *           a character that represents a plot with one mountain
    	 * @param m2
    	 *           a character that represents a plot with two mountains 
    	 * @param m3
    	 *           a character that represents a plot with three mountains
    	 * @param p
    	 *           a character that represents a plot with a plain
    	 * @param r
    	 *           a character that represents a plot with a river
    	 * @param t
    	 * 			 a character that represents a plot with the town      
    	 * 
    	 * @return a 2-d array containing characters that represent mountains, plains, and rivers
    	 */
      public static char[][] generateDefaultMap(final char m, final char m2, final char m3, final char p, final char r, final char t)
      {
         char[][] map = new char[5][9];
      
         for (int i = 0; i < map.length; i++) 
         {
               map[i][4] = r;
         }
         
         map[3][1] = m2;
         map[4][2] = m2;
         map[3][6] = m2;
         map[4][8] = m2;
         map[0][2] = m;
         map[1][1] = m;
         map[2][8] = m;
         map[0][6] = m3;
         map[1][8] = m3;
         map[2][0] = m3;

         for(int row = 0; row <map.length; row++) //fills in the remaining plots with plains
         {
        	 for(int col = 0; col < map[row].length; col++)
        	 {
        		 if (map[row][col] == 0)
        			 map[row][col] = p;
        	 }
         }
      	
         return map;
      }
      
      /**
       * The method generates a 2D array of land plot types.
       * The type at each (row,col) coordinate corresponds to the
       * type that the actual land plot at that positions will have.
       * @return The 2D LandPlotType array
       */
      public static LandPlotType[][] generateDefaultMap()
      {
         LandPlotType[][] map = new LandPlotType[5][9];
      
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
      }
      
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