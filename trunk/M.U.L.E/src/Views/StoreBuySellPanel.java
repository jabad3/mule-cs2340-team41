package Views;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Views.StoreView.SelectionListener;

/**
 * This class allows the user to select whether to buy or sell from/to the store
 * @author Tim
 */
public class StoreBuySellPanel extends JPanel{
	/** Displays the store transaction menu */
    private StorePanel storePanel;
    
	/** Label for buying/selling context */
	JLabel buySellLabel = new JLabel("Do you want to buy or sell to the store?");
	
	/** Button to select buying */
	JButton buyButton = new JButton("Buy");
	
	/** Button to select selling */
	JButton sellButton = new JButton("Sell");
	
	/**
	 * Creates the store mode selection panel
	 * 
	 * @param storePanel The store panel to configure
	 * @param buyListener Listener to call when buy selected
	 * @param sellListener Listener to call when sell selected
	 */
	public StoreBuySellPanel(StorePanel storePanel, SelectionListener buyListener, SelectionListener sellListener){
		this.storePanel = storePanel;
		
		setLayout(new GridLayout(2, 1));
		
		buyButton.addActionListener(buyListener);
		sellButton.addActionListener(sellListener);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(buyButton);
		buttonsPanel.add(sellButton);
		
		this.add(buySellLabel);
		this.add(buttonsPanel);
	}
}