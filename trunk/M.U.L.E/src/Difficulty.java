/*
 * Difficulty enum type, has getters for player and store difficulty settings
 * @author Kevin
 */

public enum Difficulty {
	BEGINNER(8, 4, 0, 16, 16, 0, 25), 
	STANDARD(4, 2, 0, 8, 8, 8, 14 ), 
	TOURNAMENT(4, 2, 0, 8, 8, 8, 14);
	
	private final int playerFood;
	private final int playerEnergy;
	private final int playerOre;
	private final int storeFood;
	private final int storeEnergy;
	private final int storeOre;
	private final int storeMuleCount;
	
	private Difficulty(int playerFood, int playerEnergy, int playerOre, int storeFood, int storeEnergy, int storeOre, int storeMuleCount) {
		this.playerFood = playerFood;
		this.playerEnergy = playerEnergy;
		this.playerOre = playerOre;
		this.storeFood = storeFood;
		this.storeEnergy = storeEnergy;
		this.storeOre = storeOre;
		this.storeMuleCount = storeMuleCount;
	}
	
	public int playerFoodSetting() {
		return playerFood;
	}
	
	public int playerEnergySetting() {
		return playerEnergy;
	}
	
	public int playerOreSetting() {
		return playerOre;
	}
	
	public int storeFoodSetting() {
		return storeFood;
	}
	
	public int storeEnergySetting() {
		return storeEnergy;
	}
	
	public int storeOreSetting() {
		return storeOre;
	}
	
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
