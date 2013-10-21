package Models;

public class Mule {
	private Resource muleType;
	
	public Mule(Resource resource) {
		this.outfit(resource);
	}

	public void outfit(Resource resource) {
		muleType = resource;
	}

	public int getScoreValue() {
		if(muleType == Resource.FOOD)
			return 525;
		else if(muleType == Resource.ENERGY) 
			return 550;
		else if(muleType == Resource.ORE) 
			return 575;
		
		return 0;
	}
}
