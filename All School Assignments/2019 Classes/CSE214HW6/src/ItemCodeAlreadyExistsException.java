/**
 * Name: Batuhan Aykac
 * SBU ID: 112813167
 * Recitation: No. 3
 */

/**
 * This is a custom exception class to throw when there is an item code that already exists when adding one.
 */
public class ItemCodeAlreadyExistsException extends Exception{
    public ItemCodeAlreadyExistsException(String errorMessage){
        super(errorMessage);
    }
}