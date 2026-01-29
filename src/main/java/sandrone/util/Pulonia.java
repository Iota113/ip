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


public class Pulonia {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDate parseDate(String input) throws SandroneException {
        try {
            return LocalDate.parse(input, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new SandroneException("Use the format yyyy-MM-dd (e.g., 2026-01-28) for dates.");
        }
    }

    public static String formatDate(LocalDate date) {
        return date.format(FORMATTER);
    }

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
