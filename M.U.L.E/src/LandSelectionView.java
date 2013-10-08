import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The View used during the Land Selection Stage of the game.
 * @author Max
 *
 */
public class LandSelectionView extends JPanel {
    
    JLabel playerNameLabel;
    JLabel landPlotPriceLabel;
    JPanel mapPanel;
    
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

    public void setCurrentPlayerName(String playerName) {
        playerNameLabel.setText(playerName);
    }

}
