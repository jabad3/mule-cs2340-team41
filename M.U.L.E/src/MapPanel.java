import javax.swing.*;


public class MapPanel extends JPanel{

	public MapPanel()
	{
		char[][] charMap = MapGenerator.generateDefaultMap('A','B','C','P','R','T');
		//a is one mountain, b is two, c is three
		
		//map = new LandPlot[CharMap.length][CharMap[0].length];
		// ^ a map of landplots (what class are we using for that?) as opposed to characters
	}
}
