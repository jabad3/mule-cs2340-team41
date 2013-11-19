package Views;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Displays the Town. The Town is made up of four components: 1) The Mule Store
 * 2) The Pub 3) The Land Office 4) The Assay Office
 * 
 * The Town must also be able to display a PlayerPawn icon that the user can
 * move around. The user interacts with each component of the town by moving the
 * PlayerPawn into the appropriate entry zones for each of the components.
 * 
 * @author Max
 * 
 */
public class TownPanel extends JPanel {

    /** The PlayerPawn object that will be drawn on the TownPanel */
    PlayerPawn playerPawn;

    /** Displays the store */
    JComponent storeDisplay;

    /** Displays the assay office */
    JComponent assayDisplay;

    /** Displays the land office */
    JComponent landOfficeDisplay;

    /** Displays the pub */
    JComponent pubDisplay;

    /** Displays the path that the user can walk */
    JComponent pathDisplay;

    /**
     * Creates a TownPanel object with the default preferredSize of 600x400.
     */
    public TownPanel() {
	this(new Dimension(600, 400));
    }

    /**
     * Creates a TownPanel with a specified preferredSize.
     * 
     * @param preferredSize
     *            Dimension specifying the preferredSize of the panel
     */
    public TownPanel(Dimension preferredSize) {
	setLayout(new GridLayout(3, 3));
	initializeIconLabels();

	// Top row, Left --> Right
	add(storeDisplay);
	add(pathDisplay);
	add(pubDisplay);

	// Middle row, Left --> Right
	// create separate instances of path objects, otherwise
	// LayoutManager just repositions the only pathDisplay
	add(new ResizableIcon(new ImageIcon("path.png")));
	add(new ResizableIcon(new ImageIcon("path.png")));
	add(new ResizableIcon(new ImageIcon("path.png")));

	// Bottom row, Left --> Right
	add(assayDisplay);
	add(new ResizableIcon(new ImageIcon("path.png")));
	add(landOfficeDisplay);

	setPreferredSize(preferredSize);
    }

    /**
     * Initializes all JLabel instance variables to contain appropriate icons
     */
    private void initializeIconLabels() {
	storeDisplay = new ResizableIcon(new ImageIcon("store.png"));
	assayDisplay = new ResizableIcon(new ImageIcon("assay.png"));
	pubDisplay = new ResizableIcon(new ImageIcon("pub.png"));
	landOfficeDisplay = new ResizableIcon(new ImageIcon("landOffice.png"));
	pathDisplay = new ResizableIcon(new ImageIcon("path.png"));
    }

    /**
     * Checks to see whether the given JComponent object has coordinates that
     * would cause any part of it to be located outside the TownPanel.
     * 
     * Pre-condition: do not call this method with null.
     * 
     * @param component
     *            The component to check for full-containment inside the town
     * @return True if the entire component is inside the TownPanel
     */
    public boolean insideTown(JComponent component) {
	Rectangle componentBounds = component.getBounds();
	Rectangle townBounds = this.getBounds();

	return townBounds.contains(componentBounds);
    }

    /**
     * Checks whether the given component overlaps any boundaries of the four
     * shops inside the TownPanel.
     * 
     * @param component
     *            The component we are checking for overlap with shops
     * @return True if the bounds of the component intersect any of the bounds
     *         for any of four the shops inside the town
     */
    public boolean overlapsTownShops(JComponent component) {

	return areOverlapping(storeDisplay, component)
		|| areOverlapping(pubDisplay, component)
		|| areOverlapping(assayDisplay, component)
		|| areOverlapping(landOfficeDisplay, component);
    }

    /**
     * Checks whether a given component is touching/overlapping with the
     * entrance of the pub.
     * 
     * @param component
     *            The component we are checking for overlap with pub entrance
     * @return True if the component is overlapping with pub entrance
     */
    public boolean overlapsPubEntrance(JComponent component) {
	return areOverlapping(pubDisplay, component);
    }

    /**
     * Checks whether a given component is touching/overlapping with the
     * entrance of the store.
     * 
     * @param component
     *            The component we are checking for overlap with store entrance
     * @return True if the component is overlapping with store entrance
     */
    public boolean overlapsStoreEntrance(JComponent component) {
	return areOverlapping(storeDisplay, component);
    }

    /**
     * Checks whether a given component is touching/overlapping with the
     * entrance of the assay office.
     * 
     * @param component
     *            The component we are checking for overlap with assay office
     *            entrance
     * @return True if the component is overlapping with assay office entrance
     */
    public boolean overlapsAssayEntrance(JComponent component) {
	// TODO
	return false;
    }

    /**
     * Checks whether a given component is touching/overlapping with the
     * entrance of the land office.
     * 
     * @param component
     *            The component we are checking for overlap with land office
     *            entrance
     * @return True if the component is overlapping with land office entrance
     */
    public boolean overlapsLandOfficeEntrance(JComponent component) {
	// TODO
	return false;
    }

