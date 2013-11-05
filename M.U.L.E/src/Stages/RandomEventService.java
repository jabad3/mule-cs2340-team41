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
	
	private boolean eventIsHappening() {
		if(rand.nextInt(101) <= 27)
			return true;
		else
			return false;
	}
	
	public void startService(Player player, boolean isInLastPlace) { 
		this.player = player;
		
		if(eventIsHappening() && isInLastPlace) {
			RandomEvent event = factory.buildGoodEvent();
			event.eventAction();
		}
		else if(eventIsHappening() && !isInLastPlace) {
			RandomEvent event = factory.buildFromAllEvent();
			event.eventAction();
	
		}	
	}
		
	
}
