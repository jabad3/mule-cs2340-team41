package Views;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The ProductionView displays the results of production to the user in a
 * friendly manner.  Each land plot that produced a positive number of resources
 * will display the number of resources that it produced.  This allows the user
 * to see their overall production for the round.
 * 
 * @author Max
 *
 */
public class ProductionView extends JPanel {
    
    /** The MapPanel to display to the user. */
    MapPanel mapPanel;

    /**
     * Create the ProductionView.
     * 
     * @param mapPanel The map to display to the user
     */
    public ProductionView(MapPanel mapPanel) {
        this.mapPanel = mapPanel;
        
        JLabel infoLabel = new JLabel("Production Results For The Round");
        
        this.setLayout(new BorderLayout());
        this.add(infoLabel, BorderLayout.NORTH);
        this.add(mapPanel, BorderLayout.CENTER);
    }
}
