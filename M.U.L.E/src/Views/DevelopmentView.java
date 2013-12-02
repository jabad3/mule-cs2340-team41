package Views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Models.LandPlot;
import Models.MapFactory;
import Models.Player;


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
    
    /** Keeps track of all ShopEntryListeners listening to the view. */
    private Collection<ShopEntryListener> shopEntryListeners;
    
    /** Holds the previous state of the action key during animation. */
    private boolean previousActionKeyState;
    
    /** Holds the current state of the action key during animation. */
    private boolean currentActionKeyState;
    
    /** Displays information about the current player. */
    private PlayerInfoPanel playerInfoPanel;
    
    /** Constant location of east exit. */
    private static final Point EAST_LOCATION = new Point(333,180);
    
    /** Constant location of west exit. */
    private static final Point WEST_LOCATION = new Point(213,180);
    
    /** Constant location of north exit. */
    private static final Point NORTH_LOCATION = new Point(283,100);
    
    /** Constant location of south exit. */
    private static final Point SOUTH_LOCATION = new Point(283,250);
    
    
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
        playerInfoPanel = new PlayerInfoPanel();
        playerNameLabel = new JLabel("Whose turn is it?");
        cardPanel = new JPanel();
        setCurrentPawn(playerPawn);
        shopEntryListeners = new ArrayList<>();
        
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
        
        getCurrentPawn().setSize(getCurrentPawn().getPreferredSize());
        getCurrentPawn().setLocation(100, 50);

        // Add to JLayeredPane.  Lower numbers are drawn behind high numbers.
        layeredPane.add(cardPanel, new Integer(0));  // map is behind pawn
        layeredPane.add(getCurrentPawn(), new Integer(1));
        layeredPane.setPreferredSize(cardPanel.getSize());
        
        // Add all components to the view in a border-layout
        this.setLayout(new BorderLayout());
        this.add(layeredPane, BorderLayout.CENTER);
        this.add(this.muleTimerPanel, BorderLayout.EAST);
        this.add(playerInfoPanel, BorderLayout.NORTH);
        
        animationTimer = new Timer();
    }
    
    /**
     * Updates the view to display player info for the given player.
     * This information includes:  1) Name, and 2) Inventory (Money, Food,
     * Energy, Ore) information
     * 
     * @param player The player to display the info for
     */
    public void updateCurrentPlayerInfo(Player player) {
        playerInfoPanel.displayInfoForNewPlayer(player);
    }
    
    /**
     * This method begins a player's turn by allowing them to move their pawn
     * and interact with the store and town until they are out of time.
     * 
     * @param duration The duration of the turn in milliseconds
     */
    public void beginPlayerTurn(int duration) {
    	showTown();
        int period = 16;  // call task.run() every 16 ms
        getCurrentPawn().enableMovement(this);
        getCurrentPawn().setLocation(mapPanel.getX() + mapPanel.getWidth()/2, mapPanel.getY() + mapPanel.getHeight()/2);
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
        animationTimer.cancel();  	// view animation will stop
        getCurrentPawn().resetStates();  // turn off all key states
    }
    
    /**
     * Animation of the player pawn and updates to the MULE timer occurs here.
     * This method handles any swapping of the map/town that is needed.
     * 
     * This method should be called on a frequently enough so that the
     * animation appears smooth.
     */
    public void animateView() {
        updateActionKeyState();
        getCurrentPawn().move();
        playerInfoPanel.refresh();
        
        if (mapPanel.isVisible())
            performMapCollisionEvents();   
        else
            performTownCollisionEvents();
            
        muleTimerPanel.decrement();
        muleTimerPanel.repaint();
        setFocusable(true);  // if not called, then muleTimerPanel.repaint() messes up focus
    }
    
    /**
     * Updates previousActionKeyState and currentActionKeyState according
     * to what the user has pressed at this point in time.
     */
    private void updateActionKeyState() {
        previousActionKeyState = currentActionKeyState;
        currentActionKeyState = getCurrentPawn().actionKey;
    }

    /** Moves the pawn to be inside the given panel
     * 
     * @param toPanel The panel to contain the pawn
     */
    
    private void constrainPawn(JPanel toPanel) {
    	Point pawnPos = getCurrentPawn().getLocation();
    	Dimension pawnSize = getCurrentPawn().getSize();
        Dimension panelSize = this.mapPanel.getSize();
        Point panelOrigin = this.townPanel.getLocation();
        
        if(pawnPos.x < panelOrigin.x) {
        	pawnPos.x = panelOrigin.x;
        }
        else if(pawnPos.x+pawnSize.width > panelOrigin.x+panelSize.width) {
        	pawnPos.x = panelOrigin.x+panelSize.width-pawnSize.width;
        }
        
        if(pawnPos.y < panelOrigin.y) {
        	pawnPos.y = panelOrigin.y;
        }
        else if(pawnPos.y+pawnSize.height > panelOrigin.y+panelSize.height) {
        	pawnPos.y = panelOrigin.y+panelSize.height-pawnSize.height;
        }
        
        getCurrentPawn().setLocation(pawnPos);
    }
    
    /**
     * In the map, take action if the current pawn...
     *   1) Collides with the town, or
     *   2) Collides with the map border
     */
    private void performMapCollisionEvents() {
        if (!mapPanel.insideMap(getCurrentPawn())) {
        	constrainPawn(mapPanel);
        } else if (mapPanel.overlapsTown(getCurrentPawn())) {
            showTown();
        } else if (actionKeyWasHit()) {
            Point currentLocation = getCurrentPawn().getLocation();
            int centerX = currentLocation.x + getCurrentPawn().getWidth() / 2;
            int centerY = currentLocation.y + getCurrentPawn().getHeight() / 2;
            Point centerOfPawn = new Point(centerX, centerY);
            LandPlot enteredPlot = mapPanel.getLandPlotAt(centerOfPawn);
            sendEnteredLandPlotNotifications(enteredPlot);
            getCurrentPawn().resetStates();
        }
    }

    /**
     * Checks whether or not the user has just hit the action key.
     * A hit key is defined as a key that is pressed now that was previously
     * unpressed.
     * 
     * @return True if the action key has just been hit
     */
    private boolean actionKeyWasHit() {
        return (!previousActionKeyState && currentActionKeyState);
    }

    /**
     * In the map, take action if the current pawn..
     *   1) Collides with the town border,
     *   2) Collides with an entrance to a shop, or
     *   3) Collides with a shop border that is not an entrance
     */
    private void performTownCollisionEvents() {
        if (!townPanel.insideTown(getCurrentPawn())) {
        	if(!townPanel.overlapsTownShops(getCurrentPawn())) {
        		if(getCurrentPawn().getLocation().x < 60) {
        			getCurrentPawn().setLocation(WEST_LOCATION);
        		}
        		else if(getCurrentPawn().getLocation().x > townPanel.getWidth() - 60) {
        			getCurrentPawn().setLocation(EAST_LOCATION);
        		}
        		else if(getCurrentPawn().getLocation().y < 60) {
        			getCurrentPawn().setLocation(NORTH_LOCATION);
        		}
        		else {
        			getCurrentPawn().setLocation(SOUTH_LOCATION);
        		}
        		showMap();
        	}
        	else {
        		constrainPawn(townPanel);
        	}
        }
        
        if(townPanel.overlapsPubEntrance(getCurrentPawn()) && getCurrentPawn().actionKey)
        {
            sendEnteredPubNotifications();
        }
        
        if(townPanel.overlapsStoreEntrance(getCurrentPawn()) && actionKeyWasHit())
        {
            sendEnteredStoreNotifications();
        }        
    }
    
    /**
     * Notify appropriate listeners that the user interacted with the pub.
     */
    private void sendEnteredPubNotifications() {
        for (ShopEntryListener sel: shopEntryListeners)
            sel.enteredPub();
    }
    
    /**
     * Notify appropriate listeners that the user interacted with a land plot.
     * 
     * @param enteredPlot The LandPlot that the user interacted with
     */
    private void sendEnteredLandPlotNotifications(LandPlot enteredPlot) {
        for (ShopEntryListener sel: shopEntryListeners)
            sel.enteredLandPlot(enteredPlot);
        
    }

    private void sendEnteredStoreNotifications() {
        for (ShopEntryListener sel: shopEntryListeners)
            sel.enteredStore();
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
     * @param newImage The new image to be displayed in the current PlayerPawn
     */
    public void setCurrentPawnImageIcon(ImageIcon newImage) {
        getCurrentPawn().setIcon(newImage);
        getCurrentPawn().setSize(new Dimension(newImage.getIconWidth(), newImage.getIconHeight()));
    }
    
    /**
     * Display the map to the user.
     */
    private void showMap() {
        cardLayout.show(cardPanel, "mapPanel");
    }
    
    /**
     * Display the town to the user.
     */
    private void showTown() {
        cardLayout.show(cardPanel, "townPanel");
    }
    
    /**
     * Adds the ShopEntryListener to the view.
     * 
     * The ShopEntryListener will be notified when the user interacts with
     * one of the shops during development.
     * 
     * @param sel The ShopEntryListener to add to the view
     */
    public void addShopEntryListener(ShopEntryListener sel) {
        shopEntryListeners.add(sel);
    }

    /**
     * Displays a simple dialog box to the user containing a message.
     * This message has no effect other than relaying a message to the user.
     * 
     * @param message The message to display to the user
     */
    public void displayMessageDialog(String message) {
        JOptionPane.showInternalMessageDialog(this, message);
    }

	public PlayerPawn getCurrentPawn() {
		return currentPawn;
	}

	public void setCurrentPawn(PlayerPawn currentPawn) {
		this.currentPawn = currentPawn;
	}
 
    /**
     * To test the development view.
     * 
     * @param args
     */
//    public static void main(String[] args) {
//        JFrame jf = new JFrame("Test Dev't View");
//        final MapPanel mapPanel = new MapPanel(MapFactory.buildMap("Default"), null);
//        final TownPanel townPanel = new TownPanel();
//        final PlayerPawn pawn = new PlayerPawn(new ImageIcon("buzzite.png"));
//        final MuleTimerPanel muleTimerPanel = new MuleTimerPanel(25000);
//        
//        final DevelopmentView dv = new DevelopmentView(mapPanel, townPanel, pawn, muleTimerPanel);
//        
//        jf.getContentPane().add(dv);
//        jf.pack();
//        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        jf.setVisible(true);
//        
//        dv.beginPlayerTurn(25000);
//    }
}
