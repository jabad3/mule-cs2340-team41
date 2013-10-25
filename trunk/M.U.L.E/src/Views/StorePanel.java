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
    
	/**
	 * Creates the ColorPanel, adding ActionListeners to
	 * the buttons and buttons to the JPanel
	 */
	public StorePanel(){
		setLayout(new GridLayout(5,2));
		
		ChangeListener updateListener = new ChangeListener() {
	        @Override
	        public void stateChanged(ChangeEvent e) {
	            Update((JSpinner)e.getSource());
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
		
		Update(null);
	}
	
	/**
	 * Updates spinners and JLabels to reflect model
	 */	
	public void Update(JSpinner source)
	{
		int storerescount[] = {10, 111, 12};
		int playerrescount[] = {7, 8, 9};
		int playermoney = 500;
		boolean isbuying = true;
		
		int oreSubtotal;
		int foodSubtotal;
		int energySubtotal;
		
		if(isbuying)
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
		
		oreLabel.setText("Ore (" + (isbuying ? storerescount[0] : playerrescount[0]) + ")");
		SpinnerModel oreModel = new SpinnerNumberModel(((Integer)oreSpinner.getValue()).intValue(), //initial value
                0, //min
                (isbuying ? Math.min((playermoney-foodSubtotal-energySubtotal)/Store.orePrice, storerescount[0]) : playerrescount[0]), //max
                1); //step
		oreSpinner.setModel(oreModel);
		
		foodLabel.setText("Food (" + (isbuying ? storerescount[1] : playerrescount[1]) + ")");
		SpinnerModel foodModel = new SpinnerNumberModel(((Integer)foodSpinner.getValue()).intValue(), //initial value
                0, //min
                (isbuying ? Math.min((playermoney-oreSubtotal-energySubtotal)/Store.foodPrice, storerescount[1]) : playerrescount[1]), //max
                1); //step
		foodSpinner.setModel(foodModel);
		
		energyLabel.setText("Energy (" + (isbuying ? storerescount[2] : playerrescount[2]) + ")");
		SpinnerModel energyModel = new SpinnerNumberModel(((Integer)energySpinner.getValue()).intValue(), //initial value
                0, //min
                (isbuying ? Math.min((playermoney-oreSubtotal-foodSubtotal)/Store.energyPrice, storerescount[2]) : playerrescount[2]), //max
                1); //step
		energySpinner.setModel(energyModel);
		
		if(source != null)
			source.requestFocus();
		
		subtotalLabel.setText("Subtotal: $" + (oreSubtotal + foodSubtotal + energySubtotal));
		buySellButton.setText(isbuying ? "Buy" : "Sell");
	}
	
	/**
     * To test the view.
     * 
     * @param args
     */
    public static void main(String[] args) {
        JFrame jf = new JFrame("Test Store View");
        final StorePanel storePanel = new StorePanel();
        
        jf.getContentPane().add(storePanel);
        jf.pack();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        
    }
}
