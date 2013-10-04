import java.awt.Color;
import java.util.EnumMap;


public class Player extends Trader {
	private String name;
	private RaceType race;
	private Color color;
	private EnumMap<InputType, Boolean> keyStates;
	
	/**
	 * Create a Player object using only a given difficulty.
	 * Name, race, and color fields will all default to null and will
	 * need to be set using appropriate setters.
	 * 
	 * @param difficulty The game's set difficulty
	 */
	public Player(Difficulty difficulty) {
	    this(null, null, null, difficulty);
	}
	
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
	    
	    this.inventory = new Inventory(difficulty.playerFoodSetting(), difficulty.playerEnergySetting(), 0, 0, 0);
	    // Difficulty affects starting food, energy, and ore

	    // Race affects starting money
	    setRace(race);
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
	
	public void setName(String name) {
	    this.name = name;
	}
	
	public void setColor(Color color) {
	    this.color = color;
	}
	
	/**
	 * Sets the Player's race and makes sure to set its money count to
	 * match that of the corresponding race.
	 * 
	 * @param race
	 */
	public void setRace(RaceType race) {
	    setMoneyToZero();
	    depositMoney(race.startingMoney());  
	}
	
	private void setMoneyToZero() {
	    int currentBalance = inventory.getResourceCount(Resource.MONEY);
	    inventory.withdrawMoney(currentBalance);
	}
	
}
