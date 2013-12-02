package Models;

import javax.swing.ImageIcon;

/**
 * RaceType represents the different kinds of races that a Player can in the
 * game of MULE.
 * 
 * Because a Player's starting inventory relies partly on their race, RaceTypes
 * must contain information related to initial money amounts for each race.
 * Similarly, RaceTypes also contain information regarding Player appearance,
 * as a Player's race is the primary factor for their on-screen appearance. 
 * 
 * @author Max
 *
 */
public enum RaceType {
	HUMAN(600,"humanoid.png"), 
	FLAPPER(1600, "flapper.png"), 
	BONZOID(1000, "bonzoid.png"), 
	UGAITE(1000, "ugaite.png"), 
	BUZZITE(1000, "buzzite.png");
	
	/** The amount of money that members of a given race start the game with. */
	private final int startingMoney;
	
	/** The default image icon associated with a particular RaceType. */
	private final ImageIcon stockIcon;
	
	/**
	 * Create a RaceType.
	 * 
	 * @param startingMoney The starting money for this race
	 * @param imageFilePath The path to the image file that will be used to
	 * generate an image icon for this race
	 */
	private RaceType(int startingMoney, String imageFilePath) {
		this.startingMoney = startingMoney;
		this.stockIcon = new ImageIcon(imageFilePath);
	}
	
	/**
	 * Get the starting money amounts associated with a particular RaceType.
	 * 
	 * @return Starting money amount for this RaceType
	 */
	public int startingMoney() {
		return startingMoney;
	}
	
	/**
	 * Get the stock (default) image icon associated with a particular
	 * RaceType.
	 * 
	 * @return The stock image icon for this RaceType
	 */
	public ImageIcon getStockImageIcon() {
	    return stockIcon;
	}
	
	/**
	 * Returns the RaceType name keeping only the first letter capitalized
	 * for more user-friendly display.
	 */
	public String toString() {
	    String allCaps = this.name();
	    String firstLetter = allCaps.substring(0, 1);  // capitalize first letter
	    String remainingLetters = allCaps.substring(1).toLowerCase();  // all others are lowercase
	    return firstLetter + remainingLetters;
	}
	
}
