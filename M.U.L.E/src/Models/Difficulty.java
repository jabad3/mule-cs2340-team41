package Models;

/**
 * Difficulty enum for all possible difficulties in a game of MULE.
 * Has getters for player and store difficulty settings associated with each
 * of the different difficulty options.
 * 
 * @author Kevin
 */
public enum Difficulty {
	BEGINNER(8, 4, 0, 16, 16, 0, 25), 
	STANDARD(4, 2, 0, 8, 8, 8, 14 ), 
	TOURNAMENT(4, 2, 0, 8, 8, 8, 14);
	
	/** Starting food count for Players. */
	private final int playerFood;
	
	/** Starting energy count for Players. */
	private final int playerEnergy;
	
	/** Starting ore count for Players. */
	private final int playerOre;
	
	/** Starting food count for the Store. */
	private final int storeFood;
	
	/** Starting energy count for the Store. */
	private final int storeEnergy;
	
	/** Starting ore count for the Store. */
	private final int storeOre;
	
	/** Starting mule count for the Store */
	private final int storeMuleCount;
	
	/**
	 * Assign appropriate starting resource counts to each Difficulty.
	 * 
	 * @param playerFood Starting food for Players
	 * @param playerEnergy Starting energy for Players
	 * @param playerOre Starting ore for Players
	 * @param storeFood Starting food for Store
	 * @param storeEnergy Starting energy for Store
	 * @param storeOre Starting ore for Store
	 * @param storeMuleCount Starting mule count for Store
	 */
	private Difficulty(int playerFood, int playerEnergy, int playerOre, int storeFood, int storeEnergy, int storeOre, int storeMuleCount) {
		this.playerFood = playerFood;
		this.playerEnergy = playerEnergy;
		this.playerOre = playerOre;
		this.storeFood = storeFood;
		this.storeEnergy = storeEnergy;
		this.storeOre = storeOre;
		this.storeMuleCount = storeMuleCount;
	}
	
	/**
	 * Gets the starting food count for Players.
	 * 
	 * @return Starting food count for Players
	 */
	public int playerFoodSetting() {
		return playerFood;
	}
	
	/**
	 * Gets the starting energy count for Players.
	 * 
	 * @return Starting energy count for Players
	 */
	public int playerEnergySetting() {
		return playerEnergy;
	}
	
	/**
	 * Gets the starting ore count for Players.
	 * 
	 * @return Starting ore count for Players
	 */
	public int playerOreSetting() {
		return playerOre;
	}
	
	/**
	 * Gets the starting food count for the Store.
	 * 
	 * @return Store's starting food count
	 */
	public int storeFoodSetting() {
		return storeFood;
	}
	
	/**
	 * Gets the starting energy count for the Store.
	 * 
	 * @return Store's starting energy count
	 */
	public int storeEnergySetting() {
		return storeEnergy;
	}
	
	/**
	 * Gets the starting ore count for the Store.
	 * 
	 * @return Store's starting ore count
	 */
	public int storeOreSetting() {
		return storeOre;
	}
	
	/**
	 * Gets the starting mule count for the Store.
	 * 
	 * @return Store's starting mule count
	 */
	public int storeMuleSetting() {
		return storeMuleCount;
	}
	
	/**
	 * Returns name of the Difficulty with only the first letter capitalized
	 * for more user-friendly display.
	 */
	public String toString() {
	    String allCaps = this.name();
        String firstLetter = allCaps.substring(0, 1);  // capitalize first letter
        String remainingLetters = allCaps.substring(1).toLowerCase();  // all others are lowercase
        return firstLetter + remainingLetters;
	}
}
