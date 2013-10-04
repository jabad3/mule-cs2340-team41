import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

/**
 * 
 */

/**
 * @author jabad3
 *
 */
public class PlayerConfigStage extends Stage {
	PlayerConfigView myView;
	public int playerAt = 1;
	
	// used to setup the View
    private String[] allColorOptions = {"0xFF0000", "0x008000", "0xFFFF00", "0x0000FF"};
    private List<Color> disabledColorOptions = new ArrayList<>();
	
	public PlayerConfigStage(JFrame mainFrame, GameModel model) {
    	super(mainFrame, model);
    }
	
	public void showPlConfigPane() {
		myView = new PlayerConfigView();
		myView.setPlayerNum(playerAt);
		myView.setAllColorOptions(allColorOptions);
		myView.setDisabledColorOptions(disabledColorOptions);
		myView.addFinishedListener(new FinishedListener());
    	displayView(myView);
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
			if(myView.getRace() == -1 || myView.getColor() == null)
			{
				return;
			}
			
			// Ensures that no color is selected twice
			disabledColorOptions.add(myView.getColor());
			Player player = new Player(myView.getText(), RaceType.values()[myView.getRace()], myView.getColor(), gameModel.getDifficulty());
			gameModel.addPlayer(player);
			playerAt++;
			
			System.out.println("New model = " + gameModel);
			
			if(playerAt > gameModel.getNumPlayers())
			{
				System.out.println("Last player configed, going to map!");
				MapPanel map = new MapPanel();
				displayView(map);
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
