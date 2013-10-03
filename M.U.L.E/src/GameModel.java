import java.util.ArrayList;
import java.util.List;


public class GameModel {
	private List<Player> playerList = new ArrayList<>();
	private Difficulty difficulty;
	private Store store;
	//private GameMap gameMap;
	private int numPlayers;
	
	public GameModel() {
		// TODO
	}
	
	/*
	 * adds a player to the playerList
	 * @param player
	 */
	public void addPlayer(Player player) {
		playerList.add(player);
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

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
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
