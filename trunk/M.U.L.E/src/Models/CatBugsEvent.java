package Models;

/**
* CatBugsEvent is a subclass of RandomEvent that implements an action method
* that performs this event's action
* 
* @author kevin
*
*/
public class CatBugsEvent extends RandomEvent {

	@Override
	public void eventAction() {
		try {
			player.removeResource(Resource.MONEY, 4); //placeholder! is really 4 * m_value!
		} catch (FailedTransactionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
