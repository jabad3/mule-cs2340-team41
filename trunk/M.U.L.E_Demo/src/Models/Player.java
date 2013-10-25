package Models;

import java.awt.Color;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import javax.swing.ImageIcon;


public class Player extends Trader implements Comparable<Player> {
    
    /** The player's chosen name */
	private String name;
	
	/** The race of the Player */
	private RaceType race;
	
	/** The color that the Player uses to mark land that it owns */
	private Color color;
	
	/** Holds the land plots that belong to this player. */
	private List<LandPlot> landPlotList = new ArrayList<>();
	
	/** True if the Player has a mule that has not been installed on a plot. */
	private boolean isHoldingMule;
	
	/**
	 * Create a Player object using a name, a RaceType, a Color, and Difficulty.
	 * The Player will have its inventory set to the proper initial amounts,
	 * which depend on both RaceType and Difficulty.
	 * 
	 * @param name The player's name
	 * @param race The player's race
	 * @param color The player's color for marking land plots
	 * @param difficulty The game's set difficulty
	 */
	public Player(String name, RaceType race, Color color, Difficulty difficulty) {
	    this.name = name;
	    this.color = color;
	    this.race = race;
	    
	    // set inventory, which depends on Race, Difficulty
	    int food = difficulty.playerFoodSetting();
	    int energy = difficulty.playerEnergySetting();
	    int ore = difficulty.playerOreSetting();
	    int money = race.startingMoney();
	    int mules = 0;  // players never start out with Mules
	    
	    this.inventory = new Inventory(food, energy, ore, money, mules);
	}
	
	/**
	 * finds the amount of food in a player's inventory
	 * 
	 * @return The amount of food resource in the player's inventory
	 */
	public int getFood() {
		int food = this.inventory.getResourceCount(Resource.FOOD);
		return food; 
	}
	
	@Override
	public void buyMuleFromSeller(Trader seller, Resource muleConfig, int price)
			throws FailedTransactionException {
		// TODO Auto-generated method stub
		
	    // check for enough money
	    // deduct money
	    // add mule to inventory
	}
	
	/**
	 * Get the Player's name
	 * 
	 * @return The Player's name
	 */
	public String getName() {
	    return name;
	}
	
	/**
	 * Get the Player's color
	 * 
	 * @return The Player's color
	 */
    public Color getColor() {
        return color;
    }

    /**
     * Get the icon associated with this player.
     * 
     * @return The image icon for the player
     */
    public ImageIcon getIcon() {
        // TODO At one point may want to customize each icon according to color
        return race.getStockImageIcon();
    }
    
    /**
     * Add a land plot to this player's list of land plots.
     * 
     * Pre-condition:  this player must be the owner of the land plot.
     * 
     * @param landPlot The landplot to add to the player's land plot list
     */
    public void addLandPlot(LandPlot landPlot) {
    	landPlotList.add(landPlot);
    }

	@Override
	public int compareTo(Player otherPlayer) {
	    return this.calculateScore() - otherPlayer.calculateScore();
	}

	/**
	 * Calculates and returns the player's current score using the original
	 * score formula for MULE.
	 * 
	 * Score is dependent on the status of the Player's plots and inventory.
	 * 
	 * @return The player's current score.
	 */
	public int calculateScore() {
		int score = 0;
		for(LandPlot plot : landPlotList) {
			score += plot.calculateScore();
		}
		score += inventory.calculateScore();
		return score;
	}
	
	/**
	 * Checks whether or not the current Player is holding a mule.
	 * 
	 * @return True if the Player is holding a mule;
	 */
	public boolean getMuleHolder() {
	    return isHoldingMule;
	}
	
	
    /**
     * Returns a String containing information for the Player's instance
     * data, as well as information concerning its inventory.
     * Intended to be printed to the console for testing.
     * 
     * @return The Player's information
     */
    public String toString() {
        String s1 = "\nPlayer Name:  " + name;
        String s2 = "\nRace: " + race.name();
        String s3 = "\nColor:  " + color;
        String s4 = "\nMy inventory info... " + inventory.toString();
        String s5 = "\nMy Score is: " + calculateScore();
        return s1 + s2 + s3 + s4 + s5;
    }
    
    /**
     * Returns String representation of inventory.
     * Intended to be printed for simple testing.
     * 
     * @return String representation of inventory
     */
    public String getMyInventoryAsString() {
        return "----------" + name + "'s Inventory--------------" + inventory.toString();
    }
	
}