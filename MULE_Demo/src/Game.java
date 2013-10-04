import javax.swing.JFrame;
/**
 * This class orchestrates an entire gameplay session passing control to various stages
 * @author Tim Farley
 * @version 1
 */

public class Game {
	private JFrame mainFrame;
	private GameModel gameModel = new GameModel();
	private int curRound = 1;
	
	public Game(JFrame mainFrame)
	{
		this.mainFrame = mainFrame;
	}
	
	public void start()
	{
	    GameConfigView gameConfigView = new BasicGameConfigView();
	    GameConfigStage gameConfigStage
	        = new GameConfigStage(gameModel, gameConfigView);
	    gameConfigStage.displayMyView(mainFrame);
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
