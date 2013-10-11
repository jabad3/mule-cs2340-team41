import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The View used during the Land Selection Stage of the game.
 * The LandSelectionView is responsible for displaying the current Player's
 * name, the current price of the land, and the map.
 * 
 * Interaction with the View includes selecting a land plot to purchase or
 * choosing the option to pass on selecting a land plot.
 * 
 * @author Max
 *
 */
public class LandSelectionView extends JPanel {
    
    /** Label to display the current Player's name. */
    JLabel playerNameLabel;
    
    /** Label to display the current price of land. */
    JLabel landPlotPriceLabel;
    
    /** Displays the land plots to the user. */
    MapPanel mapPanel;
    
    /**
     * Creates a LandSelectionView using an already-instantiated MapPanel
     * object.  Sets the layout of the View and adds appropriate components to
     * the View.
     * 
     * @param mapPanel The MapPanel to be displayed
     * @param landPlotPrice The current price of land
     * @param firstPlayerName The name of the current player
     */
    public LandSelectionView(MapPanel mapPanel, int landPlotPrice, String firstPlayerName) {
        playerNameLabel = new JLabel(firstPlayerName);
        landPlotPriceLabel = new JLabel("" + landPlotPrice);
        this.mapPanel = mapPanel;
        
        // TODO
        this.setLayout(new BorderLayout());
        this.add(mapPanel, BorderLayout.CENTER);
        this.add(landPlotPriceLabel, BorderLayout.NORTH);
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
