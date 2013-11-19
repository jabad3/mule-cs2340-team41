package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The View used during the Land Selection Stage of the game. The
 * LandSelectionView is responsible for displaying the current Player's name,
 * the current price of the land, and the map.
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

    /** Label to display error that the user doesn't have enough money */
    JLabel notEnoughMoneyLabel;

    /** Button to allow the player to skip their turn in land selection. */
    JButton skipButton;

    /** Displays the land plots to the user. */
    MapPanel mapPanel;

    /**
     * Creates a LandSelectionView using an already-instantiated MapPanel
     * object. Sets the layout of the View and adds appropriate components to
     * the View.
     * 
     * @param mapPanel
     *            The MapPanel to be displayed
     * @param landPlotPrice
     *            The current price of land
     * @param firstPlayerName
     *            The name of the current player
     * @param skipListener
     *            ActionListener that will listen to the skipButton
     */
    public LandSelectionView(MapPanel mapPanel, int landPlotPrice,
	    String firstPlayerName, ActionListener skipListener) {
	playerNameLabel = new JLabel(firstPlayerName);
	landPlotPriceLabel = new JLabel("" + landPlotPrice);
	notEnoughMoneyLabel = new JLabel("Not enough money!");

	// set text to be invisible (setVisible(false) does not give desired
	// effect)
	notEnoughMoneyLabel.setForeground(new Color(0, 0, 0, 0));

	skipButton = new JButton("Skip");
	skipButton.addActionListener(skipListener);

	this.mapPanel = mapPanel;
	// this.mapPanel.setPreferredSize(new Dimension(600, 400));
	setPreferredSize(new Dimension(600, 400));
	JPanel infoPanel = buildInfoPanel(landPlotPrice);

	this.setLayout(new BorderLayout());
	this.add(infoPanel, BorderLayout.NORTH);
	this.add(mapPanel, BorderLayout.CENTER);
    }

    /**
     * Creates a JPanel that allows the user to see: 1) Whose turn it is 2) If
     * it is a land grant, or the price of land if it is not free 3) Skip button
     * 
     * @param landPlotPrice
     *            The current price for land plots
     * @return The ready-to-use info panel
     */
    private JPanel buildInfoPanel(int landPlotPrice) {
	JPanel infoPanel = new JPanel();
	infoPanel.add(new JLabel("    Current Player: "));
	infoPanel.add(playerNameLabel);

	if (landPlotPrice == 0)
	    infoPanel.add(new JLabel("    Land Grant - Land is FREE!"));
	else {
	    infoPanel.add(new JLabel("    Land Price:  "));
	    infoPanel.add(landPlotPriceLabel);
	}

	infoPanel.add(skipButton);
	infoPanel.add(notEnoughMoneyLabel);

	infoPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));

	return infoPanel;
    }

    /**
     * Set the current Player's name
     * 
     * @param playerName
     *            The new name to display
     */
    public void setCurrentPlayerName(String playerName) {
	playerNameLabel.setText(playerName);
    }

    /**
     * Displays a message near the top of the map for one second that states
     * there is not enough money.
     */
    public void flashNotEnoughMoneyMessage() {
	notEnoughMoneyLabel.setForeground(Color.RED);
	notEnoughMoneyLabel.setVisible(true);
	Timer eraser = new Timer();
	eraser.schedule(new TimerTask() {
	    public void run() {
		// make it invisible again
		notEnoughMoneyLabel.setForeground(new Color(0, 0, 0, 0));
	    }
	}, 1000);
    }

}
