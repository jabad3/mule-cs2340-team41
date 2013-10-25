package Stages;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import Models.GameModel;
import Models.LandPlot;
import Models.Map;
import Models.Player;
import Models.Resource;
import Views.DevelopmentView;
import Views.MapPanel;
import Views.MuleTimerListener;
import Views.MuleTimerPanel;
import Views.PlayerPawn;
import Views.ShopEntryListener;
import Views.TownPanel;

/**
 * Controller for Development Stage
 */

/**
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
    	
	
	
	/**
	 *	Creates Development Stage object.
	 *
	 * @param mainFrame The JFrame to hold the View
	 * @param gameModel The game model used throughout the game
	 */
	public DevelopmentStage(JFrame mainFrame, GameModel gameModel) {
		super(mainFrame, gameModel);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see Stage#start()
	 */
	@Override
	public void start() {
		// TODO Auto-generated method stub
		System.out.println("Starting Development Stage");
        Map map = gameModel.getMap();
		mapPanel = new MapPanel(map, null);
		townPanel = new TownPanel();
		playerList = gameModel.getSortedPlayerList();
		muleTimerPanel = new MuleTimerPanel();
		muleTimerPanel.addMuleTimerListener(this);
		
		myView = new DevelopmentView(mapPanel, townPanel, new PlayerPawn(new ImageIcon("buzzite.png")), muleTimerPanel); // temporary
		myView.addShopEntryListener(this);
		displayView(myView);
		
		currentPlayerIndex = 0;
		beginCurrentPlayerTurn();
	}
	
	
	private void beginCurrentPlayerTurn() {
	    currentPlayer = playerList.get(currentPlayerIndex);
	    PlayerPawn currentPawn = new PlayerPawn(currentPlayer.getIcon());
	    myView.setCurrentPawn(currentPawn);
	    myView.setCurrentPlayerName(currentPlayer.getName());
	    myView.beginPlayerTurn(gameModel.calculateTurnTime(currentPlayer));
	    myView.showTown();
	}
	
	/**
	 * Advance the stage one turn.  Either start the next player's turn, or,
	 * if there is no next player, start the next stage.
	 */
	private void advanceOneTurn() {
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
        // TODO
        // User will buy/sell a mule to the store, or error if not enough money
        // Update View to indicate pawn has/does not have a mule
    	
    	myView.displayMessageDialog("Tims panel here");    	
    	//once finished buying from store, reset location to coordinates outside of store
    	myView.currentPawn.setLocation(new Point(283,260));
    	
    	
//    	if(currentPlayer.getMuleHolder() == true) {
//		myView.displayMessageDialog("Already holding mule!");
//		return;
//	}
    	
    }

    @Override
    public void enteredLandOffice() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void enteredPub() {
        // TODO
        // User will be paid an appropriate amount of money, then their turn ends
        // Update View to indicate next player's turn
        
        myView.endPlayerTurn();  // stop timer, pawn movement
        int pubPayment = calculatePubPayment();  
        currentPlayer.addResource(Resource.MONEY, pubPayment);
        myView.displayMessageDialog("You won $" + pubPayment + " from the Pub."
                                    + "\nYour turn is now over.");
        advanceOneTurn();
    }
    
    @SuppressWarnings("unused")
	private int calculatePubPayment() {
    	Random rand = new Random();
    	int roundBonus = 0;
    	int timeBonus = 0;
    	int totalPubPayment = 0;
    	
    	if(gameModel.getCurrentRound() < 4)
    		roundBonus = 50;
    	else if(gameModel.getCurrentRound() < 8 && gameModel.getCurrentRound() > 3)
    		roundBonus = 100;
    	else if(gameModel.getCurrentRound() < 12 && gameModel.getCurrentRound() > 7)
    		roundBonus = 150;
    	else if(gameModel.getCurrentRound() == 12)
    		roundBonus = 200;
    	else
    		roundBonus = 0;
    		
    	if(muleTimerPanel.getRemainingTime() > 36)
    		timeBonus = 200;
    	else if(muleTimerPanel.getRemainingTime() < 38 && muleTimerPanel.getRemainingTime() > 24)
    		timeBonus = 150;
    	else if(muleTimerPanel.getRemainingTime() < 26 && muleTimerPanel.getRemainingTime() > 11)
    		timeBonus = 100;
    	else if(muleTimerPanel.getRemainingTime() < 13)
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
        // TODO Auto-generated method stub
        
    }

}
