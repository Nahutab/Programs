/**
 * Name: Batuhan Aykac
 * SBU ID: 112813167
 * Recitation no 30: Juan Tarquino
 */

/**
 * This class is a custom exception class which is thrown when the inputted string doesn't match the format.
 */
public class InvalidStringException extends Exception {
    public InvalidStringException(String errorMessage) {
        super(errorMessage);
    }
}
