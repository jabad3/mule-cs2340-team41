import javax.swing.ImageIcon;

/*
 * LandPlotType enum type, each type has a foodRate, energyRate, and oreRate
 * @author Kevin
 */


public enum LandPlotType {
	RIVER(4, 2, 0, "river.png"), 
	PLAIN(2, 3, 1, "m2.png"),
	MTN_1(1, 1, 2, "m2.png"),
	MTN_2(1, 1, 3, "m2.png"),
	MTN_3(1, 1, 4, "m2.png"),
	TOWN(0, 0, 0, "town.png");
	
	private final int foodRate;
	private final int energyRate;
	private final int oreRate;
	private String iconFileName;
	
	private LandPlotType(int foodRate, int energyRate, int oreRate, String iconFileName) {
		this.foodRate = foodRate;
		this.energyRate = energyRate;
		this.oreRate = oreRate;
		this.iconFileName = iconFileName;
		
	}
	
	public int foodRate() {
		return foodRate;
	}
	
	public int energyRate() {
		return energyRate;
	}
	
	public int oreRate() {
		return oreRate;
	}
	
	public ImageIcon getStockImageIcon() {
		return new ImageIcon(iconFileName);
	}
	
}
