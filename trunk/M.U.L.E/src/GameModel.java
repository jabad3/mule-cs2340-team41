import java.util.ArrayList;
import java.util.List;


public class GameModel {
	private List<Player> playerList = new ArrayList<>();
	private Difficulty difficulty;
	private Store store;
	private Map map;
	private int numPlayers;
	public int curRound = 1; //TODO: private get/set
	
	public GameModel() {
		// TODO
	}
	
	/* to be used for quick "testing" */
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
	
	/*
	 * adds a player to the playerList
	 * @param player
	 */
	public void addPlayer(Player player) {
		playerList.add(player);
	}
	
	public Difficulty getDifficulty() {
		return difficulty;
	}
	
	/*
	 * sets the GameModel's difficulty
	 * @param difficulty
	 */
	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}
	
	/*
	 * Sets a store to GameModel
	 * @param store
	 */
	public void setStore(Store store) {
			this.store = store;
	}
	
	public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }
    
    public void setMap(Map map) {
        this.map = map;
    }
	
	/*private void sortPlayerList() {
		//sorts the list of players
	}
										//undecided on whether to have this functionality here
	public void getPlayerOrder() {
		sortPlayerList();
		//return playerList
	}
	
	*/
	
	
}
