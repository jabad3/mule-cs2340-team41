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
      public static char[][] generateMap(final char m, final char m2, final char m3, final char p, final char r, final char t)
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
   }