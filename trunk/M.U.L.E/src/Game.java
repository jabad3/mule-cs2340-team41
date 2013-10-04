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
	
	public void setView(JPanel view)
	{
    	mainFrame.setContentPane(view);
    	mainFrame.validate();
	}
	
	public void start()
	{
	    GameConfigStage gameConfigStage = new GameConfigStage(this, gameModel);
	    PlayerConfigStage playerConfigStage = new PlayerConfigStage(this, gameModel);
	    
	    gameConfigStage.setNextStage(playerConfigStage);
	    
	    gameConfigStage.start();
	    
	    //(new SetupStage(this)).takeControl();
		//SummaryStage summaryStage = new SummaryStage(this);
		//LandSelectionStage landSelectionStage = new LandSelectionStage(this);
		//LandAuctionStage landAuctionStage = new LandAuctionStage(this);
		//DevelopmentStage developmentStage = new DevelopmentStage(this);
		//ProductionStage productionStage = new ProductionStage(this);
		//ResourceAuctionStage resourceAuctionStage = new ResourceAuctionStage(this);
		for(int i = 0; i < 6; i++)
		{
			//summaryStage.takeControl();
			//landSelectionStage.takeControl();
			//landAuctionStage.takeControl();
			//developmentStage.takeControl();
			//productionStage.takeControl();
			//resourceAuctionStage.takeControl();
		}
	}
}
