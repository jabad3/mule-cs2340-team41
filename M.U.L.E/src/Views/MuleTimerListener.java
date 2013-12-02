package Views;

/**
 * The MuleTimerListener responds to the event when a mule timer has run out
 * of time.
 * 
 * @author Max
 *
 */
public interface MuleTimerListener {

    /**
     * This method is called when the mule timer that is being listened to
     * has run out of time.
     */
    public void muleTimerFinished();
}
