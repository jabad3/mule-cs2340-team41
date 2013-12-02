package Stages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Models.GameModel;
import Models.LandPlot;
import Models.Map;
import Models.Mule;
import Models.Player;
import Models.Resource;
import Views.DevelopmentView;
import Views.MapPanel;
import Views.MuleTimerListener;
import Views.MuleTimerPanel;
import Views.PlayerPawn;
import Views.ShopEntryListener;
import Views.StorePanel;
import Views.StoreView;
import Views.TownPanel;

/**
 * Controller for Development Stage
 */

/**
 * DevelopmentStage updates the View and the Model during the Development
 * part of the game. During Development, users are allowed to interact with
 * the town and the map by visiting town stores and placing mules on land plots.
 *
 * @author jabad3
 *
 */
public class DevelopmentStage extends Stage implements MuleTimerListener, ShopEntryListener {

    /** The view to be controlled by this stage. */
    private DevelopmentView myView;
    
    /** The map panel to be displayed in the view. */
    private MapPanel mapPanel;
    
    /** The town panel to be displayed in the view. */
    private TownPanel townPanel;
    
    /** The mule timer panel to be displayed in the view. */
    private MuleTimerPanel muleTimerPanel;
    
    /** Ordered list of players.  Player at index 0 goes first. */
    private List<Player> playerList;
    
    /** The player whose turn it currently is. */
    private Player currentPlayer;
    
    /** The index in the playerList of the player whose turn it currently is. */
    private int currentPlayerIndex;
    
    /** The store dialog that is currently open, if any */
    private JDialog storeDialog;
	
    /** Decides when random events occur and executes them. */
    private RandomEventService randService;
	
	/**
	 *	Creates Development Stage object.
	 *
	 * @param mainFrame The JFrame to hold the View
	 * @param gameModel The game model used throughout the game
	 */
	public DevelopmentStage(JFrame mainFrame, GameModel gameModel) {
		super(mainFrame, gameModel);
		randService = new RandomEventService();
	}

	/* (non-Javadoc)
	 * @see Stage#start()
	 */
	@Override
	public void start() {
        Map map = gameModel.getMap();
		mapPanel = new MapPanel(map, null);
		townPanel = new TownPanel();
		playerList = gameModel.getPlayerList();
		muleTimerPanel = new MuleTimerPanel();
		muleTimerPanel.addMuleTimerListener(this);
		
		myView = new DevelopmentView(mapPanel, townPanel, new PlayerPawn(new ImageIcon("buzzite.png")), muleTimerPanel); // temporary
		myView.addShopEntryListener(this);
		displayView(myView);
		
		currentPlayerIndex = 0;
		beginCurrentPlayerTurn();
		
        myView.requestFocus();
	}
	
	/**
	 * Begins the current player's turn by...
	 *   1) determining the current player
	 *   2) updating the view to display the correct pawn
	 *   3) reseting the view to put the player in the correct spot with
	 *      correct amount of time remaining
	 */
	private void beginCurrentPlayerTurn() {
	    currentPlayer = playerList.get(currentPlayerIndex);
	    randService.startService(currentPlayer, gameModel.getCurrentRound(), gameModel.isInLastPlace(currentPlayer), mainFrame);
	    myView.setCurrentPawnImageIcon(currentPlayer.getIcon());
	    myView.updateCurrentPlayerInfo(currentPlayer);	    
	    myView.beginPlayerTurn(gameModel.calculateTurnTime(currentPlayer));
	}
	
	/**
	 * Advance the stage one turn.  Either start the next player's turn, or,
	 * if there is no next player, start the next stage.
	 */
	private void advanceOneTurn() {
		if(storeDialog != null)
		{
			storeDialog.dispose();
		}
		
		// mule runs away if not placed
		if (currentPlayer.hasMule())
		    muleRunsAway();
		
		currentPlayerIndex++;
        if (currentPlayerIndex >= playerList.size())
            goNextStage();
        else
            beginCurrentPlayerTurn();
	}

