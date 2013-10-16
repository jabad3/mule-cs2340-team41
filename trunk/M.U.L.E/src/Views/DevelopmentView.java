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
    private JLabel playerNameLabel;
    
    /** Displays the land plots to the user. */
    private MapPanel mapPanel;
    
    /** Displays the town to the user. */
    private TownPanel townPanel;
    
    /** A JPanel with card layout to allow easy swapping between town and map. */
    private JPanel cardPanel;
    
    /** The CardLayout that is the layout manager of the cardPanel. */
    private CardLayout cardLayout;
    
    /** The current PlayerPawn object to be displayed to the user. */
    private PlayerPawn currentPawn;
    
    /** Displays a bar representing the time left in the current turn. */
    private MuleTimerPanel muleTimerPanel;
    
    /**
     * Create the Development View.
     * 
     * @param mapPanel The map to display to the user
     * @param townPanel The town to display to the user
     * @param playerPawn The first pawn to display to the user
     * @param muleTimerPanel The timer bar to display to the user
     */
    public DevelopmentView(MapPanel mapPanel, TownPanel townPanel, PlayerPawn playerPawn, MuleTimerPanel muleTimerPanel) {
        playerNameLabel = new JLabel("Whose turn is it?");
        this.mapPanel = mapPanel;
        this.townPanel = townPanel;
        this.cardPanel = new JPanel();
        this.currentPawn = playerPawn;
        this.muleTimerPanel = muleTimerPanel;
        
        // config card panel
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        cardPanel.add(this.mapPanel, "mapPanel");
        cardPanel.add(this.townPanel, "townPanel");
        //cardPanel.add(this.muleTimerPanel, "muleTimer");
        
        // place cardPanel and muleTimerPanel in same container so that
        // a layout manager positions them correctly relative to one another
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(cardPanel, BorderLayout.CENTER);
        mainPanel.add(this.muleTimerPanel, BorderLayout.EAST);
        
        
        // Because JLayeredPane layout manager is null, manually set size,
        // location of components to add to it
        mainPanel.setSize(new Dimension(600, 400));
        mainPanel.setLocation(0, 0);
        
        playerNameLabel.setSize(playerNameLabel.getPreferredSize());
        playerNameLabel.setLocation(0, mainPanel.getHeight() - 100);
        
        currentPawn.setSize(currentPawn.getPreferredSize());
        currentPawn.setLocation(100, 50);

        // Add to JLayeredPane.  Lower numbers are drawn behind high numbers.
        this.add(mainPanel, new Integer(0));  // map is behind everything
        this.add(playerNameLabel, new Integer(1));
        this.add(currentPawn, new Integer(2));

        this.setPreferredSize(mainPanel.getSize());
    }
    
    /**
     * This method begins a player's turn by allowing them to move their pawn
     * and interact with the store and town until they are out of time.
     */
    public void beginPlayerTurn() {
        final int period = 16;  // call task.run() every 16 ms
        
        currentPawn.enableMovement(this);
        
        java.util.Timer timer = new java.util.Timer();
        java.util.TimerTask task = new java.util.TimerTask() {
                public void run() {
                    if (!muleTimerPanel.isFinished()) {
                        animateView(period);
                    } else {
                        this.cancel();
                    }
                }
            };
        timer.schedule(task, 0, period);
    }
    
    /**
     * Animation of the player pawn and updates to the MULE timer occurs here.
     * This method handles any swapping of the map/town that is needed.
     * 
     * This method should be called on a frequently enough so that the
     * animation appears smooth.
     * 
     * @param period The number of milliseconds between each call of the update
     * method.  This is needed so that the mule timer panel is updated at the
     * correct rate.
     */
    public void animateView(int period) {
        // TODO
        // call pawn.move if new location is valid
        // swap town/map panel if needed
        // update the MULE timer

        currentPawn.move();
        
        if (mapPanel.isVisible())
            performMapCollisionEvents();   
        else
            performTownCollisionEvents();
            
        muleTimerPanel.decrement(period);
        muleTimerPanel.repaint();
        setFocusable(true);  // if not called, then muleTimerPanel.repaint() messes up focus
    }
    
    /**
     * In the map, take action if the current pawn...
     *   1) Collides with the town, or
     *   2) Collides with the map border
     */
    private void performMapCollisionEvents() {
        
        if (!mapPanel.insideMap(currentPawn)) {
            // TODO
            // means the moved pawn is out of bounds, so either put it back
            // in bounds, or maybe we need to update the pawns coordinates
            // in a slightly different way
        }
                
        if (mapPanel.overlapsTown(currentPawn))
            showTown();
    }
    
    /**
     * In the map, take action if the current pawn..
     *   1) Collides with the town border,
     *   2) Collides with an entrance to a shop, or
     *   3) Collides with a shop border that is not an entrance
     */
    private void performTownCollisionEvents() {
        if (!townPanel.insideTown(currentPawn))
            showMap();
        
        // TODO
        // check if pawn enters a store
        
        // TODO
        // keep pawn from going out of bounds while still inside the town
        // like above, we probably can move it back in bounds, or update
        // the coordinates in a different way
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
        final MapPanel mapPanel = new MapPanel(MapFactory.buildMap("Default"), null);
        final TownPanel townPanel = new TownPanel();
        final PlayerPawn pawn = new PlayerPawn(new ImageIcon("buzzite.png"));
        final MuleTimerPanel muleTimerPanel = new MuleTimerPanel(25000);
        
        final DevelopmentView dv = new DevelopmentView(mapPanel, townPanel, pawn, muleTimerPanel);
        
        

        
        jf.getContentPane().add(dv);
        jf.pack();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        
        dv.beginPlayerTurn();
        
        
    }
    
}

