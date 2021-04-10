/**
 * Name: Batuhan Aykac
 * SBU ID: 112813167
 * Recitation no 30: Juan Tarquino
 */


/**
 * This is a custom exception class that is thrown when a folder already exists.
 */
public class FolderAlreadyExistsException extends Exception {
    public FolderAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
