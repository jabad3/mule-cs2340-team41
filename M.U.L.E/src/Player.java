import java.awt.Color;
import java.util.EnumMap;


public class Player extends Trader {
	private String name;
	private RaceType race;
	private Color color;
	private EnumMap<InputType, Boolean> keyStates;
	
	/**
	 * Create a Player object using a name, a RaceType, and a Color.
	 * 
	 * @param name The player's name
	 * @param race The player's race
	 * @param color The player's color for marking land plots
	 * @param difficulty The game's set difficulty
	 */
	public Player(String name, RaceType race, Color color, Difficulty difficulty) {
	    this.name = name;
	    this.color = color;
	    
	    // set inventory, which depends on Race, Difficulty
	    int food = difficulty.playerFoodSetting();
	    int energy = difficulty.playerEnergySetting();
	    int ore = difficulty.playerOreSetting();
	    int money = race.startingMoney();
	    int mules = 0;  // players never start out with Mules
	    
	    this.inventory = new Inventory(food, energy, ore, money, mules);
	}
	
	public int calculateScore() {
		// TODO
		return 0; 
	}
	
	@Override
	public void buyMuleFromSeller(Trader seller, Resource muleConfig, int price)
			throws FailedTransactionException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void buyLandFromSeller(Trader seller, int price)
			throws FailedTransactionException {
		// TODO Auto-generated method stub
		
	}
	
}
