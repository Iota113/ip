package sandrone.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import sandrone.command.AddCommand;
import sandrone.command.AddGeneratorCommand;
import sandrone.command.Command;
import sandrone.command.DeleteCommand;
import sandrone.command.ExitCommand;
import sandrone.command.FindCommand;
import sandrone.command.MarkCommand;
import sandrone.command.PrintCommand;
import sandrone.command.UnmarkCommand;
import sandrone.exception.SandroneException;
import sandrone.task.Deadline;
import sandrone.task.Event;
import sandrone.task.Task;
import sandrone.task.Todo;
import sandrone.taskgenerators.TaskGenerator;
import sandrone.taskgenerators.TodoGenerator;

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
    private static final String TODO_KEYWORD = "todo";
    private static final String DEADLINE_KEYWORD = "deadline";
    private static final String EVENT_KEYWORD = "event";
    private static final String RECURSE_KEYWORD = "recur";

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
        LIST, TODO, DEADLINE, EVENT, MARK, UNMARK,
        DELETE, FIND, BYE, RECUR, DEFAULT;

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
            return new PrintCommand();
        case MARK:
            return new MarkCommand(extractIndex(userInput));
        case UNMARK:
            return new UnmarkCommand(extractIndex(userInput));
        case DELETE:
            return new DeleteCommand(extractIndex(userInput));
        case FIND:
            return new FindCommand(extractFind(userInput));
        case RECUR:
            return parseGeneratorCommand(userInput);
        case TODO:
        case DEADLINE:
        case EVENT:
            return parseAddCommand(userInput);
        case BYE:
            return new ExitCommand();
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

    /**
     * Interprets user input specific to creating new tasks (Todo, Deadline, Event).
     *
     * @param userInput The full command string provided by the user.
     * @return An {@code AddCommand} containing the initialized task.
     * @throws SandroneException If required components (task description, /by, /from, /to) are missing
     */
    public static AddCommand parseAddCommand(String userInput) throws SandroneException {
        if (userInput.startsWith(TODO_KEYWORD)) {
            return parseAddTodo(userInput);
        } else if (userInput.startsWith(DEADLINE_KEYWORD)) {
            return parseAddDeadline(userInput);
        } else if (userInput.startsWith(EVENT_KEYWORD)) {
            return parseAddEvent(userInput);
        } else {
            assert false : "Pulonia failed to properly identify add commands~";
            return null;
        }
    }

    private static String removeCommandKeyword(String userInput, String keyword) {
        assert userInput.contains(keyword) : "remove command is used before checking the command is valid~";
        return userInput.substring(keyword.length());
    }

    private static AddCommand parseAddTodo(String userInput) {
        String desc = removeCommandKeyword(userInput, TODO_KEYWORD);
        Task newTodo = new Todo(desc);
        return new AddCommand(newTodo);
    }

    private static AddCommand parseAddDeadline(String userInput) throws SandroneException {
        String remainingCommand = removeCommandKeyword(userInput, DEADLINE_KEYWORD);

        validateDeadlineFormat(remainingCommand);

        String[] components = extractDeadlineComponents(remainingCommand);
        String desc = components[0];
        LocalDate dueDate = parseDate(components[1].trim());
        Task newDeadline = new Deadline(desc, dueDate);
        return new AddCommand(newDeadline);
    }

    private static void validateDeadlineFormat(String userInput) throws SandroneException {
        if (!userInput.contains(" /by ")) {
            throw new SandroneException("Incomplete command! A by needs a ' /by ' component.");
        }
    }

    private static String[] extractDeadlineComponents(String remainingCommand) throws SandroneException {
        String[] descTime = remainingCommand.split(" /by ");
        if (descTime.length < 2 || descTime[1].trim().isEmpty()) {
            throw new SandroneException("The by cannot be empty.");
        }

        String desc = descTime[0];
        if (desc.isEmpty()) {
            throw new SandroneException("The description of a task cannot be empty!");
        }

        return new String[] {desc, descTime[1]};
    }

    private static AddCommand parseAddEvent(String userInput) throws SandroneException {
        String remainingCommand = removeCommandKeyword(userInput, EVENT_KEYWORD);

        validateEventFormat(remainingCommand);

        String[] components = extractEventComponents(remainingCommand);
        String desc = components[0];
        LocalDate from = parseDate(components[1]);
        LocalDate to = parseDate(components[2]);

        Task newEvent = new Event(desc, from, to);
        return new AddCommand(newEvent);
    }

    private static void validateEventFormat(String userInput) throws SandroneException {
        boolean hasNoFrom = !userInput.contains(" /from ");
        boolean hasNoTo = !userInput.contains(" /to");

        if (hasNoFrom || hasNoTo) {
            throw new SandroneException("Incomplete command! An sandrone.task.Event needs "
                    + "a ' /from ' and ' /to ' component.");
        }
    }

    private static String[] extractEventComponents(String remainingCommand) throws SandroneException {
        String[] descTime = remainingCommand.split("/from");

        String desc = descTime[0].trim();
        if (desc.isEmpty()) {
            throw new SandroneException("The description of a task cannot be empty!");
        }

        String[] timeParts = descTime[1].split("/to");
        checkEventDates(timeParts);

        return new String[] {desc, timeParts[0].trim(), timeParts[1].trim()};
    }

    private static void checkEventDates(String[] timeParts) throws SandroneException {
        if (timeParts.length < 2) {
            if (timeParts[0].trim().isEmpty()) {
                throw new SandroneException("Both from and to fields are empty!");
            }
            throw new SandroneException("The to field is empty!");
        }

        if (timeParts[0].trim().isEmpty()) {
            throw new SandroneException("The from field is empty!");
        }
    }

    public static AddGeneratorCommand parseGeneratorCommand(String userInput) {
        TaskGenerator newTaskGenerator = new TodoGenerator("TestRecurCommand", Period.ofWeeks(1), LocalDate.now());
        return new AddGeneratorCommand(newTaskGenerator);
    }

}
