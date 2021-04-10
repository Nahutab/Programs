/**
 * Name: Batuhan Aykac
 * SBU ID: 112813167
 * Recitation no 30: Juan Tarquino
 */

/**
 * Custom class exception thrown when the price is a negative double.
 */
public class NegativePriceException extends Exception {
    public NegativePriceException(String errorMessage){
        super(errorMessage);
    }
}
