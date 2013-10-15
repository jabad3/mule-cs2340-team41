package Stages;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import Models.GameModel;
import Models.Map;
import Models.Player;
import Views.DevelopmentView;
import Views.MapPanel;
import Views.PlayerPawn;
import Views.TownPanel;

/**
 * Controller for Development Stage
 */

/**
 * @author jabad3
 *
 */
public class DevelopmentStage extends Stage {

    /** The view to be controlled by this stage. */
    private DevelopmentView myView;
    
    /** The map panel to be displayed in the view. */
    private MapPanel mapPanel;
    
    /** The town panel to be displayed in the view. */
    private TownPanel townPanel;
    
    /** Ordered list of players.  Player at index 0 goes first. */
    private List<Player> playerList;
    
    /** The player whose turn it currently is. */
    private Player currentPlayer;
    
    /** The index in the playerList of the player whose turn it currently is. */
    private int currentPlayerIndex;
    	
	
	
	/**
	 *	Creates Development Stage object
	 * @param mainFrame
	 * @param gameModel
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
		mapPanel = new MapPanel(map, new LandPlotListener());
		townPanel = new TownPanel();
		
		myView = new DevelopmentView(mapPanel, townPanel, new PlayerPawn(new ImageIcon("buzzite.png")), null); // temporary
		displayView(myView);
	}

	
	
    private class LandPlotListener extends MouseAdapter {

    }

}
