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
    
    public FailedTransactionException() {
        super();
    }
    
    public FailedTransactionException(String message) {
        super(message);
    }
}
