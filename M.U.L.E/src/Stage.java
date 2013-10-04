import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This interface is implemented by the controllers
 * @author Tim Farley
 * @version 1
 */

public abstract class Stage
{
	JFrame mainFrame;
	GameModel gameModel;
	Stage nextStage;
	
	public Stage(JFrame mainFrame, GameModel gameModel) {
		this.mainFrame = mainFrame;
		this.gameModel = gameModel;
	}
	
	public void setNextStage(Stage nextStage) {
		this.nextStage = nextStage;
	}
	
	public void setView(JPanel view)
	{
    	mainFrame.setContentPane(view);
    	mainFrame.validate();
	}
	
	public abstract void start();
	public abstract void goNextStage();
}