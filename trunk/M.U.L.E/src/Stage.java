import javax.swing.JPanel;

/**
 * This interface is implemented by the controllers
 * @author Tim Farley
 * @version 1
 */

public abstract class Stage
{
	Game game;
	GameModel gameModel;
	Stage nextStage;
	
	public Stage(Game game, GameModel gameModel) {
		this.game = game;
		this.gameModel = gameModel;
	}
	
	public void setNextStage(Stage nextStage) {
		this.nextStage = nextStage;
	}
	
	public abstract void start();
	public abstract void goNextStage();
}