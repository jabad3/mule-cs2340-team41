import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * This class orchestrates an entire gameplay session of MULE.
 * All Stages and the GameModel are instantiated in this class.
 * 
 * @author Tim Farley
 * @version 1
 */

public class Game {
	/**  The primary container to hold all Views used during the game. */
    private JFrame mainFrame;
    
    /** The gameModel object that will be used for the duration of the game. */
	private GameModel gameModel;
	
	/**
	 * Instantiates a new Game object to run a game of MULE.
	 * 
	 * @param mainFrame An empty mainFrame
	 */
	public Game(JFrame mainFrame)
	{
		this.mainFrame = mainFrame;
		this.gameModel = new GameModel();
	}
	
	/**
	 * Begins a game of MULE by starting the first stage of the game.
	 */
	public void start()
	{
	    Stage gameConfigStage = new GameConfigStage(mainFrame, gameModel);
	    Stage playerConfigStage = new PlayerConfigStage(mainFrame, gameModel);
	    Stage landSelectionStage = new LandSelectionStage(mainFrame, gameModel);
	    
	    gameConfigStage.setNextStage(playerConfigStage);
	    playerConfigStage.setNextStage(landSelectionStage);
	    
	    gameConfigStage.start();
	}
}
