package sandrone.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import sandrone.command.AddCommand;
import sandrone.command.Command;
import sandrone.command.DeleteCommand;
import sandrone.command.FindCommand;
import sandrone.command.MarkCommand;
import sandrone.command.PrintCommand;
import sandrone.command.UnmarkCommand;
import sandrone.exception.SandroneException;
import sandrone.task.Deadline;
import sandrone.task.Event;
import sandrone.task.Task;
import sandrone.task.Todo;

/**
 * Handles the parsing of user input strings into executable commands and data types.
 * This utility class (Pulonia) processes text commands for the Sandrone chatbot, ensuring
 * that dates and command parameters are correctly formatted.
 *
 * @author Henry Tse
 * @version 0.1
 */
public class Pulonia {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Parses a date string into a {@code LocalDate} object.
     *
     * @param input The date string in the format yyyy-MM-dd.
     * @return A {@code LocalDate} representation of the input.
     * @throws SandroneException If the input does not match the expected format.
     */
    public static LocalDate parseDate(String input) throws SandroneException {
        try {
            return LocalDate.parse(input, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new SandroneException("Use the format yyyy-MM-dd (e.g., 2026-01-28) for dates.");
        }
    }

    /**
     * Converts a {@code LocalDate} object into its standard string representation.
     *
     * @param date The date to format.
     * @return A string formatted as yyyy-MM-dd.
     */
    public static String formatDate(LocalDate date) {
        return date.format(FORMATTER);
    }

    /**
     * Interprets user input specific to creating new tasks (Todo, Deadline, Event).
     *
     * @param userInput The full command string provided by the user.
     * @return An {@code AddCommand} containing the initialized task.
     * @throws SandroneException If required components (task description, /by, /from, /to) are missing
     */
    public static Command parseAddCommand(String userInput) throws SandroneException {
        if (userInput.startsWith("todo")) {
            Task newTodo = new Todo(userInput.replace("todo", "").trim());
            return new AddCommand(newTodo);
        } else if (userInput.startsWith("deadline")) {
            if (!userInput.contains(" /by ")) {
                throw new SandroneException("Incomplete command! A by needs a ' /by ' component.");
            }

            String[] parts = userInput.split(" /by ");
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new SandroneException("The by cannot be empty.");
            }

            String desc = parts[0].substring(8).trim();
            if (desc.isEmpty()) {
                throw new SandroneException("The task cannot be empty.");
            }

            LocalDate by = parseDate(parts[1]);
            Task newDeadline = new Deadline(desc, by);
            return new AddCommand(newDeadline);
        } else if (userInput.startsWith("event")) {
            if (!(userInput.contains(" /from ") && userInput.contains(" /to"))) {
                throw new SandroneException("Incomplete command! An sandrone.task.Event needs "
                        + "a ' /from ' and ' /to ' component.");
            }

            // Remove "event"
            String command = userInput.substring(5).trim();
            String[] descParts = command.split("/from");
            String desc = descParts[0].trim();
            if (desc.isEmpty()) {
                throw new SandroneException("The task description is empty!");
            }

            String[] timeParts = descParts[1].split("/to");
            if (timeParts.length < 2) {
                if (timeParts[0].trim().isEmpty()) {
                    throw new SandroneException("Both from and to fields are empty!");
                }
                throw new SandroneException("The to field is empty!");
            }

            String fromString = timeParts[0].trim();
            if (fromString.isEmpty()) {
                throw new SandroneException("The from field is empty!");
            }

            String toString = timeParts[1].trim();

            LocalDate from = parseDate(fromString);
            LocalDate to = parseDate(toString);

            Task newEvent = new Event(desc, from, to);
            return new AddCommand(newEvent);
        } else {
            return null;
        }
    }

    /**
     * Extracts the integer index from a user command (e.g. "mark 2").
     * Converts the 1-based index provided by the user to a 0-based index for internal use.
     *
     * @param userInput The command string containing an index.
     * @return The 0-based integer index.
     */
    public static int extractIndex(String userInput) {
        return Integer.parseInt(userInput.split(" ")[1]) - 1;
    }

    public static String extractFind(String userInput) {
        return userInput.substring(4).trim();
    }

    private enum CommandType {
        LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, FIND, DEFAULT;

        public static CommandType getCommandType(String userInput) {
            if (userInput == null) {
                return DEFAULT;
            }
            try {
                return valueOf(userInput.split(" ")[0].toUpperCase());
            } catch (IllegalArgumentException e) {
                return DEFAULT;
            }
        }
    }

    /**
     * Core parsing method that determines the command type and returns the appropriate
     * {@code Command} object.
     *
     * @param userInput The raw line of text entered by the user.
     * @return The executable {@code Command} corresponding to the user's request.
     * @throws SandroneException If the command is unrecognized or improperly formatted.
     */
    public static Command parseCommand(String userInput) throws SandroneException {
        CommandType commandType = CommandType.getCommandType(userInput);
        switch (commandType) {
        case LIST:
            System.out.println("MAMBO");
            return new PrintCommand();
        case MARK:
            System.out.println("MARK");
            return new MarkCommand(extractIndex(userInput));
        case UNMARK:
            return new UnmarkCommand(extractIndex(userInput));
        case DELETE:
            return new DeleteCommand(extractIndex(userInput));
        case FIND:
            return new FindCommand(extractFind(userInput));
        case TODO:
        case DEADLINE:
        case EVENT:
            return parseAddCommand(userInput);
        default:
            String message =
                    "You Fool. What are you saying.\n"
                            + "Here are the valid commands:\n"
                            + "1. todo [insert desc] \n"
                            + "2. deadline [insert desc] /by [insert time]\n"
                            + "3. event [insert desc] /from [insert time] /to [insert time]\n"
                            + "4. mark/unmark [insert index]\n"
                            + "5. list";
            throw new SandroneException(message);
        }

    }
}
