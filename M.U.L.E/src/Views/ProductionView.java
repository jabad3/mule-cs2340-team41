package Views;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The ProductionView displays the results of production to the user in a
 * friendly manner. Each land plot that produced a positive number of resources
 * will display the number of resources that it produced. This allows the user
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
     * @param mapPanel
     *            The map to display to the user
     */
    public ProductionView(MapPanel mapPanel, ActionListener finishedListener) {
	this.mapPanel = mapPanel;

	JLabel infoLabel = new JLabel("Production Results For The Round");
	JButton continueButton = new JButton("Continue to Summary");
	continueButton.addActionListener(finishedListener);

	JPanel northPanel = new JPanel();
	northPanel.add(infoLabel);
	northPanel.add(continueButton);

	this.setLayout(new BorderLayout());
	this.add(northPanel, BorderLayout.NORTH);
	this.add(mapPanel, BorderLayout.CENTER);
    }
}