    /**
     * Determines whether two JComponents are overlapping each other.
     * 
     * @param comp1
     *            First component being checked
     * @param comp2
     *            Second component being checked
     * @return True if the components' bounds overlap (intersect) each other
     */
    private boolean areOverlapping(JComponent comp1, JComponent comp2) {
	Rectangle r1 = comp1.getBounds();
	Rectangle r2 = comp2.getBounds();
	return r1.intersects(r2);
    }

    /**
     * View an empty town panel
     */
    public static void main(String[] args) {
	JFrame jf = new JFrame("Town Panel Test");
	TownPanel tp = new TownPanel();
	jf.getContentPane().add(tp);
	jf.pack();
	jf.setVisible(true);
	jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method returns a new location of a JComponent that would place the
     * component completely in-bounds inside the Town.
     * 
     * Pre-condition: the given component is overlapping one of the town shops
     * (i.e. the component is inside the town, but out-of-bounds)
     * 
     * @param component
     *            The component to calculate a new location for
     * @return Point representing the new in-bounds position of the component
     */
    public Point calcInBoundsLocation(JComponent component) {
	if (areOverlapping(component, storeDisplay))
	    return calcInBoundsLocation(storeDisplay, component);
	else if (areOverlapping(component, landOfficeDisplay))
	    return calcInBoundsLocation(landOfficeDisplay, component);
	else if (areOverlapping(component, pubDisplay))
	    return calcInBoundsLocation(pubDisplay, component);
	else
	    // overlaps assay office
	    return calcInBoundsLocation(assayDisplay, component);
    }

    /**
     * This method returns a new location of a JComponent that would place the
     * component completely in-bounds inside the Town.
     * 
     * Pre-condition: the given component is overlapping the given town shop
     * 
     * @param shop
     *            The shop that the component is overlapping
     * @param component
     *            The component we wish to get a valid location for
     * @return Point representing a new, valid location for the component
     */
    private Point calcInBoundsLocation(JComponent shop, JComponent component) {
	// shop bounds info
	int shopWidth = shop.getWidth();
	int shopHeight = shop.getHeight();
	Point shopLocation = shop.getLocation();
	Rectangle shopBounds = shop.getBounds();

	// component info
	int compWidth = component.getWidth();
	int compHeight = component.getHeight();
	Point topLeft = component.getLocation();
	Point topRight = new Point(topLeft.x + compWidth, topLeft.y);
	Point botLeft = new Point(topLeft.x, topLeft.y + compHeight);
	Point botRight = new Point(topRight.x, botLeft.y);

	// Coordinates of the new Point for the components location (topLeft)
	int newx = topLeft.x;
	int newy = topLeft.y;

	/*
	 * Eight ways that one rectangle can intersect another rectangle.
	 * 
	 * For each case, to obtain a smooth solution when the component is
	 * ultimately moved instantaneously to its new location, we can only
	 * adjust the location in one direction (either x or y).
	 * 
	 * For "corner-cases", the new location is chosen so that the component
	 * will get moved the minimum amount to put it back in bounds.
	 */
	if (shopBounds.contains(topLeft) && shopBounds.contains(topRight)) { // overlaps
									     // bottom
									     // side
	    newy = shopLocation.y + shopHeight;
	} else if (shopBounds.contains(topLeft) && shopBounds.contains(botLeft)) { // overlaps
										   // right
										   // side
	    newx = shopLocation.x + shopWidth;
	} else if (shopBounds.contains(botLeft)
		&& shopBounds.contains(botRight)) { // overlaps top side
	    newy = shopLocation.y - compHeight;
	} else if (shopBounds.contains(topRight)
		&& shopBounds.contains(botRight)) { // overlaps left side
	    newx = shopLocation.x - compWidth;
	} else if (shopBounds.contains(topLeft)) { // overlaps bottom-right
						   // corner
	    int dx = (shopLocation.x + shopWidth) - topLeft.x;
	    int dy = (shopLocation.y + shopHeight) - topLeft.y;
	    if (dx < dy)
		newx = shopLocation.x + shopWidth;
	    else
		newy = shopLocation.y + shopHeight;
	} else if (shopBounds.contains(topRight)) { // overlaps bottom-left
						    // corner
	    int dx = topRight.x - shopLocation.x;
	    int dy = (shopLocation.y + shopHeight) - topRight.y;
	    if (dx < dy)
		newx = shopLocation.x - compWidth;
	    else
		newy = shopLocation.y + shopHeight;
	} else if (shopBounds.contains(botLeft)) { // overlaps top-right corner
	    int dx = (shopLocation.x + shopWidth) - botLeft.x;
	    int dy = botLeft.y - shopLocation.y;
	    if (dx < dy)
		newx = shopLocation.x + shopWidth;
	    else
		newy = shopLocation.y - compHeight;
	} else { // shopBounds.contains(botRight), overlaps top-left corner,
	    int dx = botRight.x - shopLocation.x;
	    int dy = botRight.y - shopLocation.y;
	    if (dx < dy)
		newx = shopLocation.x - compWidth;
	    else
		newy = shopLocation.y - compHeight;
	}
	return new Point(newx, newy);
    }

}
