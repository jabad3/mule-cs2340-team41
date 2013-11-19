package Views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import Models.LandPlot;
import Models.Mule;
import Models.Resource;

/**
 * LandPlotBtn represents the visual representation of a land plot in the game
 * model.
 * 
 * LandPlotBtns are icons that display the type of land and the owner of the
 * land. Ownership is signified by coloring the border to the owner's color.
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
     * @param landPlot
     *            The land plot model object to be displayed
     * @param buttonListener
     *            A mouse listener for the land plot object that is used to
     *            respond to user interaction with the land plot icon
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
	if (myLandPlot.hasMule())
	    drawMuleIcon(g);
    }

    /**
     * Draws a mule icon in the lower left of the image if the land plot has a
     * mule.
     * 
     * @param g
     *            The graphics object for this component
     */
    private void drawMuleIcon(Graphics g) {
	// TODO Draw an image
	// Image iconImg = new ImageIcon("mule_icon.png").getImage();
	Image iconImg = myLandPlot.getMule().getIcon();
	g.drawImage(iconImg, 0, 0, 50, 50, null);

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