import javax.swing.ImageIcon;


public enum RaceType {
	HUMAN(600,"humanoid.png"), 
	FLAPPER(1600, "flapper.png"), 
	BONZOID(1000, "bonzoid.png"), 
	UGAITE(1000, "ugaite.png"), 
	BUZZITE(1000, "buzzite.png");
	
	private final int startingMoney;
	private final ImageIcon stockIcon;
	
	private RaceType(int startingMoney, String imageFilePath) {
		this.startingMoney = startingMoney;
		this.stockIcon = new ImageIcon(imageFilePath);
	}
	
	public int startingMoney() {
		return startingMoney;
	}
	
	public ImageIcon getStockImageIcon() {
	    return stockIcon;
	}
	
}