    @Override
    /**
     * Called when a player's turn is up
     */
    public void muleTimerFinished() {
        myView.endPlayerTurn();
        advanceOneTurn();
    }

    @Override
    public void enteredStore() {
    	storeDialog = new JDialog();
    	StorePanel storepanel = new StorePanel(gameModel.getStore(), currentPlayer, new ActionListener() {
    	    public void actionPerformed(ActionEvent e)
    	    {
    	    	storeDialog.dispose();
    	    }
    	});
    	StoreView storeview = new StoreView(storepanel);
    	storeDialog.setContentPane(storeview);
    	storeDialog.pack();
    	storeDialog.setVisible(true);
    	
    	myView.getCurrentPawn().resetStates();
    	
    }

    @Override
    public void enteredLandOffice() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void enteredPub() {        
        myView.endPlayerTurn();  // stop timer, pawn movement
        int pubPayment = calculatePubPayment();  
        currentPlayer.addResource(Resource.MONEY, pubPayment);
        myView.displayMessageDialog("You won $" + pubPayment + " from the Pub."
                                    + "\nYour turn is now over.");
        advanceOneTurn();
    }
    
    /**
     * Calculates the pub payment according to the (CS 2340) rules of the game.
     * Pub payment depends on the player's time remaining as well as the
     * current round of the game.
     * 
     * @return The money amount that the pub will give to the Player
     */
	private int calculatePubPayment() {
    	Random rand = new Random();
    	int roundBonus = 0;
    	int timeBonus = 0;
    	int totalPubPayment = 0;
    	double secondsRemaining = muleTimerPanel.getRemainingTime() / 1000.0;
    	
    	if(gameModel.getCurrentRound() < 4)
    		roundBonus = 50;
    	else if(gameModel.getCurrentRound() < 8 && gameModel.getCurrentRound() >= 4)
    		roundBonus = 100;
    	else if(gameModel.getCurrentRound() < 12 && gameModel.getCurrentRound() >= 8)
    		roundBonus = 150;
    	else if(gameModel.getCurrentRound() == 12)
    		roundBonus = 200;
    	else
    		roundBonus = 0;
    		
    	if(secondsRemaining >= 37 && secondsRemaining <= 50)
    		timeBonus = 200;
    	else if(secondsRemaining < 37 && secondsRemaining >= 25)
    		timeBonus = 150;
    	else if(secondsRemaining < 25 && secondsRemaining >= 12)
    		timeBonus = 100;
    	else if(secondsRemaining < 12)
    		timeBonus = 50;
    		
    	totalPubPayment = roundBonus * (rand.nextInt(timeBonus + 1));
    	if(totalPubPayment <= 250)
    		return totalPubPayment;
    	else
    		return 250;
    }

    @Override
    public void enteredAssayOffice() {
        // TODO
        // Only if crystite resource is added
        
    }

    @Override
    public void enteredLandPlot(LandPlot plot) {
        if (currentPlayer.hasMule()) {
            if (plot.isOwnedBy(currentPlayer) && !plot.hasMule())
                swapMules(currentPlayer, plot);  // place mule on plot
            else
                muleRunsAway();
        } else if (plot.isOwnedBy(currentPlayer))
            swapMules(currentPlayer, plot);  // take mule from plot
        mapPanel.repaint();
        System.out.println("Entered LandPlot");
    }
    
    /**
     * Swaps the mules between a Player and a LandPlot.
     * 
     * Pre-condition:  Player is the owner of the LandPlot.
     * 
     * @param player The player that the land plot gives its mule to
     * @param plot The land plot that the player gives its mule to
     */
    private void swapMules(Player player, LandPlot plot) {
        Mule playerMule = player.getMule();
        Mule plotMule = plot.getMule();
        player.setMule(plotMule);
        plot.setMule(playerMule);
    }
    
    /**
     * Causes the current player's mule to run away by setting its mule
     * to null.
     */
    private void muleRunsAway() {
        currentPlayer.setMule(null);
        myView.displayMessageDialog("Oh no!  Your Mule has run away!");
    }

}
