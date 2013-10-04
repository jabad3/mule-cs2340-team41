import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 */

/**
 * @author jabad3
 *
 */
public class PlayerConfigStage {
	public String name; //TODO: make enum
	public int race;
	public int color; //TODO: make color
	
	public PlayerConfigStage() {
	}
	}

    private class FinishedListener implements ActionListener {
        
        /**
         * When the View is "finished", set up the model accordingly,
         * then terminate this stage of the game.
         * @param e
         */
        public void actionPerformed(ActionEvent e) {
        	PlayerConfigStage.this.configureModel();
            PlayerConfigStage.this.endStage();
}
