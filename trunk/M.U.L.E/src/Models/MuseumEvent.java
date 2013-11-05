package Models;

/**
* MuseumEvent is a subclass of RandomEvent that implements an action method
* that performs this event's action
* 
* Event description:
* THE MUSEUM BOUGHT YOUR ANTIQUE PERSONAL COMPUTER FOR $ 8*m.
* 
* @author kevin
*
*/
public class MuseumEvent extends RandomEvent {
    
    int lastComputerPrice;

	@Override
	public void eventAction(Player player) {
	    int m = 1;  //placeholder! m-value needs to be implemented!
	    int computerPrice = 4 * m;
		player.addResource(Resource.MONEY, 4);
		lastComputerPrice = computerPrice;
	}

    @Override
    public String getResultMessage() {
        String result = "The museum bought your antique personal computer for $"
                        + lastComputerPrice + "!";
        return result;
    }

}