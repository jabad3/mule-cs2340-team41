package Views;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

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
        add(new ResizableIcon(new ImageIcon("path.png")));
        add(new ResizableIcon(new ImageIcon("path.png")));
        add(new ResizableIcon(new ImageIcon("path.png")));
        
        // Bottom row, Left --> Right
        add(assayDisplay);
        add(new ResizableIcon(new ImageIcon("path.png")));
        add(landOfficeDisplay);
        
        // TODO add contents to panel
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
     * Sets the PlayerPawn object to be displayed.
     * 
     * @param currentPlayer
     */
    public void setPlayerPawn(PlayerPawn newPawn) {
        playerPawn = newPawn;
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
