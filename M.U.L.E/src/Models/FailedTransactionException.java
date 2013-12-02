package Models;


/**
 * A FailedTransactionException that should be thrown whenever a Trader
 * attempts to make a trade that could not happen.
 * 
 * @author Max
 *
 */
@SuppressWarnings("serial")
public class FailedTransactionException extends Exception {
    
    /**
     * Create a FailedTransactionException without any message.
     */
    public FailedTransactionException() {
        super();
    }
    
    /**
     * Create a FailedTransactionException with a message.
     * 
     * @param message Message associated with the exception.
     */
    public FailedTransactionException(String message) {
        super(message);
    }
}
