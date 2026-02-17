package sandrone.parser;

import sandrone.exception.SandroneException;
import sandrone.util.Messages;

/**
 * Base class for all specialist parsers.
 * Provides shared utility methods for string manipulation and validation.
 */
public abstract class Parser {

    /**
     * Removes the command keyword from the start of the input.
     *
     * @param userInput The full input string.
     * @param keyword The keyword to remove (e.g., "todo").
     * @return The remaining string trimmed of whitespace.
     */
    protected static String removeCommandKeyword(String userInput, String keyword) {
        assert userInput.contains(keyword) : "remove command is used before checking the command is valid~ ";
        return userInput.substring(keyword.length()).trim();
    }

    /**
     * Extracts and validates a 1-based index from user input.
     *
     * @param args The argument string (e.g., "2").
     * @return The 0-based integer index.
     * @throws SandroneException if the index is missing or invalid.
     */
    protected static int parseIndex(String args) throws SandroneException {
        if (args.isEmpty()) {
            throw new SandroneException(Messages.ERROR_EMPTY_INDEX);
        }
        try {
            int index = Integer.parseInt(args.split("\\s+")[0]) - 1;
            if (index < 0) {
                throw new SandroneException(Messages.ERROR_NONPOSITVE_INDEX);
            }
            return index;
        } catch (NumberFormatException e) {
            throw new SandroneException(Messages.ERROR_NONINTEGER_INDEX);
        }
    }
}
