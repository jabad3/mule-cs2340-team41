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

    public abstract void setDifficultyChoices(Difficulty[] difficulties);
    
    public abstract void setMapTypeChoices(String[] mapChoices);

    public abstract void setPlayerCountChoices(Integer[] playerCountChoices);

    public abstract void addFinishedListener(ActionListener finishedListener);

    public abstract Difficulty getSelectedDifficulty();

    public abstract String getSelectedMapType();

    public abstract int getSelectedPlayerCount();

    
}
