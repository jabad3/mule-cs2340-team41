package Views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

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
public class DevelopmentView extends JPanel {
    
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
    
    /** The timer object used to animate the view. */
    private Timer animationTimer;
    
    /**
     * Create the Development View.
     * 
     * @param mapPanel The map to display to the user
     * @param townPanel The town to display to the user
     * @param playerPawn The first pawn to display to the user
     * @param muleTimerPanel The timer bar to display to the user
     */
    public DevelopmentView(MapPanel mapPanel, TownPanel townPanel, PlayerPawn playerPawn, MuleTimerPanel muleTimerPanel) {
        this.mapPanel = mapPanel;
        this.townPanel = townPanel;
        this.muleTimerPanel = muleTimerPanel;
        playerNameLabel = new JLabel("Whose turn is it?");
        cardPanel = new JPanel();
        currentPawn = playerPawn;
        
        // configure card panel
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        cardPanel.add(this.mapPanel, "mapPanel");
        cardPanel.add(this.townPanel, "townPanel");
        
        // JLayeredPane allows pawn to be shown on top of map/own
        // Add map/town and pawn together so that they share the same
        // coordinate system
        JLayeredPane layeredPane = new JLayeredPane();
        
        
        // Because JLayeredPane layout manager is null, manually set size,
        // location of components to add to it
        cardPanel.setSize(new Dimension(600, 400));
        cardPanel.setLocation(0, 0);
        
        currentPawn.setSize(currentPawn.getPreferredSize());
        currentPawn.setLocation(100, 50);

        // Add to JLayeredPane.  Lower numbers are drawn behind high numbers.
        layeredPane.add(cardPanel, new Integer(0));  // map is behind pawn
        layeredPane.add(currentPawn, new Integer(1));
        layeredPane.setPreferredSize(cardPanel.getSize());
        
        // Add all components to the view in a border-layout
        this.setLayout(new BorderLayout());
        this.add(layeredPane, BorderLayout.CENTER);
        this.add(this.muleTimerPanel, BorderLayout.EAST);
        this.add(playerNameLabel, BorderLayout.NORTH);
        
        animationTimer = new Timer();
    }
    
    /**
     * This method begins a player's turn by allowing them to move their pawn
     * and interact with the store and town until they are out of time.
     * 
     * @param duration The duration of the turn in milliseconds
     */
    public void beginPlayerTurn(int duration) {
        int period = 16;  // call task.run() every 16 ms
        
        currentPawn.enableMovement(this);
        currentPawn.setLocation(mapPanel.getX() + mapPanel.getWidth()/2, mapPanel.getY() + mapPanel.getHeight()/2);
        muleTimerPanel.reset(duration);
        muleTimerPanel.setDefaultDecrementAmount(period);
        
        TimerTask task = new TimerTask() {
                public void run() {
                    animateView();
                }
            };
        animationTimer = new Timer();
        animationTimer.schedule(task, 0, period);
    }
    
    /**
     * This method ends a player's turn by preventer further interaction
     * with the View.
     */
    public void endPlayerTurn() {
        animationTimer.cancel();  // view animation will stop
    }
    
    /**
     * Animation of the player pawn and updates to the MULE timer occurs here.
     * This method handles any swapping of the map/town that is needed.
     * 
     * This method should be called on a frequently enough so that the
     * animation appears smooth.
     */
    public void animateView() {
        // TODO
        // call pawn.move if new location is valid
        // swap town/map panel if needed
        // update the MULE timer

        currentPawn.move();
        
        if (mapPanel.isVisible())
            performMapCollisionEvents();   
        else
            performTownCollisionEvents();
            
        muleTimerPanel.decrement();
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
        	
        	Point pawnPos = currentPawn.getLocation();
        	Dimension pawnSize = currentPawn.getSize();
            Dimension mapPanelSize = this.mapPanel.getSize();
            Point cardPanelOrigin = this.cardPanel.getLocation();
            Point mapPanelOrigin = this.mapPanel.getLocation();
            Point mapPanelRealOrigin = mapPanelOrigin;
            mapPanelRealOrigin.translate(cardPanelOrigin.x, cardPanelOrigin.y);
            if(pawnPos.x < mapPanelOrigin.x)
            {
            	pawnPos.x = mapPanelOrigin.x;
            }
            else if(pawnPos.x+pawnSize.width > mapPanelOrigin.x+mapPanelSize.width)
            {
            	pawnPos.x = mapPanelOrigin.x+mapPanelSize.width-pawnSize.width;
            }
            if(pawnPos.y < mapPanelOrigin.y)
            {
            	pawnPos.y = mapPanelOrigin.y;
            }
            else if(pawnPos.y+pawnSize.height > mapPanelOrigin.y+mapPanelSize.height)
            {
            	pawnPos.y = mapPanelOrigin.y+mapPanelSize.height-pawnSize.height;
            }
            currentPawn.setLocation(pawnPos);
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
        // check if pawn is close enough to interact with store
        // AND attempted to interact with store (action button)
        
        // TODO
        // keep pawn from going out of bounds while still inside the town
        // like above, we probably can move it back in bounds, or update
        // the coordinates in a different way
        if (townPanel.overlapsTownShops(currentPawn)) {
            Point newPawnLocation = townPanel.calcInBoundsLocation(currentPawn);
            currentPawn.setLocation(newPawnLocation);
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
        currentPawn.setImage(newPawn.getImage());
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
        
        dv.beginPlayerTurn(25000);
    }
    
}

