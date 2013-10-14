package Views;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Displays the Town.  The Town is made up of four components:
 *      1)  The Mule Store
 *      2)  The Pub
 *      3)  The Land Office
 *      4)  The Assay Office
 *      
 * The Town must also be able to display a PlayerPawn icon that the user can
 * move around.  The user interacts with each component of the town by moving
 * the PlayerPawn into the appropriate entry zones for each of the components.
 * 
 * @author Max
 *
 */
public class TownPanel extends JPanel{
	
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
     * @param preferredSize Dimension specifying the preferredSize of the panel
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
        assayDisplay = new  ResizableIcon(new ImageIcon("assay.png"));
        pubDisplay = new ResizableIcon(new ImageIcon("pub.png"));
        landOfficeDisplay = new ResizableIcon(new ImageIcon("landOffice.png"));
        pathDisplay = new ResizableIcon(new ImageIcon("path.png"));
    }

    /**
     * Checks to see whether the given JComponent object has coordinates that
     * would cause any part of it to be located outside the TownPanel.
     * 
     * Pre-condition:  do not call this method with null.
     * 
     * @param component The component to check for full-containment inside the
     * town
     * @return True if the entire component is inside the TownPanel
     */
    public boolean insideTown(JComponent component) {
        Rectangle componentBounds = component.getBounds();
        Rectangle townBounds = this.getBounds();
        
        return townBounds.contains(componentBounds);
    }
    
    /**
     * Checks whether the given component overlaps any boundaries of the
     * four shops inside the TownPanel.
     * 
     * @param component The component we are checking for overlap with shops
     * @return True if the bounds of the component intersect any of the bounds
     * for any of four the shops inside the town
     */
    public boolean overlapsTownShops(JComponent component) {
        Rectangle componentBounds = component.getBounds();
        Rectangle storeBounds = storeDisplay.getBounds();
        Rectangle pubBounds = pubDisplay.getBounds();
        Rectangle assayBounds = assayDisplay.getBounds();
        Rectangle landOfficeBounds = landOfficeDisplay.getBounds();
        
        return componentBounds.intersects(storeBounds)
                || componentBounds.intersects(pubBounds)
                || componentBounds.intersects(assayBounds)
                || componentBounds.intersects(landOfficeBounds);
    }
    
    /**
     * Checks whether a given component is touching/overlapping with
     * the entrance of the pub.
     * 
     * @param component The component we are checking for overlap with pub
     * entrance
     * @return True if the component is overlapping with pub entrance
     */
    public boolean overlapsPubEntrance(JComponent component) {
        // TODO
        return false;
    }
    
    /**
     * Checks whether a given component is touching/overlapping with
     * the entrance of the store.
     * 
     * @param component The component we are checking for overlap with store
     * entrance
     * @return True if the component is overlapping with store entrance
     */
    public boolean overlapsStoreEntrance(JComponent component) {
        // TODO
        return false;
    }
    
    /**
     * Checks whether a given component is touching/overlapping with
     * the entrance of the assay office.
     * 
     * @param component The component we are checking for overlap with assay
     * office entrance
     * @return True if the component is overlapping with assay office entrance
     */
    public boolean overlapsAssayEntrance(JComponent component) {
        // TODO
        return false;
    }
    
    /**
     * Checks whether a given component is touching/overlapping with
     * the entrance of the land office.
     * 
     * @param component The component we are checking for overlap with land
     * office entrance
     * @return True if the component is overlapping with land office entrance
     */
    public boolean overlapsLandOfficeEntrance(JComponent component) {
        // TODO
        return false;
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

}
