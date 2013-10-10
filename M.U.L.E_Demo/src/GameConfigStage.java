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
 * The GameConfigStage class is responsible for:  
 *     1)  Setting up its View with valid choices
 *     2)  Listening for when the User signifies they are finished
 *           interacting with the View (ex: the User presses "Next" or "Done")
 *     3)  Setting GameModel's attributes according to User's input at the View
 *     
 * @author Max
 *
 */
public class GameConfigStage extends Stage {

    /** The GameConfigView to display to the user. */
    private GameConfigView myView;
    
    /** All available difficulties that can be selected. */
    private final Difficulty[] difficulties = Difficulty.values();
    
    /** All available options for the map type. */
    private final String[] mapChoices = new String[] {"Default", "Random"};
    
    /** All available options for the number of players in the game. */
    private final Integer[] playerCountChoices = new Integer[] {1, 2, 3, 4};
    
    /**
     * Create a GameConfigStage.
     * 
     * @param mainFrame Reference to the game's JFrame
     * @param model Reference to the game's GameModel
     */
    public GameConfigStage(JFrame mainFrame, GameModel model) {
    	super(mainFrame, model);
    }
    
    /**
     * Creates and configures an instance of the GameConfigView, then
     * displays it in the mainFrame to show to the user to start the stage.
     */
    public void start() {
    	myView = new BasicGameConfigView();
    	
    	IntroTrack theme = new IntroTrack();
        theme.start();
        
        myView.setDifficultyChoices(difficulties);
        myView.setMapTypeChoices(mapChoices);
        myView.setPlayerCountChoices(playerCountChoices);
        myView.addFinishedListener(new FinishedListener());
        
        displayView(myView);
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
        
        Map map = MapFactory.buildMap(mapType);
        gameModel.setMap(map);

        Store store = new Store(difficulty);
        gameModel.setStore(store);
    }
    
    /**
     * This class listens to the View for an ActionEvent signaling
     * that the User is finished interacting with the GameConfigView.
     * 
     * @author Max
     *
     */
    private class FinishedListener implements ActionListener {
        
        /**
         * When the View is "finished", set up the model accordingly,
         * then terminate this stage of the game by starting the nextStage.
         * 
         * @param e ActionEvent fired by the GameConfigView
         */
        public void actionPerformed(ActionEvent e) {
            GameConfigStage.this.configureModel();
            GameConfigStage.this.goNextStage();
        }
    }
}
