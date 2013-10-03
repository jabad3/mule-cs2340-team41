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
public class GameConfigStage {

    private GameConfigView myView;
    private GameModel model;
    
    private final Difficulty[] difficulties = Difficulty.values();
    private final String[] mapChoices = new String[] {"Default", "Random"};
    private final Integer[] playerCountChoices = new Integer[] {1, 2, 3, 4};
    
    public GameConfigStage(GameModel model, GameConfigView view) {
        this.model = model;
        this.myView = view;
        setupMyView();
    }
    
    private void setupMyView() {
        myView.setDifficultyChoices(difficulties);
        myView.setMapTypeChoices(mapChoices);
        myView.setPlayerCountChoices(playerCountChoices);
        myView.addFinishedListener(new FinishedListener());
    }
    
    /**
     * Displays this Stage's View in the primary JFrame for the game.
     * 
     * @param mainframe The primary container for all views in MULE
     */
    public void displayMyView(JFrame mainframe) {
        // TODO
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
        model.setDifficulty(difficulty);
        model.setNumPlayers(numPlayers);
        // TODO - not sure how to handle the map...
        // I don't want anymore enums!!  Can we just build the map here??
        // Map map = buildMap(mapType);
        // model.setMap(map);
    }
    
    /**
     * Need to figure out the best way to do this...
     *   one way:  let a Stage controller know what the next controller is,
     *   then upon calling endStage, do something like
     *      nextController.showMyView?  It seems a little weird, though.
     */
    public void endStage() {
        // TODO
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
            GameConfigStage.this.endStage();
        }
    }
}
