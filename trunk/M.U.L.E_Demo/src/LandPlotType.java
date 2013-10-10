import javax.swing.ImageIcon;

/**
 * LandPlotType enum type.
 * Each LandPlotType has a foodRate, energyRate, and oreRate
 * 
 * @author Kevin
 */
public enum LandPlotType {
	RIVER(4, 2, 0, "river.png"), 
	PLAIN(2, 3, 1, "plain.png"),
	MTN_1(1, 1, 2, "m1.png"),
	MTN_2(1, 1, 3, "m2.png"),
	MTN_3(1, 1, 4, "m3.png"),
	TOWN(0, 0, 0, "town.png");
	
	/** Base food production for the land plot type */
	private final int foodRate;
	
	/** Base energy production for the land plot type */
	private final int energyRate;
	
	/** Base ore production for the land plot type */
	private final int oreRate;
	
	/** File name for the stock image file associated with the land plot type */
	private String iconFileName;
	
	/**
	 * Assign appropriate production rates for all land plot types
	 * 
	 * @param foodRate Food production rate
	 * @param energyRate Energy production rate
	 * @param oreRate Ore production rate
	 * @param iconFileName Stock image file name
	 */
	private LandPlotType(int foodRate, int energyRate, int oreRate, String iconFileName) {
		this.foodRate = foodRate;
		this.energyRate = energyRate;
		this.oreRate = oreRate;
		this.iconFileName = iconFileName;
		
	}
	
	/**
	 * Get the food production rate for the land plot type.
	 * 
	 * @return Food production rate
	 */
	public int foodRate() {
		return foodRate;
	}
	
	/**
	 * Get the energy production rate for the land plot type.
	 * 
	 * @return Energy production rate
	 */
	public int energyRate() {
		return energyRate;
	}
	
	/**
	 * Get the ore production rate for the land plot type.
	 * 
	 * @return Ore production rate
	 */
	public int oreRate() {
		return oreRate;
	}
	
	/**
	 * Get the ImageIcon object associated with the land plot type.
	 * 
	 * @return ImageIcon for the land plot type
	 */
	public ImageIcon getStockImageIcon() {
		return new ImageIcon(iconFileName);
	}
	
}
