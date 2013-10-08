import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
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
	
    
    /**
     * A list of Color objects representing colors that cannot be chosen.
     * 
     * Colors must be disabled after they are picked so that no two Players
     * have the same Color.  
     * 
     * This list grows as more Players select their colors. 
     */
    private List<Color> disabledColorOptions = new ArrayList<>();
	
	public PlayerConfigStage(JFrame mainFrame, GameModel model) {
    	super(mainFrame, model);
    	
    }
	
	public void showPlayerConfigPane() {
		myView = new PlayerConfigView();
		myView.setPlayerNum(playerAt);
		myView.setDisabledColorOptions(disabledColorOptions);
		myView.addFinishedListener(new FinishedListener());
    	displayView(myView);
	}
	
	public void start() {
		System.out.println("pconfig start");
		showPlayerConfigPane();
	}
	
	public void goNextStage() {
		
	}
	
	private class FinishedListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			// take no action if user does not select a race or color
		    if(myView.getRace() == null || myView.getColor() == null)
			{
				return;
			}
			
			// Ensures that no color is selected twice
			disabledColorOptions.add(myView.getColor());
			Player player = new Player(myView.getText(), myView.getRace(), myView.getColor(), gameModel.getDifficulty());
			gameModel.addPlayer(player);
			playerAt++;
			
			if(playerAt > gameModel.getNumPlayers())
			{
				System.out.println("Game Model info after configuring... \n" + gameModel);
			    System.out.println("Last player configed, going to map!");
				MapPanel map = new MapPanel();
				displayView(map);
			}
			else
			{
				showPlayerConfigPane();
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
