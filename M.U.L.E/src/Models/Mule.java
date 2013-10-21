package Models;

public class Mule {
	private Resource muleType;
	private int scoreValue;


	public Mule(Resource resource) {
		this.outfit(resource);
	}

	public void outfit(Resource resource) {
		muleType = resource;
		setScoreValue();
	}

	public int getScoreValue() {
		return scoreValue;
	}

	private void setScoreValue() {
		if(muleType == Resource.FOOD)
			scoreValue = 525;
		else if(muleType == Resource.ENERGY) 
			scoreValue = 550;
		else if(muleType == Resource.ORE) 
			scoreValue = 575;
	}
}
