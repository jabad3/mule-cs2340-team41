import java.util.List;

import javax.swing.JFrame;

/**
 * Controller for Development Stage
 */

/**
 * @author jabad3
 *
 */
public class DevelopmentStage extends Stage {

    /** The view to be controlled by this stage. */
    private DevelopmentStageView myView;
    
    /** The map panel to be displayed in the view. */
    private MapPanel mapPanel;
    
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
		displayView(myView);
	}

	/**
	 * @param args
	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}

}
