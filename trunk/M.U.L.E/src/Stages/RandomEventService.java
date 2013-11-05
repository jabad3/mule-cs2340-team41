package Stages;
import Models.*; //fix this!
import java.util.List;
import java.util.Random;

public class RandomEventService {
	private RandomEventFactory factory = new RandomEventFactory();
	private Player player;
	//private GameModel gameModel;
	//private List<Player> playerList;
	Random rand = new Random();
	
	public RandomEventService(Player player) {
		this.player = player;
	}
	
	private boolean eventIsHappening() {
		if(rand.nextInt(101) <= 27)
			return true;
		else
			return false;
	}
	
	/*public void startService() { 
	
		if(eventIsHappening()) {
			factory.
	}*/
		
	
}
