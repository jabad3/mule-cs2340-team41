import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

/**
 * GameConfigStage acts as the Controller during the game configuration step.
 *     
 * During the GameConfigStage, the User should be able to select the following:
 *     a) Difficulty
 *     b) Map Type
 *     c) Number of Players
 *     
 * The GameConfigStage class is responsible for
 *     1)  Setting up its View with valid choices
 *     2)  Listening for when the User signifies they are finished
 *           interacting with the View (ex: the User presses "Next" or "Done")
 *     3)  Setting GameModel's attributes according to User's input at the View
 *     
 * @author Max
 *
 */
public class GameConfigStage extends Stage {

    private GameConfigView myView;
    
    private final Difficulty[] difficulties = Difficulty.values();
    private final String[] mapChoices = new String[] {"Default", "Random"};
    private final Integer[] playerCountChoices = new Integer[] {1, 2, 3, 4};
    
    public GameConfigStage(JFrame mainFrame, GameModel model) {
    	super(mainFrame, model);
    }
    
    public void start() {
    	myView = new BasicGameConfigView();
    	setView(myView);
    	
    	IntroTrack theme = new IntroTrack();	//to fix, this creates a blocking thread
        theme.start();
        
        myView.setDifficultyChoices(difficulties);
        myView.setMapTypeChoices(mapChoices);
        myView.setPlayerCountChoices(playerCountChoices);
        myView.addFinishedListener(new FinishedListener());
    }
    
    /**
     * Configures the model according to information in the View.
     * The information taken from the View is...
     *      1) Difficulty
     *      2) Map Type
     *      3) Number of players
     */
    private void configureModel() {
        // pull selections from the View
        Difficulty difficulty = myView.getSelectedDifficulty();
        String mapType = myView.getSelectedMapType();
        int numPlayers = myView.getSelectedPlayerCount();
        
        // give these selections to the Model
        gameModel.setDifficulty(difficulty);
        gameModel.setNumPlayers(numPlayers);
        // TODO - not sure how to handle the map...
        // I don't want anymore enums!!  Can we just build the map here??
        Map map = MapFactory.buildMap(mapType);
        gameModel.setMap(map);
        // TODO - build the store here, or have GameModel do it??
        Store store = Store.buildStore(difficulty);
        gameModel.setStore(store);
        System.out.println("GameModel Info----\n" + gameModel);
    }
    
    /**
     * Need to figure out the best way to do this...
     *   one way:  let a Stage controller know what the next controller is,
     *   then upon calling endStage, do something like
     *      nextController.showMyView?  It seems a little weird, though.
     */
    public void goNextStage() {
        // TODO
        /* perhaps something like  nextController.takeOver()? */
    	//will add link to player config right here soon
    	//next stage is player config. how to implement loop of n times for n players so that
    	//everyone can configure their player in sequence?
    	
    	////// super sample code, remove when finsihed testing -joe
    	////
    	nextStage.start();
		/*JFrame temp = new JFrame("temp");
		PlayerConfigView temp2 = new PlayerConfigView();
		temp.add(temp2);
		temp.pack();
		temp.setVisible(true);*/
		System.out.println("next stage");
    	///
    }
    
    /**
     * This class listens to the View for an ActionEvent signalling
     * that the User is finished interacting with the 
     * @author Max
     *
     */
    private class FinishedListener implements ActionListener {
        
        /**
         * When the View is "finished", set up the model accordingly,
         * then terminate this stage of the game.
         * @param e
         */
        public void actionPerformed(ActionEvent e) {
            GameConfigStage.this.configureModel();
            GameConfigStage.this.goNextStage();
        }
    }
}
