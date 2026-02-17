package sandrone.parser;

import static sandrone.util.DateUtils.FORMATTER;

import java.time.LocalDate;

import sandrone.command.Command;
import sandrone.command.DeleteCommand;
import sandrone.command.DeleteGeneratorCommand;
import sandrone.command.ExitCommand;
import sandrone.command.FindCommand;
import sandrone.command.HelpCommand;
import sandrone.command.MarkCommand;
import sandrone.command.PrintCommand;
import sandrone.command.SyncCommand;
import sandrone.command.UnmarkCommand;
import sandrone.exception.SandroneException;
import sandrone.util.Messages;

/**
 * Handles the parsing of user input strings into executable commands and data types.
 * This utility class (Pulonia) processes text commands for the Sandrone chatbot, ensuring
 * that dates and command parameters are correctly formatted.
 *
 * @author Henry Tse
 * @version 0.1
 */
public class Pulonia {
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
     * Core parsing method that determines the command type and returns the appropriate
     * {@code Command} object.
     *
     * @param userInput The raw line of text entered by the user.
     * @return The executable {@code Command} corresponding to the user's request.
     * @throws SandroneException If the command is unrecognized or improperly formatted.
     */
    public static Command parseCommand(String userInput) throws SandroneException {
        String[] parts = userInput.trim().split("\\s+", 2);
        String commandWord = parts[0];
        String arguments = (parts.length > 1) ? parts[1] : "";

        CommandType type = CommandType.getCommandType(commandWord);
        switch (type) {
        case LIST:
            return new PrintCommand();
        case MARK:
            return new MarkCommand(Parser.parseIndex(arguments));
        case UNMARK:
            return new UnmarkCommand(Parser.parseIndex(arguments));
        case DELETE:
            return new DeleteCommand(Parser.parseIndex(arguments));
        case FIND:
            return new FindCommand(Parser.removeCommandKeyword(userInput, CommandType.FIND.getKeyword()));
        case TODO:
            return TodoParser.parse(arguments);
        case DEADLINE:
            return DeadlineParser.parse(arguments);
        case EVENT:
            return EventParser.parse(arguments);
        case RECUR:
            return RecurParser.parse(arguments);
        case DRECUR:
            return new DeleteGeneratorCommand(Parser.parseIndex(arguments));
        case SYNC:
            return new SyncCommand();
        case HELP:
            return new HelpCommand();
        case BYE:
            return new ExitCommand();
        default:
            throw new SandroneException(Messages.ERROR_INVALID_COMMAND);
        }

    }

}
