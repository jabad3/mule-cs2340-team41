package Views;

import java.awt.Dimension;

import javax.swing.JPanel;

import Models.PlayerPawn;

/**
 * Displays the Town.  The Town is made up of four components:
 *      1)  The Mule Store
 *      2)  The Pub
 *      3)  The Land Office
 *      4)  
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
        // TODO add contents to panel
        setPreferredSize(preferredSize);
    }
    
    /**
     * Sets the PlayerPawn object to be displayed.
     * 
     * @param currentPlayer
     */
    public void setPlayerPawn(PlayerPawn newPawn) {
        playerPawn = newPawn;
    }

}
