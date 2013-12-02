package Views;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Models.Player;

/**
 * This class is responsible for displaying information for a single player.
 * This information includes:
 *   1) Name
 *   2) Money
 *   3) Food
 *   4) Energy
 *   5) Ore
 *   
 * PlayerInfoPanel displays this information in a horizontal arrangement
 * @author Max
 *
 */
public class PlayerInfoPanel extends JPanel {

    /** Label displaying the player's name. */
    private JLabel nameLabel;
    
    /** Label displaying the money amount. */
    private JLabel moneyLabel;
    
    /** Label displaying the food amount. */
    private JLabel foodLabel;
    
    /** Label displaying the energy amount. */
    private JLabel energyLabel;
    
    /** Label displaying the ore amount. */
    private JLabel oreLabel;
    
    /** Player that information is currently being displayed for. */
    private Player player;
    
    /**
     * Creates a PlayerInfoPanel to display information for a single player.
     * 
     * @param player The player to display information about
     */
    public PlayerInfoPanel(Player player) {
        String name = player.getName();
        int moneyCount = player.getMoney();
        int foodCount = player.getFood();
        int energyCount = player.getEnergy();
        int oreCount = player.getOre();
        
        /* set up labels */
        nameLabel = new JLabel(name);
        moneyLabel = new JLabel("$" + moneyCount);
        foodLabel = new JLabel("FOOD: " + foodCount);
        energyLabel = new JLabel("ENERGY: " + energyCount);
        oreLabel = new JLabel("ORE: " + oreCount);
        
        add(nameLabel);
        add(moneyLabel);
        add(foodLabel);
        add(energyLabel);
        add(oreLabel);
    }
    
    /**
     * Creates an empty PlayerInfoPanel.
     */
    public PlayerInfoPanel() {

        nameLabel = new JLabel("NAME:");
        moneyLabel = new JLabel("$");
        foodLabel = new JLabel("FOOD: ");
        energyLabel = new JLabel("ENERGY: ");
        oreLabel = new JLabel("ORE: ");
        
        add(nameLabel);
        add(moneyLabel);
        add(foodLabel);
        add(energyLabel);
        add(oreLabel);
    }
    
    /**
     * Updates the panel to display the latest information about the
     * current player
     */
    public void refresh() {
        String name = player.getName();
        int moneyCount = player.getMoney();
        int foodCount = player.getFood();
        int energyCount = player.getEnergy();
        int oreCount = player.getOre();

        nameLabel.setText(name);
        moneyLabel.setText("$" + moneyCount);
        foodLabel.setText("FOOD: " + foodCount);
        energyLabel.setText("ENERGY: " + energyCount);
        oreLabel.setText("ORE: " + oreCount);
        
    }
    
    /**
     * Updates the fields to display information for a new player.
     * 
     * @param player The new player to display information for
     */
    public void displayInfoForNewPlayer(Player player) {
        this.player = player;
        refresh();
    }
}
