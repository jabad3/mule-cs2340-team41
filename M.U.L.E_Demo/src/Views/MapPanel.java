package Views;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Models.LandPlot;
import Models.Map;
import Models.MapFactory;
/**
 * This class is the view for the map screen
 * @author Tim Farley
 * @version 1
 */

public class MapPanel extends JPanel {
    
    /** The number of rows in the grid of land plots. */
    private final int ROWS = 5;
    
    /** The number of columns in the grid of land plots. */
    private final int COLS = 9;
	
    /** Maintains a reference to the component representing the town. */
    LandPlotBtn townBtn;
    
	/**
	 * Creates the MapPanel containing a 5x9 grid of buttons
	 * 
	 * @param map The map object being used by the current game
	 * @param commonPlotListener A MouseListener object that will listen to
	 * each LandPlotBtn object
	 */
	public MapPanel(Map map, MouseListener commonPlotListener)
	{
	    LandPlot[][] landPlots = map.landPlotArray();
	    
	    setLayout(new GridLayout(ROWS, COLS));
	    
	    for (int row = 0; row < landPlots.length; row++) {
	        for (int col = 0; col < landPlots[0].length; col++) {
	            LandPlot plot = landPlots[row][col];
	            LandPlotBtn plotBtn = new LandPlotBtn(plot, commonPlotListener);
	            this.add(plotBtn);
	            
	            if (plot.isTown())
	                townBtn = plotBtn;
	        }
	    }
		
		this.setPreferredSize(new Dimension(600, 600));
	}
	
	/**
	 * Checks to see whether the given JComponent object has coordinates that
	 * would cause it to overlap with the town component in the MapPanel.
	 * 
	 * Pre-condition:  do not call this method with null.
	 * 
	 * @param component The component to check for overlap with town
	 * @return True if the bounds specified by the component overlap the bounds
	 * specified by the town component in the MapPanel
	 */
	public boolean overlapsTown(JComponent component) {
	    Rectangle componentBounds = component.getBounds();
	    Rectangle townBounds = townBtn.getBounds();
	    
	    return (townBounds.intersects(componentBounds));
	}
	
	/**
	 * Checks to see whether the given JComponent object has coordinates that
	 * would cause any part of it to be located outside the MapPanel.
	 * 
	 * Pre-condition:  do not call this method with null.
	 * 
	 * @param component The component to check for full-containment inside the
	 * map
	 * @return True if the entire component is inside the MapPanel
	 */
	public boolean insideMap(JComponent component) {
	    Rectangle componentBounds = component.getBounds();
        Rectangle mapBounds = this.getBounds();
        
        return (mapBounds.contains(componentBounds));
	}
	
	/**
	 * Returns the land plot associated with the LandPlotBtn at the given
	 * location.
	 * 
	 * Pre-condition:  location must be within the map panel boundary
	 * 
	 * @param location The
	 * @return The land plot model object found at the given location.
	 */
	public LandPlot getLandPlotAt(Point location) {
	    LandPlotBtn lpb = (LandPlotBtn) this.getComponentAt(location);
	    return lpb.getMyLandPlot();
	}
	
	public static void main(String[] args) {
	    MapPanel mp = new MapPanel(MapFactory.buildMap("Default"), null);
	    JFrame frame = new JFrame("MapPanel Test");
	    frame.add(mp);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.pack();
	    frame.setVisible(true);
	    System.out.println("half and half:  " + mp.getLandPlotAt(new Point(mp.getWidth()/2, mp.getHeight()/2)));
	}
}
