import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	public PlayerConfigStage(Game game, GameModel model) {
    	super(game, model);
    }
	
	public void start() {
		System.out.println("pconfig start");
		myView = new PlayerConfigView();
    	game.setView(myView);
	}
	
	public void goNextStage() {
		
	}
	
	private class FinishedListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(playerAt > gameModel.getNumPlayers())
			{
				//go to map
			}
				
			//TODO: myView.getRace() instead of 123 in here!
			Player player = new Player(myView.getText(), 123, myView.getColor(), gameModel.getDifficulty());
			playerAt++;
			
			//reset view, invalidate colors
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
