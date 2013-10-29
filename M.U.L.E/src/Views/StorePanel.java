package Views;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Models.FailedTransactionException;
import Models.Mule;
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
					
					if(muleTypeCombobox.getSelectedIndex() == 1)
					{
						//Buy Food Mule
						player.buyResourceFromSeller(store, Resource.MULE, Store.mulePrice + Resource.FOOD.getMuleTypeScore(), 1);
						player.setMule(new Mule(Resource.FOOD));
					}
					else if(muleTypeCombobox.getSelectedIndex() == 2)
					{
						//Buy Energy Mule
						player.buyResourceFromSeller(store, Resource.MULE, Store.mulePrice + Resource.ENERGY.getMuleTypeScore(), 1);
						player.setMule(new Mule(Resource.ENERGY));
					}
					else if(muleTypeCombobox.getSelectedIndex() == 3)
					{
						//Buy Ore Mule
						player.buyResourceFromSeller(store, Resource.MULE, Store.mulePrice + Resource.ORE.getMuleTypeScore(), 1);
						player.setMule(new Mule(Resource.ORE));
					}
					player.buyResourceFromSeller(store, Resource.ENERGY, Store.energyPrice, (Integer)energySpinner.getValue());
					player.buyResourceFromSeller(store, Resource.ORE, Store.orePrice, (Integer)oreSpinner.getValue());
					player.buyResourceFromSeller(store, Resource.FOOD, Store.foodPrice, (Integer)foodSpinner.getValue());
				}
				else
				{
					if(muleTypeCombobox.getSelectedIndex() == 1)
					{
						//Sell Mule
						//TODO: base sold price on TYPE of mule (the "FOOD" in below transaction)
						store.buyResourceFromSeller(player, Resource.MULE, Store.mulePrice + Resource.FOOD.getMuleTypeScore(), 1);
						//TODO: take mule from player
					}
					
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
	public StorePanel(Store store, Player player, ActionListener closeListener) {
		this.player = player;
		this.store = store;
		
		setLayout(new GridLayout(5,2));
		
		ChangeListener updateListener = new ChangeListener() {
	        @Override
	        public void stateChanged(ChangeEvent e) {
	            updateStorePanel((JSpinner)e.getSource(), false);
	        }
	    };
	    
	    ActionListener updateListenerAction = new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            updateStorePanel(null, false);
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
		
		this.add(new JLabel("Mule Type"));
		muleTypeCombobox = new JComboBox<String>();
		muleTypeCombobox.addActionListener(updateListenerAction);
		this.add(muleTypeCombobox);
		
		buySellButton.addActionListener(closeListener);
		
		this.add(subtotalLabel);
		this.add(buySellButton);
		
		InventoryListener inventoryListener = new InventoryListener();
		buySellButton.addActionListener(inventoryListener);
		
		updateStorePanel(null, false);
	}
	
	/**
	 * Changes the transaction mode of the store to buy/sell
	 */	
	public void changeMode(boolean isBuying)
	{
		this.isBuying = isBuying;
		
		if(isBuying)
		{
			String[] muleTypeStrings = {"", "Food", "Energy", "Ore"};
			
			muleTypeCombobox.setModel(new javax.swing.DefaultComboBoxModel<String>(muleTypeStrings));
		}
		else
		{
			if(player.hasMule())
			{
				String[] muleTypeStrings = {"", "Sell"};
				muleTypeCombobox.setModel(new javax.swing.DefaultComboBoxModel<String>(muleTypeStrings));
			}
			else
			{
				String[] muleTypeStrings = {""};
				muleTypeCombobox.setModel(new javax.swing.DefaultComboBoxModel<String>(muleTypeStrings));
			}
		}
		
		updateStorePanel(null, false);
	}
	
	/**
	 * Updates spinners and JLabels to reflect model
	 */	
	public void updateStorePanel(JSpinner source, boolean onlyRecalcSubtotal)
	{
		int oreSubtotal;
		int foodSubtotal;
		int energySubtotal;
		int muleSubtotal = 0;
		
		if(isBuying)
		{
			oreSubtotal = (Integer)oreSpinner.getValue() * Store.orePrice;
			foodSubtotal = (Integer)foodSpinner.getValue() * Store.foodPrice;
			energySubtotal = (Integer)energySpinner.getValue() * Store.energyPrice;
			
			if(muleTypeCombobox.getSelectedIndex() == 1)
			{
				muleSubtotal = Store.mulePrice + Resource.FOOD.getMuleTypeScore();
			}
			else if(muleTypeCombobox.getSelectedIndex() == 2)
			{
				muleSubtotal = Store.mulePrice + Resource.ENERGY.getMuleTypeScore();
			}
			else if(muleTypeCombobox.getSelectedIndex() == 3)
			{
				muleSubtotal = Store.mulePrice + Resource.ORE.getMuleTypeScore();
			}
		}
		else
		{
			//TODO: different selling prices?
			oreSubtotal = (Integer)oreSpinner.getValue() * Store.orePrice;
			foodSubtotal = (Integer)foodSpinner.getValue() * Store.foodPrice;
			energySubtotal = (Integer)energySpinner.getValue() * Store.energyPrice;
			
			if(muleTypeCombobox.getSelectedIndex() == 1)
			{
				//TODO: base sold price on TYPE of mule (the "FOOD" in below transaction)
				muleSubtotal = Store.mulePrice + Resource.FOOD.getMuleTypeScore();
			}
		}
		
		if(!onlyRecalcSubtotal)
		{
			int prevResCount;
			int maxResCount;
			
			oreLabel.setText("Ore (" + (isBuying ? store.getOre() : player.getOre()) + ")");
			prevResCount = ((Integer)oreSpinner.getValue()).intValue();
			maxResCount = (isBuying ? Math.max(Math.min((player.getMoney()-foodSubtotal-energySubtotal-muleSubtotal)/Store.orePrice, store.getOre()), 0) : player.getOre());
			if(prevResCount > maxResCount)
			{
				prevResCount = maxResCount;
			}
			System.out.println(prevResCount);
			SpinnerModel oreModel = new SpinnerNumberModel(prevResCount, //initial value
	                0, //min
	                maxResCount, //max
	                1); //step
			oreSpinner.setModel(oreModel);
			
			foodLabel.setText("Food (" + (isBuying ? store.getFood() : player.getFood()) + ")");
			prevResCount = ((Integer)foodSpinner.getValue()).intValue();
			maxResCount = (isBuying ? Math.max(Math.min((player.getMoney()-oreSubtotal-energySubtotal-muleSubtotal)/Store.foodPrice, store.getFood()), 0) : player.getFood());
			if(prevResCount > maxResCount)
			{
				prevResCount = maxResCount;
			}
			
			SpinnerModel foodModel = new SpinnerNumberModel(prevResCount, //initial value
	                0, //min
	                maxResCount, //max
	                1); //step
			foodSpinner.setModel(foodModel);
			
			energyLabel.setText("Energy (" + (isBuying ? store.getEnergy() : player.getEnergy()) + ")");
			prevResCount = ((Integer)energySpinner.getValue()).intValue();
			maxResCount = (isBuying ? Math.max(Math.min((player.getMoney()-oreSubtotal-foodSubtotal-muleSubtotal)/Store.energyPrice, store.getEnergy()), 0) : player.getEnergy());
			if(prevResCount > maxResCount)
			{
				prevResCount = maxResCount;
			}
			
			SpinnerModel energyModel = new SpinnerNumberModel(prevResCount, //initial value
	                0, //min
	                maxResCount, //max
	                1); //step
			energySpinner.setModel(energyModel);
			
			if(source != null)
				source.requestFocus();
			
			updateStorePanel(null, true);
		}
		
		if(onlyRecalcSubtotal)
		{
			subtotalLabel.setText("Subtotal: $" + (oreSubtotal + foodSubtotal + energySubtotal + muleSubtotal));
		}
		
		buySellButton.setText(isBuying ? "Buy" : "Sell");
	}
}
