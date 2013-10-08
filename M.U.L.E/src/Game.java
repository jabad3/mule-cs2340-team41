import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * This class orchestrates an entire gameplay session passing control to various stages
 * @author Tim Farley
 * @version 1
 */

public class Game {
	private JFrame mainFrame;
	private GameModel gameModel = new GameModel();
	
	public Game(JFrame mainFrame)
	{
		this.mainFrame = mainFrame;
	}
	
	public void start()
	{
	    GameConfigStage gameConfigStage = new GameConfigStage(mainFrame, gameModel);
	    PlayerConfigStage playerConfigStage = new PlayerConfigStage(mainFrame, gameModel);
	    LandSelectionStage landSelectionStage = new LandSelectionStage(mainFrame, gameModel);
	    
	    gameConfigStage.setNextStage(playerConfigStage);
	    playerConfigStage.setNextStage(landSelectionStage);
	    
	    gameConfigStage.start();
	}
}
