import java.awt.event.ActionListener;

import javax.swing.JPanel;

/**
 * A GameConfigView must be able to display the following:
 *     1) Difficulty choices
 *     2) Map type choices
 *     3) Number of players choices
 *     4) A way to let the User confirm their choice (ex: Next button)
 * 
 * @author Max
 *
 */
public abstract class GameConfigView extends JPanel {

    /**
     * Configure appropriate component(s) in this GameConfigView to display
     * given difficulties to the user.
     * 
     * @param difficulties Difficulty options to give the user
     */
    public abstract void setDifficultyChoices(Difficulty[] difficulties);
    
    /**
     * Configure appropriate component(s) in this GameConfigView to display
     * given map type choices to the user.
     * 
     * @param mapChoices String array of map type options
     */
    public abstract void setMapTypeChoices(String[] mapChoices);

    /**
     * Configure appropriate component(s) in this GameConfigView to display
     * given player count choices to the user.
     * 
     * @param playerCountChoices int array of options for the number of players
     */
    public abstract void setPlayerCountChoices(Integer[] playerCountChoices);

    /**
     * Assign the finishedListener to the appropriate component that will fire
     * an ActionEvent when the user chooses to finishe configuring the game.
     * 
     * @param finishedListener ActionListener to listen for this finish event
     */
    public abstract void addFinishedListener(ActionListener finishedListener);

    /**
     * Get the Difficulty chosen by the user.
     * 
     * @return The selected difficulty
     */
    public abstract Difficulty getSelectedDifficulty();

    /**
     * Get the map type chosen by the user.
     * 
     * @return String representing the selected map type
     */
    public abstract String getSelectedMapType();

    /**
     * Get the player count chosen by the user.
     * 
     * @return Number of players that will play the game
     */
    public abstract int getSelectedPlayerCount();

    
}
