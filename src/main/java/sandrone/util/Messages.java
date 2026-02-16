package sandrone.util;

/**
 * Container for all user-visible strings and error messages used by the Sandrone chatbot.
 * <p>
 * This class centralizes all text output to ensure consistency across the application
 * and to simplify the process of editing or localizing messages. It follows a
 * constant-only pattern and should not be instantiated.
 * </p>
 *
 * @author Henry Tse
 * @version 0.1
 */
public class Messages {
    public static final String MESSAGE_VALID_COMMANDS = "Here are the valid commands:\n"
            + "1. todo <description> \n"
            + "2. deadline <description> /by <date>\n"
            + "3. event <description> /from <date> /to <date>\n"
            + "4. delete <index>\n"
            + "5. mark / unmark <index>\n"
            + "6. list\n"
            + "7. find <description>\n"
            + "8. recur <todo / deadline / event command>\n"
            + "9. drecur <index>\n"
            + "10. sync\n"
            + "11. bye";

    // Error Messages
    public static final String ERROR_INVALID_COMMAND = "You fool. What are you saying\n"
            + "Use \"help\" if you need to see the list of valid commands.";
    public static final String ERROR_INVALID_DATE_FORMAT = "Use the format yyyy-MM-dd (e.g., 2026-01-28) for dates.";
    public static final String ERROR_DATE_DOES_NOT_EXIST = "That date doesn't exist. Please check your calendar!";
    public static final String ERROR_EMPTY_INDEX = "Please provide an index number.";
    public static final String ERROR_INVALID_DEADLINE = "Incomplete command! A by needs a ' /by ' component.";
    public static final String ERROR_EMPTY_BY = "The by cannot be empty.";
    public static final String ERROR_EMPTY_DESCRIPTION = "The description of a task cannot be empty!";
    public static final String ERROR_INVALID_EVENT_FORMAT = "Incomplete command! An Event needs a ' /from ' and ' /to ' component.";
    public static final String ERROR_DATE_ORDER = "The start date of an event cannot be after the end date!";
    public static final String ERROR_RECUR_TYPE = "You can only create recurring todo, deadline or events ~";
    public static final String ERROR_DUPLCIATE_DESCRIPTION = "Excuse me. You already have a task with the same description. I am not adding this.";
    public static final String ERROR_NONINTEGER_INDEX = "Please provide a valid number for the index!";
    public static final String ERROR_NONPOSITVE_INDEX = "Indices must be 1 or greater!";
    public static final String ERROR_INDEX_OUT_OF_RANGE = "You do not have that many tasks!";
}
