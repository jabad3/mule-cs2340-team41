import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 */

/**
 * @author jabad3
 *
 */
public class DevelopmentStageView extends JPanel {
    
    /** Label to display the current Player's name. */
    JLabel playerNameLabel;
    
    /** Displays the land plots to the user. */
    MapPanel mapPanel;
    
    public DevelopmentStageView(MapPanel mapPanel, String firstPlayerName) {
        playerNameLabel = new JLabel(firstPlayerName);
        this.mapPanel = mapPanel;
        
        // TODO
        this.setLayout(new BorderLayout());
        this.add(mapPanel, BorderLayout.CENTER);
        this.add(playerNameLabel, BorderLayout.SOUTH);
    }

    /**
     * Set the current Player's name
     * 
     * @param playerName The new name to display
     */
    public void setCurrentPlayerName(String playerName) {
        playerNameLabel.setText(playerName);
    }
 
    
    
}

