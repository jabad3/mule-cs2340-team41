/*
 * LandPlotType enum type, each type has a foodRate, energyRate, and oreRate
 * @author Kevin
 */


public enum LandPlotType {
	RIVER(4, 2, 0), 
	PLAIN(2, 3, 1),
	MTN_1(1, 1, 2),
	MTN_2(1, 1, 3),
	MTN_3(1, 1, 4),
	TOWN(0, 0, 0);
	
	private final int foodRate;
	private final int energyRate;
	private final int oreRate;
	
	private LandPlotType(int foodRate, int energyRate, int oreRate) {
		this.foodRate = foodRate;
		this.energyRate = energyRate;
		this.oreRate = oreRate;
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
	
	
}
