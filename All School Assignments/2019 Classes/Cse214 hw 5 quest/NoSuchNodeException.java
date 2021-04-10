/**
 * Name:Batuhan Aykac
 * SBU ID: 112813167
 * Recitation: No.3
 */


/**
 * This is a custom exception class to throw when the node doesn't exist.
 */
public class NoSuchNodeException extends Exception {
    public NoSuchNodeException(String errorMessage){
        super(errorMessage);
    }
}
