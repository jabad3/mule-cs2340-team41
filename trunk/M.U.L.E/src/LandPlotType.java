/*
 * LandPlotType enum type, each type has a foodRate, energyRate, and oreRate
 * @author Kevin
 */


public enum LandPlotType {
	RIVER(4, 2, 0, "rIcon.png"), 
	PLAIN(2, 3, 1, "pIcon.png"),
	MTN_1(1, 1, 2, "m1Icon.png"),
	MTN_2(1, 1, 3, "m2Icon.png"),
	MTN_3(1, 1, 4, "m3Icon.png"),
	TOWN(0, 0, 0, "tIcon.png");
	
	private final int foodRate;
	private final int energyRate;
	private final int oreRate;
	private String iconName;
	
	private LandPlotType(int foodRate, int energyRate, int oreRate, String iconName) {
		this.foodRate = foodRate;
		this.energyRate = energyRate;
		this.oreRate = oreRate;
		this.iconName = iconName;
		
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
	
	public String iconName() {
		return iconName;
	}
	
}
