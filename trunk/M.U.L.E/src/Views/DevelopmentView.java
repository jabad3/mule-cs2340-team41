package Views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import Models.MapFactory;


/**
 * The DevelopmentView class allows the user to move their player around the
 * map and interact with the different shops in the town.
 * 
 * It should allow the user to move a PlayerPawn around the map and the town.
 * The user should also be able to interact with the different shops inside the
 * town.
 * 
 * @author jabad3
 *
 */
public class DevelopmentView extends JLayeredPane {
    
    /** Label to display the current Player's name. */
    JLabel playerNameLabel;
    
    /** Displays the land plots to the user. */
    MapPanel mapPanel;
    
    /** Displays the town to the user. */
    TownPanel townPanel;
    
    /** A JPanel with card layout to allow easy swapping between town and map. */
    JPanel cardPanel;
    
    /** The CardLayout that is the layout manager of the cardPanel. */
    CardLayout cardLayout;
    
    /** The current PlayerPawn object to be displayed to the user. */
    PlayerPawn currentPawn;
    
    MuleTimerPanel muleTimerPanel;
    
    /**
     * Create the Development View.
     * 
     * @param mapPanel The map to display to the user
     * @param townPanel The town to display to the user
     * @param playerPawn The first pawn to display to the user
     */
    public DevelopmentView(MapPanel mapPanel, TownPanel townPanel, PlayerPawn playerPawn, MuleTimerPanel muleTimerPanel) {
        playerNameLabel = new JLabel("Whose turn is it?");
        this.mapPanel = mapPanel;
        this.townPanel = townPanel;
        this.cardPanel = new JPanel();
        this.currentPawn = playerPawn;
        this.muleTimerPanel = muleTimerPanel;	//not functional yet
        
        // config card panel
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        cardPanel.add(this.mapPanel, "mapPanel");
        cardPanel.add(this.townPanel, "townPanel");
        cardPanel.add(this.muleTimerPanel, "muleTimer");
        
        // Because JLayeredPane layout manager is null, manually set size,
        // location of components to add to it
        cardPanel.setSize(mapPanel.getPreferredSize());
        cardPanel.setLocation(0, 0);
        
        playerNameLabel.setSize(playerNameLabel.getPreferredSize());
        playerNameLabel.setLocation(0, this.mapPanel.getHeight() - 100);
        
        currentPawn.setSize(currentPawn.getPreferredSize());
        currentPawn.setLocation(100, 50);
        
        muleTimerPanel.setSize(muleTimerPanel.getPreferredSize());
        muleTimerPanel.setLocation(900,0);
        
        this.add(cardPanel, new Integer(0));  // place map/town underneath everything
        this.add(playerNameLabel, new Integer(1));
        this.add(currentPawn, new Integer(2));
        this.add(muleTimerPanel, new Integer(3));

        this.setPreferredSize(mapPanel.getPreferredSize());
    }
    
    /**
     * Animation of the player pawn and updates to the MULE timer occurs here.
     * This method handles any swapping of the map/town that is needed.
     * 
     * This method should be called on a frequently enough so that the
     * animation appears smooth.
     * 
     * @param duration The duration of the player's turn in milliseconds.
     */
    public void update() {
        // TODO
        // call pawn.move if new location is valid
        // swap town/map panel if needed
        // update the MULE timer
    	if(currentPawn.getLocation().getX() >= 280 && currentPawn.getLocation().getX() <= 350
    			&& currentPawn.getLocation().getY() >= 250 && currentPawn.getLocation().getY() <= 350){
    		showTown();
    	}
    }
    
    /**
     * Set the current Player's name
     * 
     * @param playerName The new name to display
     */
    public void setCurrentPlayerName(String playerName) {
        playerNameLabel.setText(playerName);
    }
    
    /**
     * Set the current pawn to a new pawn.
     * 
     * @param newPawn The new PlayerPawn to be displayed
     */
    public void setCurrentPawn(PlayerPawn newPawn) {
        currentPawn = newPawn;
    }
    
    /**
     * Shows the map and positions the current pawn to its initial position in the map.
     */
    public void resetView() {
        // TODO
    }
    
    /**
     * Display the map to the user.
     */
    public void showMap() {
        cardLayout.show(cardPanel, "mapPanel");
    }
    
    /**
     * Display the town to the user.
     */
    public void showTown() {
        cardLayout.show(cardPanel, "townPanel");
    }
 
    /**
     * To test the development view.
     * 
     * @param args
     */
    public static void main(String[] args) {
        JFrame jf = new JFrame("Test Dev't View");
        MapPanel mapPanel = new MapPanel(MapFactory.buildMap("Default"), null);
        TownPanel townPanel = new TownPanel();
        PlayerPawnStateful pawn = new PlayerPawnStateful(new ImageIcon("Buzzite.png"));
        MuleTimerPanel muleTimerPanel = new MuleTimerPanel(3000);
        
        DevelopmentView dv = new DevelopmentView(mapPanel, townPanel, pawn, muleTimerPanel);
        
        pawn.listen(dv);

        
        jf.getContentPane().add(dv);
        jf.pack();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
    
}

