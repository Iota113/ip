package sandrone.exception;

/**
 * Represents exceptions specific to the Sandrone chatbot.
 * This class is used to signal errors related to user input,
 * command parsing, and task management.
 *
 * @author Henry Tse
 * @version 0.1
 */
public class SandroneException extends Exception {

    /**
     * Constructs a {@code SandroneException} with the specified detail message.
     *
     * @param message The specific error message to be displayed to the user.
     */
    public SandroneException(String message) {
        super(message);
    }
}
