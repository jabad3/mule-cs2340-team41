import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

/**
 * LandSelectionStage is the controller for player land selection.
 *     
 * @author Tim
 *
 */
public class LandSelectionStage extends Stage {

    private MapPanel myView;
    
    private int curChoosingPlayer = 0;
    
    public LandSelectionStage(JFrame mainFrame, GameModel model) {
    	super(mainFrame, model);
    }
    
    public void start() {
    	myView = new MapPanel();
    	displayView(myView);
    }
}
