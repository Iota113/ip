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
    // Introduction and Exit
    public static final String GREETINGS =
            "Look very closely, for standing before you is none other than Marionette.\n"
                    + "Seventh of the Fatui Harbingers.";
    public static final String FAREWELL = "Ad astra abyssosque! Welcome to Nod-Krai, dominion of the Fatui.";

    // Help
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

    // Action Confirmations
    public static final String MESSAGE_TASK_ADDED = "It has been recorded. Try not to forget it.\n";
    public static final String MESSAGE_TASK_DELETED = "Disposed of. It was not worth my data storage anyway.\n";
    public static final String MESSAGE_TASK_MARKED = "I've noted your progress. Is that all?\n";
    public static final String MESSAGE_TASK_UNMARKED = "Hmph. Regressing, are we?\n";
    public static final String MESSAGE_RECURRING_TASK_ADDED = "A repetitive cycle... very well, I shall automate it.\n";
    public static final String MESSAGE_RECURRING_TASK_DELETED = "Hmm, ok. I have removed this recurring task:\n";

    // Error Messages
    public static final String ERROR_INVALID_DATE_FORMAT = "Incorrect date format. Use yyyy-MM-dd. "
            + "I have no time for your sloppy notation";
    public static final String ERROR_DATE_DOES_NOT_EXIST = "That date doesn't exist. Check your calendar.";

    public static final String ERROR_NONINTEGER_INDEX = "I required a number, not whatever gibberish you just provided.";
    public static final String ERROR_NONPOSITVE_INDEX = "Negative indices? Zero? Do you even understand the concept of a list?";
    public static final String ERROR_EMPTY_INDEX = "Am I supposed to guess which task you mean? Provide an index, now.";
    public static final String ERROR_INDEX_OUT_OF_RANGE = "Look at your list. Do you see that index? No. Because it isn't there.";

    public static final String ERROR_INVALID_COMMAND = "You fool. What are you saying\n"
            + "Use \"help\" if you need to see the list of valid commands.";

    public static final String ERROR_EMPTY_DESCRIPTION = "A task without a description? What do you expect me to do with it? "
            + "Give a proper description.";
    public static final String ERROR_INVALID_DEADLINE_FORMAT = "Your deadline command is incomplete. It requires a ' /by ' tag.";
    public static final String ERROR_EMPTY_DESCRIPTION_AND_DUEDATE = "You provided me with neither a description nor the due date?";
    public static final String ERROR_EMPTY_DUEDATE = "You provided a deadline without a duedate.";
    public static final String ERROR_INVALID_EVENT_FORMAT = "Your event syntax is a mess. "
            + "It requires both ' /from ' and ' /to '. Try to keep up.";
    public static final String ERROR_EVENT_DATE_ORDER = "Time flows forward, not backward. Fix your event dates.";
    public static final String ERROR_DUPLCIATE_DESCRIPTION =
            "Excuse me. You already have a task with the same description."
                    + " I am not adding this.";

    public static final String ERROR_RECUR_TYPE = "I can only replicate todos, deadlines, or events. "
            + "Don't test my patience threshold.";
}
