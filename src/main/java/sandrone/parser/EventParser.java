package sandrone.parser;

import java.time.LocalDateTime;

import sandrone.command.AddCommand;
import sandrone.exception.SandroneException;
import sandrone.task.Event;
import sandrone.task.Task;
import sandrone.util.Messages;

/**
 * Handles the parsing and validation of input strings specifically for {@link Event} tasks.
 * This specialist parser interprets the syntax required for events, which include
 * a description, a start date, and an end date.
 *
 * @author Henry Tse
 * @version 0.1
 */
public class EventParser extends Parser {

    /**
     * Translates raw user arguments into an executable {@link AddCommand}.
     * This is the primary entry point used by the dispatcher (Pulonia) to process event commands.
     *
     * @param arguments The command arguments excluding the keyword (e.g., "concert /from 2026-02-20 /to 2026-02-21").
     * @return An {@code AddCommand} configured to add the interpreted event.
     * @throws SandroneException If the format is invalid or required components are missing.
     */
    public static AddCommand parse(String arguments) throws SandroneException {
        validateEventFormat(arguments);
        Task newEvent = createNewEvent(arguments);
        return new AddCommand(newEvent);
    }

    private static void validateEventFormat(String userInput) throws SandroneException {
        boolean hasNoFrom = !userInput.contains("/from");
        boolean hasNoTo = !userInput.contains("/to");

        if (hasNoFrom || hasNoTo) {
            throw new SandroneException(Messages.ERROR_INVALID_EVENT_FORMAT);
        }
    }
    /**
     * Splits the raw command string into its constituent parts: description, start date, and end date.
     * Performs granular validation to ensure no field is left blank between delimiters.
     *
     * @param remainingCommand The arguments string to be partitioned.
     * @return A String array where index 0 is description, index 1 is start date, and index 2 is end date.
     * @throws SandroneException If the description is empty or if time-related fields are missing.
     */
    private static String[] extractEventComponents(String remainingCommand) throws SandroneException {
        String[] descTime = remainingCommand.split("/from");

        String desc = descTime[0].trim();
        if (desc.isEmpty()) {
            throw new SandroneException(Messages.ERROR_EMPTY_DESCRIPTION);
        }

        String[] timeParts = descTime[1].split("/to");

        if (timeParts.length < 2) {
            if (timeParts[0].trim().isEmpty()) {
                throw new SandroneException(Messages.ERROR_EMPTY_EVENT_START_AND_END);
            }
            throw new SandroneException(Messages.ERROR_EMPTY_EVENT_END_DATE);
        }

        if (timeParts[0].trim().isEmpty()) {
            throw new SandroneException(Messages.ERROR_EMPTY_EVENT_START_DATE);
        }

        return new String[] {desc, timeParts[0].trim(), timeParts[1].trim()};
    }

    /**
     * Creates a new {@link Event} object from the provided argument string.
     * Validates chronological order to ensure the start date does not occur after the end date.
     * This method is public to allow {@code RecurParser} to generate event templates.
     *
     * @param eventContents The raw string containing description and date boundaries.
     * @return A fully initialized {@code Event} object.
     * @throws SandroneException If fields are empty, date formats are invalid, or start date is after end date.
     */
    public static Event createNewEvent(String eventContents) throws SandroneException {
        String[] components = extractEventComponents(eventContents);
        String desc = components[0].trim();
        LocalDateTime startDate = DateParser.parseDateTime(components[1]);
        LocalDateTime endDate = DateParser.parseDateTime(components[2]);
        if (startDate.isAfter(endDate)) {
            throw new SandroneException(Messages.ERROR_EVENT_DATE_ORDER);
        }

        return new Event(desc, startDate, endDate);
    }
}
