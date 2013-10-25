package Views;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import Models.LandPlot;

/**
 * LandPlotBtn represents the visual representation of a land plot in the
 * game model.
 * 
 * LandPlotBtns are icons that display the type of land and the owner of the
 * land.  Ownership is signified by coloring the border to the owner's color.
 * Unowned land plots should have no border.
 * 
 * @author Max
 *
 */
public class LandPlotBtn extends ResizableIcon {
    
    /** The land plot model object associated with this icon. */
    private LandPlot myLandPlot;

    /**
     * Create a LandPlotBtn object.
     * 
     * @param landPlot The land plot model object to be displayed
     * @param buttonListener A mouse listener for the land plot object that is
     * used to respond to user interaction with the land plot icon
     */
    public LandPlotBtn(LandPlot landPlot, MouseListener buttonListener) {
        super(landPlot.getLandType().getStockImageIcon());
        this.myLandPlot = landPlot;
        this.addMouseListener(buttonListener);
        drawBorder();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // TODO needed later for painting mule, resource
    }

    /**
     * Draw the correct border by querying the land plot model object.
     */
    private void drawBorder() {
        if (myLandPlot.getOwner() == null) {
            setBorderToDefault();
        } else {
            Color borderColor = myLandPlot.getBorderColor();
            this.setBorder(BorderFactory.createLineBorder(borderColor, 3));
        }
    }
    
    /**
     * Get the land plot model object associated with this icon.
     * 
     * @return The LandPlot for this icon
     */
    public LandPlot getMyLandPlot() {
        return myLandPlot;
    }

    /**
     * Set the border to the default empty border.
     */
    public void setBorderToDefault() {
        this.setBorder(BorderFactory.createEmptyBorder());
    }

}