package Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The Map class holds a 9x5 grid of LandPlots and methods to act on the
 * LandPlots as a whole
 * 
 * @author Max
 * 
 */
public class Map implements Serializable {

    /** A 9x5 array of all landPlots in the map in row-major order. */
    private LandPlot[][] landPlots;

    /**
     * Creates the map model object.
     * 
     * @param landPlots
     *            The instantiated 9x5 array of landPlots
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
     * Calls all land plots to produce for their owner. Production starts at the
     * top-left-most land plot.
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
     * Calls all land plots to produce for their owner. The order that land
     * plots produce is random.
     */
    public void produceAllRandomOrder() {
	int width = landPlots[0].length;
	int height = landPlots.length;
	List<Integer> linearIndices = new ArrayList<>(width * height);

	for (int i = 0; i < width * height; i++)
	    linearIndices.add(i);

	Collections.shuffle(linearIndices);

	for (int i = 0; i < width * height; i++) {
	    int index = linearIndices.get(i);
	    int row = index / width;
	    int col = index % width;
	    LandPlot currentPlot = landPlots[row][col];
	    currentPlot.produce();
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
