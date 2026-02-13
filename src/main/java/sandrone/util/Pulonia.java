package sandrone.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import sandrone.command.AddCommand;
import sandrone.command.AddGeneratorCommand;
import sandrone.command.Command;
import sandrone.command.DeleteCommand;
import sandrone.command.DeleteGeneratorCommand;
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
import sandrone.taskgenerators.DeadlineGenerator;
import sandrone.taskgenerators.EventGenerator;
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
    private static final String RECUR_KEYWORD = "recur";
    private static final Period ONE_WEEK = Period.ofWeeks(1);

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
        String[] parts = userInput.trim().split("\\s+");
        if (parts.length < 2) {
            throw new IllegalArgumentException("Please provide an index number.");
        }

        return Integer.parseInt(parts[1]) - 1;
    }

    public static String extractFind(String userInput) {
        return userInput.substring(4).trim();
    }

    private enum CommandType {
        LIST, TODO, DEADLINE, EVENT, MARK, UNMARK,
        DELETE, FIND, BYE, RECUR, DRECUR, DEFAULT;

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
        case TODO:
        case DEADLINE:
        case EVENT:
            return parseAddCommand(userInput);
        case RECUR:
            return parseGeneratorCommand(userInput);
        case DRECUR:
            return new DeleteGeneratorCommand(extractIndex(userInput));
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

    private static String removeCommandKeyword(String userInput, String keyword) {
        assert userInput.contains(keyword) : "remove command is used before checking the command is valid~" + userInput;
        return userInput.substring(keyword.length()).trim();
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
            return parseTodoCommand(userInput);
        } else if (userInput.startsWith(DEADLINE_KEYWORD)) {
            return parseDeadlineCommand(userInput);
        } else if (userInput.startsWith(EVENT_KEYWORD)) {
            return parseEventCommand(userInput);
        } else {
            assert false : "Pulonia failed to properly identify add commands~";
            return null;
        }
    }


    private static AddCommand parseTodoCommand(String userInput) {
        String taskDescription = removeCommandKeyword(userInput, TODO_KEYWORD);
        Task newTodo = getNewTodo(taskDescription);
        return new AddCommand(newTodo);
    }

    private static Todo getNewTodo(String taskDescription) {
        Todo newTodo = new Todo(taskDescription);
        return newTodo;
    }


    private static AddCommand parseDeadlineCommand(String userInput) throws SandroneException {
        String deadlineContents = removeCommandKeyword(userInput, DEADLINE_KEYWORD);

        validateDeadlineFormat(deadlineContents);

        Task newDeadline = getNewDeadline(deadlineContents);
        return new AddCommand(newDeadline);
    }

    private static Deadline getNewDeadline(String deadlineContents) throws SandroneException {
        String[] components = extractDeadlineComponents(deadlineContents);
        String desc = components[0];
        LocalDate dueDate = parseDate(components[1].trim());
        Deadline newDeadline = new Deadline(desc, dueDate);
        return newDeadline;
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


    private static AddCommand parseEventCommand(String userInput) throws SandroneException {
        String eventContents = removeCommandKeyword(userInput, EVENT_KEYWORD);

        validateEventFormat(eventContents);

        Task newEvent = getNewEvent(eventContents);
        return new AddCommand(newEvent);
    }

    private static Event getNewEvent(String eventContents) throws SandroneException {
        String[] components = extractEventComponents(eventContents);
        String desc = components[0];
        LocalDate from = parseDate(components[1]);
        LocalDate to = parseDate(components[2]);

        Event newEvent = new Event(desc, from, to);
        return newEvent;
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

        if (timeParts.length < 2) {
            if (timeParts[0].trim().isEmpty()) {
                throw new SandroneException("Both from and to fields are empty!");
            }
            throw new SandroneException("The to field is empty!");
        }

        if (timeParts[0].trim().isEmpty()) {
            throw new SandroneException("The from field is empty!");
        }

        return new String[] {desc, timeParts[0].trim(), timeParts[1].trim()};
    }

    /**
     * Parses the user input that attempts to add a recurring task (in the form of a task generator)
     * Format of recurring task commnad: recur followed by [todo command / deadline command / event command]
     * Period and nextInitDate defaulted to 1 week and today temporarily.
     *
     * @param userInput The full command string provided by the user.
     * @return The executable {@code Command} corresponding to the user's request.
     */
    public static AddGeneratorCommand parseGeneratorCommand(String userInput) throws SandroneException {
        String remainingCommand = removeCommandKeyword(userInput, RECUR_KEYWORD);

        TaskGenerator newTaskGenerator = null;

        if (remainingCommand.startsWith(TODO_KEYWORD)) {
            String taskDescription = removeCommandKeyword(remainingCommand, TODO_KEYWORD);

            Todo newTodo = getNewTodo(taskDescription);
            newTaskGenerator = new TodoGenerator(newTodo, ONE_WEEK, LocalDate.now());
        } else if (remainingCommand.startsWith(DEADLINE_KEYWORD)) {
            String deadlineContents = removeCommandKeyword(remainingCommand, DEADLINE_KEYWORD);

            Deadline newDeadline = getNewDeadline(deadlineContents);
            newTaskGenerator = new DeadlineGenerator(newDeadline, ONE_WEEK, LocalDate.now());
        } else if (remainingCommand.startsWith(EVENT_KEYWORD)) {
            String eventContents = removeCommandKeyword(remainingCommand, EVENT_KEYWORD);

            Event newEvent = getNewEvent(eventContents);
            newTaskGenerator = new EventGenerator(newEvent, ONE_WEEK, LocalDate.now());
        } else {
            throw new SandroneException("You can only create recurring todo, deadline or events ~");
        }

        return new AddGeneratorCommand(newTaskGenerator);
    }

}
