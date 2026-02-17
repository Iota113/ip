package sandrone.parser;

import java.time.LocalDate;

import sandrone.command.AddCommand;
import sandrone.exception.SandroneException;
import sandrone.task.Deadline;
import sandrone.task.Task;
import sandrone.util.Messages;

/**
 * Handles the parsing and validation of input strings specifically for {@link Deadline} tasks.
 * This specialist parser interprets the syntax required to create deadlines, ensuring
 * descriptions and due dates are correctly formatted according to system standards.
 *
 * @author Henry Tse
 * @version 0.1
 */
public class DeadlineParser extends Parser {

    /**
     * Translates raw user arguments into an executable {@link AddCommand}.
     * This method is the primary entry point used by the main dispatcher (Pulonia).
     *
     * @param arguments The command arguments excluding the keyword (e.g., "Return book /by 2026-12-01").
     * @return An {@code AddCommand} configured to add the interpreted deadline.
     * @throws SandroneException If the format is invalid or required components are missing.
     */
    public static AddCommand parse(String arguments) throws SandroneException {
        validateDeadlineFormat(arguments);
        Task newDeadline = createNewDeadline(arguments);
        return new AddCommand(newDeadline);
    }

    private static void validateDeadlineFormat(String userInput) throws SandroneException {
        if (!userInput.contains("/by")) {
            throw new SandroneException(Messages.ERROR_INVALID_DEADLINE_FORMAT);
        }
    }

    /**
     * Creates a new {@link Deadline} object from the provided argument string.
     * This method is made public to allow other parsers, such as the RecurParser,
     * to generate deadline templates for task generators.
     *
     * @param deadlineContents The raw string containing the description and date.
     * @return A fully initialized {@code Deadline} object.
     * @throws SandroneException If the description is empty or the date format is unparseable.
     */
    public static Deadline createNewDeadline(String deadlineContents) throws SandroneException {
        String[] components = extractDeadlineComponents(deadlineContents);
        String desc = components[0];
        LocalDate dueDate = DateParser.parseDate(components[1].trim());
        return new Deadline(desc, dueDate);
    }

    /**
     * Splits the raw command string into its constituent parts: description and date string.
     *
     * @param remainingCommand The arguments string to be partitioned.
     * @return A String array where index 0 is the description and index 1 is the date string.
     * @throws SandroneException If the "/by" delimiter is missing or fields are empty.
     */
    private static String[] extractDeadlineComponents(String remainingCommand) throws SandroneException {
        String[] descTime = remainingCommand.split("/by");

        if (descTime.length == 0) {
            throw new SandroneException(Messages.ERROR_EMPTY_DESCRIPTION_AND_DUEDATE);
        }

        String desc = descTime[0].trim();
        String dueDate = (descTime.length > 1) ? descTime[1].trim() : "";

        if (desc.isEmpty()) {
            throw new SandroneException(Messages.ERROR_EMPTY_DESCRIPTION);
        }

        if (dueDate.isEmpty()) {
            throw new SandroneException(Messages.ERROR_EMPTY_DUEDATE);
        }

        return new String[] {desc, dueDate};
    }

}
