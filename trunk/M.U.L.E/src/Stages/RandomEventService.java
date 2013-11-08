package Stages;
import Models.Player;
import RandomEvents.RandomEvent;
import RandomEvents.RandomEventFactory;

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
		
		/// PLACE HOLDER!!!!!!!!!!!  GRAB M FROM TABLE!!! ///
		int m = 500;
		/// PLACE HOLDER!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ///
		
		if(eventIsHappening() && isInLastPlace) {
			RandomEvent event = factory.buildGoodEvent();
			event.eventAction(player, m);
		}
		else if(eventIsHappening() && !isInLastPlace) {
			RandomEvent event = factory.buildFromAllEvent();
			event.eventAction(player, m);
	
		}	
	}
		
	
}
