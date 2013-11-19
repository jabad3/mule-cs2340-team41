package Views;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

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
 * 
 * @author Tim
 */
public class StorePanel extends JPanel {
    /** Label for ore */
    private JLabel oreLabel = new JLabel();

    /** Spinner for ore */
    public JSpinner oreSpinner;

    /** Label for food */
    private JLabel foodLabel = new JLabel();

    /** Spinner for food */
    public JSpinner foodSpinner;

    /** Label for energy */
    private JLabel energyLabel = new JLabel();

    /** Spinner for energy */
    public JSpinner energySpinner;

    /** Label for mule */
    private JLabel muleLabel = new JLabel();

    /** Combo box to select mule type */
    public JComboBox<String> muleTypeCombobox;

    /** Label for subtotal */
    private JLabel subtotalLabel = new JLabel();

    /** Button to complete transaction */
    private JButton buySellButton = new JButton();

    /** The player that is currently interacting with the store */
    private Player player;

    /** The store that is currently interacting with the player */
    private Store store;

    /** Tracks whether player is buying or selling from/to store */
    private boolean isBuying;

    /**
     * This class updates a player's inventory when the buy button is pressed
     * 
     * @author Erica
     */
    private class InventoryListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    try {
		if (isBuying == true) {
		    // TODO: NEED TO HANDLE MULE AND MULE TYPE
		    // TODO: make dialog box (in DevelopmentStage) close when
		    // button is clicked

		    if (muleTypeCombobox.getSelectedIndex() == 1) {
			// Buy Food Mule
			player.buyResourceFromSeller(
				store,
				Resource.MULE,
				Store.mulePrice
					+ Resource.FOOD.getMuleTypeScore(), 1);
			player.setMule(new Mule(Resource.FOOD));
		    } else if (muleTypeCombobox.getSelectedIndex() == 2) {
			// Buy Energy Mule
			player.buyResourceFromSeller(
				store,
				Resource.MULE,
				Store.mulePrice
					+ Resource.ENERGY.getMuleTypeScore(), 1);
			player.setMule(new Mule(Resource.ENERGY));
		    } else if (muleTypeCombobox.getSelectedIndex() == 3) {
			// Buy Ore Mule
			player.buyResourceFromSeller(
				store,
				Resource.MULE,
				Store.mulePrice
					+ Resource.ORE.getMuleTypeScore(), 1);
			player.setMule(new Mule(Resource.ORE));
		    }
		    player.buyResourceFromSeller(store, Resource.ENERGY,
			    Store.energyPrice,
			    (Integer) energySpinner.getValue());
		    player.buyResourceFromSeller(store, Resource.ORE,
			    Store.orePrice, (Integer) oreSpinner.getValue());
		    player.buyResourceFromSeller(store, Resource.FOOD,
			    Store.foodPrice, (Integer) foodSpinner.getValue());
		} else {
		    if (muleTypeCombobox.getSelectedIndex() == 1) {
			// Sell Mule
			store.buyResourceFromSeller(player, Resource.MULE,
				Store.mulePrice
					+ player.getMule().getMuleType()
						.getMuleTypeScore(), 1);
			player.setMule(null);
		    }

		    store.buyResourceFromSeller(player, Resource.ENERGY,
			    Store.energyPrice,
			    (Integer) energySpinner.getValue());
		    store.buyResourceFromSeller(player, Resource.ORE,
			    Store.orePrice, (Integer) oreSpinner.getValue());
		    store.buyResourceFromSeller(player, Resource.FOOD,
			    Store.foodPrice, (Integer) foodSpinner.getValue());
		}
	    } catch (FailedTransactionException e1) {
		e1.printStackTrace();
	    }
	}
    }

    /**
     * Creates the StorePanel's interface
     * 
     * @param store
     *            The store to transact with
     * @param player
     *            The Player to transact with
     * @param closeListener
     *            Listener for when the panel needs to be closed
     */
    public StorePanel(Store store, Player player, ActionListener closeListener) {
	this.player = player;
	this.store = store;

	setLayout(new GridLayout(5, 2));

	ChangeListener updateListener = new ChangeListener() {
	    @Override
	    public void stateChanged(ChangeEvent e) {
		updateStorePanel((JSpinner) e.getSource(), false);
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

	this.add(muleLabel);
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
     * 
     * @param isBuying
     *            True indicates that the transaction mode is "buying"
     */
    public void changeMode(boolean isBuying) {
	this.isBuying = isBuying;

	updateStorePanel(null, false);
    }

    /**
     * Updates spinners and JLabels to reflect model
     */
    public HashMap<Resource, Integer> getResSubtotals() {
	HashMap<Resource, Integer> resSubtotals = new HashMap<Resource, Integer>();

	if (isBuying) {
	    resSubtotals.put(Resource.ORE, (Integer) oreSpinner.getValue()
		    * Store.orePrice);
	    resSubtotals.put(Resource.FOOD, (Integer) foodSpinner.getValue()
		    * Store.foodPrice);
	    resSubtotals.put(Resource.ENERGY,
		    (Integer) energySpinner.getValue() * Store.energyPrice);

	    if (muleTypeCombobox.getSelectedItem() != null) {
		if (muleTypeCombobox.getSelectedItem().equals("Food")) {
		    resSubtotals.put(Resource.MULE, Store.mulePrice
			    + Resource.FOOD.getMuleTypeScore());
		} else if (muleTypeCombobox.getSelectedItem().equals("Energy")) {
		    resSubtotals.put(Resource.MULE, Store.mulePrice
			    + Resource.ENERGY.getMuleTypeScore());
		} else if (muleTypeCombobox.getSelectedItem().equals("Ore")) {
		    resSubtotals.put(Resource.MULE, Store.mulePrice
			    + Resource.ORE.getMuleTypeScore());
		} else {
		    resSubtotals.put(Resource.MULE, 0);
		}
	    } else {
		resSubtotals.put(Resource.MULE, 0);
	    }
	} else {
	    // TODO: different selling prices?
	    resSubtotals.put(Resource.ORE, (Integer) oreSpinner.getValue()
		    * Store.orePrice);
	    resSubtotals.put(Resource.FOOD, (Integer) foodSpinner.getValue()
		    * Store.foodPrice);
	    resSubtotals.put(Resource.ENERGY,
		    (Integer) energySpinner.getValue() * Store.energyPrice);

	    if (muleTypeCombobox.getSelectedItem() != null
		    && muleTypeCombobox.getSelectedItem().equals("Sell")) {
		resSubtotals.put(Resource.MULE, Store.mulePrice
			+ player.getMule().getMuleType().getMuleTypeScore());
	    } else {
		resSubtotals.put(Resource.MULE, 0);
	    }
	}

	return resSubtotals;
    }

    /**
     * Updates spinners and JLabels to reflect model
     */
    private void updateStorePanel(JSpinner source, boolean onlyRecalcSubtotal) {
	HashMap<Resource, Integer> resSubtotals = getResSubtotals();

	if (!onlyRecalcSubtotal) {
	    int prevResCount;
	    int maxResCount;

	    oreLabel.setText("Ore ("
		    + (isBuying ? store.getOre() : player.getOre()) + ")");
	    prevResCount = ((Integer) oreSpinner.getValue()).intValue();
	    maxResCount = (isBuying ? Math.max(Math.min(
		    (player.getMoney() - resSubtotals.get(Resource.FOOD)
			    - resSubtotals.get(Resource.ENERGY) - resSubtotals
				.get(Resource.MULE)) / Store.orePrice,
		    store.getOre()), 0) : player.getOre());
	    if (prevResCount > maxResCount) {
		prevResCount = maxResCount;
	    }

	    SpinnerModel oreModel = new SpinnerNumberModel(prevResCount, // initial
									 // value
		    0, // min
		    maxResCount, // max
		    1); // step
	    oreSpinner.setModel(oreModel);

	    foodLabel.setText("Food ("
		    + (isBuying ? store.getFood() : player.getFood()) + ")");
	    prevResCount = ((Integer) foodSpinner.getValue()).intValue();
	    maxResCount = (isBuying ? Math.max(Math.min(
		    (player.getMoney() - resSubtotals.get(Resource.ORE)
			    - resSubtotals.get(Resource.ENERGY) - resSubtotals
				.get(Resource.MULE)) / Store.foodPrice,
		    store.getFood()), 0) : player.getFood());
	    if (prevResCount > maxResCount) {
		prevResCount = maxResCount;
	    }

	    SpinnerModel foodModel = new SpinnerNumberModel(prevResCount, // initial
									  // value
		    0, // min
		    maxResCount, // max
		    1); // step
	    foodSpinner.setModel(foodModel);

	    energyLabel
		    .setText("Energy ("
			    + (isBuying ? store.getEnergy() : player
				    .getEnergy()) + ")");
	    prevResCount = ((Integer) energySpinner.getValue()).intValue();
	    maxResCount = (isBuying ? Math.max(Math.min(
		    (player.getMoney() - resSubtotals.get(Resource.ORE)
			    - resSubtotals.get(Resource.FOOD) - resSubtotals
				.get(Resource.MULE)) / Store.energyPrice,
		    store.getEnergy()), 0) : player.getEnergy());
	    if (prevResCount > maxResCount) {
		prevResCount = maxResCount;
	    }

	    SpinnerModel energyModel = new SpinnerNumberModel(prevResCount, // initial
									    // value
		    0, // min
		    maxResCount, // max
		    1); // step
	    energySpinner.setModel(energyModel);

	    if (source != null)
		source.requestFocus();

	    muleLabel.setText("Mule ("
		    + (isBuying ? store.getMules() : (player.hasMule() ? "1"
			    : "0")) + ")");

	    int prevSelIndex = muleTypeCombobox.getSelectedIndex();

	    if (isBuying) {
		ArrayList<String> muleTypeStrings = new ArrayList<String>(4);
		muleTypeStrings.add("");

		if (store.getMules() > 0 && !player.hasMule()) {
		    int leftoverMoney = Math.max(
			    player.getMoney() - resSubtotals.get(Resource.ORE)
				    - resSubtotals.get(Resource.FOOD)
				    - resSubtotals.get(Resource.ENERGY), 0);
		    if (leftoverMoney > Store.mulePrice
			    + Resource.FOOD.getMuleTypeScore()) {
			muleTypeStrings.add("Food");
		    }
		    if (leftoverMoney > Store.mulePrice
			    + Resource.ENERGY.getMuleTypeScore()) {
			muleTypeStrings.add("Energy");
		    }
		    if (leftoverMoney > Store.mulePrice
			    + Resource.ORE.getMuleTypeScore()) {
			muleTypeStrings.add("Ore");
		    }
		}
		muleTypeCombobox
			.setModel(new javax.swing.DefaultComboBoxModel<String>(
				muleTypeStrings
					.toArray(new String[muleTypeStrings
						.size()])));
	    } else {
		if (player.hasMule()) {
		    String[] muleTypeStrings = { "", "Sell" };
		    muleTypeCombobox
			    .setModel(new javax.swing.DefaultComboBoxModel<String>(
				    muleTypeStrings));
		} else {
		    String[] muleTypeStrings = { "" };
		    muleTypeCombobox
			    .setModel(new javax.swing.DefaultComboBoxModel<String>(
				    muleTypeStrings));
		}
	    }

	    muleTypeCombobox.setSelectedIndex(prevSelIndex);

	    updateStorePanel(null, true);
	}

	if (onlyRecalcSubtotal) {
	    subtotalLabel.setText("Subtotal: $"
		    + (resSubtotals.get(Resource.ORE)
			    + resSubtotals.get(Resource.FOOD)
			    + resSubtotals.get(Resource.ENERGY) + resSubtotals
				.get(Resource.MULE)));
	}

	buySellButton.setText(isBuying ? "Buy" : "Sell");
    }
}
