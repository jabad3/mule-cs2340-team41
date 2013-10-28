package Views;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Models.FailedTransactionException;
import Models.Player;
import Models.Resource;
import Models.Store;
/**
 * This class allows the user to buy from the store
 * @author Tim
 */
public class StorePanel extends JPanel{
    /** Label for ore */
	JLabel oreLabel = new JLabel();
	
	/** Spinner for ore */
	JSpinner oreSpinner;
	
	/** Label for food */
	JLabel foodLabel = new JLabel();
	
	/** Spinner for food */
	JSpinner foodSpinner;
	
	/** Label for energy */
	JLabel energyLabel = new JLabel();
	
	/** Spinner for energy */
	JSpinner energySpinner;
	
	/** Combo box to select mule type */
	JComboBox<String> muleTypeCombobox;
	
	/** Label for subtotal */
	JLabel subtotalLabel = new JLabel();
	
	/** Button to complete transaction */
	JButton buySellButton = new JButton();
	
	/** The player that is currently interacting with the store */
	Player player;
	
	/** The store that is currently interacting with the player */
	Store store;
	
	/** Tracks whether player is buying or selling from/to store */
	boolean isBuying;
    
	/**
	 * This class updates a player's inventory when the buy button is pressed
	 * @author Erica
	 */
	private class InventoryListener implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			try {
				if(isBuying == true)
				{
				//TODO: NEED TO HANDLE MULE AND MULE TYPE
				//TODO: make dialog box (in DevelopmentStage) close when button is clicked
					player.buyResourceFromSeller(store, Resource.ENERGY, Store.energyPrice, (Integer)energySpinner.getValue());
					player.buyResourceFromSeller(store, Resource.ORE, Store.orePrice, (Integer)oreSpinner.getValue());
					player.buyResourceFromSeller(store, Resource.FOOD, Store.foodPrice, (Integer)foodSpinner.getValue());
				}
				else
				{
					store.buyResourceFromSeller(player, Resource.ENERGY, Store.energyPrice, (Integer)energySpinner.getValue());
					store.buyResourceFromSeller(player, Resource.ORE, Store.orePrice, (Integer)oreSpinner.getValue());
					store.buyResourceFromSeller(player, Resource.FOOD, Store.foodPrice, (Integer)foodSpinner.getValue());
				}
			} catch (FailedTransactionException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * Creates the StorePanel's interface
	 * 
	 * @param store The store to transact with
	 * @param player The Player to transact with
	 * @param isBuying Whether to set the interface in buying or selling mode
	 */
	public StorePanel(Store store, Player player) {
		this.player = player;
		this.store = store;
		
		setLayout(new GridLayout(5,2));
		
		ChangeListener updateListener = new ChangeListener() {
	        @Override
	        public void stateChanged(ChangeEvent e) {
	            updateStorePanel((JSpinner)e.getSource());
	        }
	    };
    	
		this.add(oreLabel);
		oreSpinner = new JSpinner();
		oreSpinner.addChangeListener(updateListener);
		this.add(oreSpinner);
		
		this.add(foodLabel);
		foodSpinner = new JSpinner();
		foodSpinner.addChangeListener(updateListener);
		this.add(foodSpinner);
		
		this.add(energyLabel);
		energySpinner = new JSpinner();
		energySpinner.addChangeListener(updateListener);
		this.add(energySpinner);
		
		String[] muleTypeStrings = {"Don't Buy", "Food", "Energy", "Ore"};

		this.add(new JLabel("Mule Type"));
		muleTypeCombobox = new JComboBox<String>(muleTypeStrings);
		this.add(muleTypeCombobox);
		
		this.add(subtotalLabel);
		this.add(buySellButton);
		
		InventoryListener inventoryListener = new InventoryListener();
		buySellButton.addActionListener(inventoryListener);
		
		updateStorePanel(null);
	}
	
	/**
	 * Changes the transaction mode of the store to buy/sell
	 */	
	public void changeMode(boolean isBuying)
	{
		this.isBuying = isBuying;
		updateStorePanel(null);
	}
	
	/**
	 * Updates spinners and JLabels to reflect model
	 */	
	public void updateStorePanel(JSpinner source)
	{
		int oreSubtotal;
		int foodSubtotal;
		int energySubtotal;
		
		if(isBuying)
		{
			oreSubtotal = (Integer)oreSpinner.getValue() * Store.orePrice;
			foodSubtotal = (Integer)foodSpinner.getValue() * Store.foodPrice;
			energySubtotal = (Integer)energySpinner.getValue() * Store.energyPrice;
		}
		else
		{
			//TODO: different selling prices?
			oreSubtotal = (Integer)oreSpinner.getValue() * Store.orePrice;
			foodSubtotal = (Integer)foodSpinner.getValue() * Store.foodPrice;
			energySubtotal = (Integer)energySpinner.getValue() * Store.energyPrice;
		}
		
		oreLabel.setText("Ore (" + (isBuying ? store.getOre() : player.getOre()) + ")");
		SpinnerModel oreModel = new SpinnerNumberModel(((Integer)oreSpinner.getValue()).intValue(), //initial value
                0, //min
                (isBuying ? Math.min((player.getMoney()-foodSubtotal-energySubtotal)/Store.orePrice, store.getOre()) : player.getOre()), //max
                1); //step
		oreSpinner.setModel(oreModel);
		
		foodLabel.setText("Food (" + (isBuying ? store.getFood() : player.getFood()) + ")");
		SpinnerModel foodModel = new SpinnerNumberModel(((Integer)foodSpinner.getValue()).intValue(), //initial value
                0, //min
                (isBuying ? Math.min((player.getMoney()-oreSubtotal-energySubtotal)/Store.foodPrice, store.getFood()) : player.getFood()), //max
                1); //step
		foodSpinner.setModel(foodModel);
		
		energyLabel.setText("Energy (" + (isBuying ? store.getEnergy() : player.getEnergy()) + ")");
		SpinnerModel energyModel = new SpinnerNumberModel(((Integer)energySpinner.getValue()).intValue(), //initial value
                0, //min
                (isBuying ? Math.min((player.getMoney()-oreSubtotal-foodSubtotal)/Store.energyPrice, store.getEnergy()) : player.getEnergy()), //max
                1); //step
		energySpinner.setModel(energyModel);
		
		if(source != null)
			source.requestFocus();
		
		subtotalLabel.setText("Subtotal: $" + (oreSubtotal + foodSubtotal + energySubtotal));
		buySellButton.setText(isBuying ? "Buy" : "Sell");
	}
}
