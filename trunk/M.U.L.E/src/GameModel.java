import java.util.ArrayList;
import java.util.List;


public class GameModel {
    
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
	public int curRound = 0;
	
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
		// TODO
	}

	/**
	 * Get the list of Players based on score.
	 * The Player with the highest score will be first in the list.
	 * 
	 * @return Sorted players
	 */
	public List<Player> getSortedPlayerList() {
		sortPlayerList();
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
	
}
