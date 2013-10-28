package Views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import Models.Player;
import Models.Store;

/**
 * This class manages a player's interaction with the store through buying/selling
 * 
 * @author Tim
 *
 */
public class StoreView extends JPanel {

    /** Displays the store transaction menu */
    private StorePanel storePanel;
    
    /** Displays the store buy/sell mode selection menu */
    private StoreBuySellPanel storeBuySellPanel;
    
    /** A JPanel with card layout to allow easy swapping between transaction and selection menus. */
    private JPanel cardPanel;
    
    /** The JPanel's card layout */
    CardLayout cardLayout;
    
    /**
     * Create the Store View.
     * 
     * @param storePanel The transaction panel to display to the user
     * @param storeBuySellPanel The transaction mode selector to display to the user
     */
    public StoreView(StorePanel storePanel) {
        this.storePanel = storePanel;
        this.storeBuySellPanel = new StoreBuySellPanel(storePanel, new SelectionListener(true), new SelectionListener(false));
        
        cardLayout = new CardLayout();
        
        cardPanel = new JPanel();
        cardPanel.setLayout(cardLayout);
        cardPanel.add(this.storeBuySellPanel, "storeBuySellPanel");
        cardPanel.add(this.storePanel, "storePanel");
        
        this.add(cardPanel);
    }
    
    /**
	 * Processes mode selection
	 */
	public class SelectionListener implements ActionListener
	{
		boolean isBuying;
		
		/**
		 * Initializes the mode selector listener
		 * 
		 * @param isBuying Whether to configure the store to buying or selling
		 */
		public SelectionListener(boolean isBuying)
		{
			this.isBuying = isBuying;
		}
		
		/**
		 * Sets the configuration of the store
		 * 
		 * @param ActionEvent Unused
		 */
		public void actionPerformed(ActionEvent e)
	    {
	        storePanel.changeMode(isBuying);
	        cardLayout.show(cardPanel, "storePanel");
	    }
	}
}
