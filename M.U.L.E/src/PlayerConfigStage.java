import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

/**
 * 
 */

/**
 * @author jabad3
 *
 */
public class PlayerConfigStage extends Stage {
	public String name; //TODO: make enum
	public int race;
	public int color; //TODO: make color
	PlayerConfigView myView;
	public int playerAt = 1;
	
	public PlayerConfigStage(JFrame mainFrame, GameModel model) {
    	super(mainFrame, model);
    }
	
	public void showPlConfigPane() {
		myView = new PlayerConfigView();
		myView.setPlayerNum(playerAt);
		myView.addFinishedListener(new FinishedListener());
    	setView(myView);
	}
	
	public void start() {
		System.out.println("pconfig start");
		showPlConfigPane();
	}
	
	public void goNextStage() {
		
	}
	
	private class FinishedListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			Player player = new Player(myView.getText(), RaceType.values()[myView.getRace()], myView.getColor(), gameModel.getDifficulty());
			gameModel.addPlayer(player);
			playerAt++;
			
			System.out.println("New model = " + gameModel);
			
			if(playerAt > gameModel.getNumPlayers())
			{
				System.out.println("Last player configed, going to map!");
				//TODO: go to map
			}
			else
			{
				showPlConfigPane();
			}
		}
	}
}

//    private class FinishedListener implements ActionListener {
//        
//        /**
//         * When the View is "finished", set up the model accordingly,
//         * then terminate this stage of the game.
//         * @param e
//         */
//        public void actionPerformed(ActionEvent e) {
//        	PlayerConfigStage.this.configureModel();
//            PlayerConfigStage.this.endStage();
//}
