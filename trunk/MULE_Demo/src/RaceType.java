
public enum RaceType {
	HUMAN(600), 
	FLAPPER(1600), 
	BONZOID(1000), 
	UGAITE(1000), 
	BUZZITE(1000);
	
	private final int startingMoney;
	
	private RaceType(int startingMoney) {
		this.startingMoney = startingMoney;
	}
	
	public int startingMoney() {
		return startingMoney;
	}
	
}
