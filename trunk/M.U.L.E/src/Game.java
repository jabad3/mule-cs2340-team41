import javax.swing.JFrame;
/**
 * This class orchestrates an entire gameplay session passing control to various stages
 * @author Tim Farley
 * @version 1
 */

public class Game {
	public JFrame mainFrame;
	public int curRound;
	
	public Game(JFrame mainFrame)
	{
		this.mainFrame = mainFrame;
	}
	
	public void start()
	{
		(new SetupStage()).takeControl(this);
		//SummaryStage summaryStage = new SummaryStage();
		//LandSelectionStage landSelectionStage = new LandSelectionStage();
		//LandAuctionStage landAuctionStage = new LandAuctionStage();
		//DevelopmentStage developmentStage = new DevelopmentStage();
		//ProductionStage productionStage = new ProductionStage();
		//ResourceAuctionStage resourceAuctionStage = new ResourceAuctionStage();
		for(int i = 0; i < 6; i++)
		{
			//(new summaryStage()).takeControl(this);
			//(new landSelectionStage()).takeControl(this);
			//(new landAuctionStage()).takeControl(this);
			//(new developmentStage()).takeControl(this);
			//(new productionStage()).takeControl(this);
			//(new resourceAuctionStage()).takeControl(this);
		}
	}
}
