package Stages;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import Models.GameModel;
import Models.Map;
import Models.Player;
import Views.DevelopmentView;
import Views.MapPanel;
import Views.MuleTimerListener;
import Views.MuleTimerPanel;
import Views.PlayerPawn;
import Views.TownPanel;

/**
 * Controller for Development Stage
 */

/**
 * @author jabad3
 *
 */
public class DevelopmentStage extends Stage implements MuleTimerListener {

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
		displayView(myView);
		
		currentPlayerIndex = 0;
		beginCurrentPlayerTurn();
	}
	
	
	private void beginCurrentPlayerTurn() {
	    currentPlayer = playerList.get(currentPlayerIndex);
	    PlayerPawn currentPawn = new PlayerPawn(currentPlayer.getIcon());
	    myView.setCurrentPawn(currentPawn);
	    myView.setCurrentPlayerName(currentPlayer.getName());
	    myView.beginPlayerTurn(7500);  // temporary fixed time
	    myView.showTown();
	}

    @Override
    /**
     * Called when a player's turn is up
     */
    public void muleTimerFinished() {
        myView.endPlayerTurn();
        
        currentPlayerIndex++;
        if (currentPlayerIndex >= playerList.size())
            goNextStage();
        else
            beginCurrentPlayerTurn();
    }

}
