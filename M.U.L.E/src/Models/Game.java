package Models;
import javax.swing.JFrame;

import Stages.DevelopmentStage;
import Stages.GameConfigStage;
import Stages.LandSelectionStage;
import Stages.PlayerConfigStage;
import Stages.ProductionStage;
import Stages.Stage;

/**
 * This class orchestrates an entire game play session of MULE.
 * All Stages and the GameModel are instantiated in this class.
 * 
 * @author Tim Farley
 * @version 1
 */

public class Game {
	/** The primary container to hold all View classes used during the game. */
    private JFrame mainFrame;
    
    /** The GameModel object that will be used for the duration of the game. */
	private GameModel gameModel;
	
	/** The Stage controller during the game configuration phase of the game. */
	private Stage gameConfigStage;
	
	/** The Stage controller during the player configuration phase of the game. */
	private Stage playerConfigStage;
    
    /** The Stage controller during the land selection phase of the game. */
	private Stage landSelectionStage;
    
    /** The Stage controller during the development phase of the game. */
	private Stage developmentStage;
    
    /** The Stage controller during production phase of the game. */
	private Stage productionStage;
	
	/**
	 * Instantiates a new Game object to run a game of MULE.
	 * 
	 * @param mainFrame An empty mainFrame
	 */
	public Game(JFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.gameModel = new GameModel();
	}
	/**
	 * Instantiates a new Game object to run a game of MULE using a previous GameModel (ie. loading a game).
	 * 
	 * @param mainFrame An empty mainFrame
	 */
	public Game(JFrame mainFrame, GameModel gameModel) {
		this.mainFrame = mainFrame;
		this.gameModel = gameModel;
	}
	
	/**
	 * Creates and links each stage to prepare the game loop.
	 * Begins a game of MULE by starting the first stage of the game.
	 */
	public void start() {
		gameConfigStage = new GameConfigStage(mainFrame, gameModel);
		playerConfigStage = new PlayerConfigStage(mainFrame, gameModel);
	    landSelectionStage = new LandSelectionStage(mainFrame, gameModel);
	    developmentStage = new DevelopmentStage(mainFrame, gameModel);
	    productionStage = new ProductionStage(mainFrame, gameModel);
	    
	    gameConfigStage.setNextStage(playerConfigStage);
	    playerConfigStage.setNextStage(landSelectionStage);
	    landSelectionStage.setNextStage(developmentStage);
	    developmentStage.setNextStage(productionStage);
	    productionStage.setNextStage(landSelectionStage);
	    
		if(gameModel.getCurrentRound() == 0) {
			gameConfigStage.start();
		}
		else {
			landSelectionStage.start();
		}
	}
}
