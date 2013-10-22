package Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class GameModel {
    
    /** The round number of the final round. */
    private final int FINAL_ROUND = 12;
    
    /** Holds all Player objects in the game. */
	private List<Player> playerList = new ArrayList<>();
	
	/** The chosen difficulty for the game. */
	private Difficulty difficulty;
	
	/** The store model object that participates in all transactions. */
	private Store store;
	
	/** The map model object. */
	private Map map;
	
	/** Number of players in the game. */
	private int numPlayers;
	
	/** The current round of the game. */
	private int currentRound = 0;
	
	/** The food requirement for players each round. */
	private java.util.Map<Integer,Integer> foodRequirements;
	
	public GameModel()
	{
		foodRequirements = new HashMap<Integer,Integer>();
		foodRequirements.put(1, 3); //key = round, value = # of foods
		foodRequirements.put(2, 3);
		foodRequirements.put(3, 3);
		foodRequirements.put(4, 3);
		foodRequirements.put(5, 4);
		foodRequirements.put(6, 4);
		foodRequirements.put(7, 4);
		foodRequirements.put(8, 4);
		foodRequirements.put(9, 5);
		foodRequirements.put(10, 5);
		foodRequirements.put(11, 5);
		foodRequirements.put(12, 5);
	}
	
	/**
	 * Add a Player to playerList.
	 * 
	 * @param player The new Player object to add to the list
	 */
	public void addPlayer(Player player) {
		playerList.add(player);
	}
	
	/**
	 * Get the difficulty.
	 * 
	 * @return Difficulty of the game
	 */
	public Difficulty getDifficulty() {
		return difficulty;
	}
	
	/**
	 * Sets the difficulty.
	 * 
	 * @param difficulty Difficulty chosen for the game.
	 */
	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}
	
	/**
	 * Sets the store.
	 * 
	 * @param store An initialized Store to be used for the game
	 */
	public void setStore(Store store) {
			this.store = store;
	}
	
	/**
	 * Get the number of Players in the game.
	 * 
	 * @return Number of players
	 */
	public int getNumPlayers() {
        return numPlayers;
    }

	/**
	 * Set the number of Players in the game.
	 * 
	 * @param numPlayers Number of players
	 */
    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }
    
    /**
     * Set the map model object.
     * 
     * @param map The instantiated map to use in the game
     */
    public void setMap(Map map) {
        this.map = map;
    }
	
    /**
     * Sort the list of Players based on score.
     * The Player with the highest score will be first in the list.
     */
	private void sortPlayerList() {
		Collections.sort(playerList);
	}

	/**
	 * Get the list of Players based on score.
	 * The Player with the highest score will be first in the list.
	 * 
	 * @return Sorted players
	 */
	public List<Player> getSortedPlayerList() {
		return playerList;
	}

	/**
	 * Get the map model object.
	 * 
	 * @return The map
	 */
    public Map getMap() {
        return map;
    }
    
    /**
     * Returns a String containing details about instance data.
     * Intended to be used for print-testing.
     */
    public String toString() {
        String s2 = "\nDifficulty:  " + difficulty;
        String s3 = "\nStore:  " + store;
        String s4 = "\nMap:  " + map;
        String s5 = "\nNumber of Players:  " + numPlayers;
        String s6 = "\n\n Player info.......";
        for (Player p: playerList)
            s6 += p.toString();
        return s2 + s3 + s4 + s5 + s6;
    }

    /**
     * Returns the Store object.
     * 
     * @return The store used in the game
     */
    public Store getStore() {
        return store;
    }
    
    /**
     * Returns the current round number.
     * 
     */
	public int getCurrentRound() {
	    return currentRound;
	}
	
	/**
	 * Increments the current round number.
	 */
	public void incrementRound() {
	    currentRound++;
	}
	
	/**
	 * Update the player order by sorting according to score.
	 */
	public void updatePlayerOrder() {
	    sortPlayerList();
	}

	/**
	 * Calculates the current player's turn time for the current round
	 * 
	 * @param player The current player
	 * 
	 * @return the number of seconds a player's turn should last
	 */
	public int calculateTurnTime(Player player) {
		int returnval = 0;
		int playerFood = player.getFood();
		int requiredFood = foodRequirements.get(currentRound);
		if(playerFood >= requiredFood)
			returnval = 50;
		else if(playerFood == 0)
			returnval = 5;
		else
			returnval = 30;
		return returnval * 1000;
	}
	
	/**
	 * determines whether or not the game is over
	 * 
	 * @return true if the game is over
	 */
    public boolean gameIsOver() {
        return currentRound > FINAL_ROUND;
    }
}
