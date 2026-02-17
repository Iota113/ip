package sandrone.parser;

import java.time.LocalDate;
import java.time.Period;

import sandrone.command.AddGeneratorCommand;
import sandrone.exception.SandroneException;
import sandrone.taskgenerators.DeadlineGenerator;
import sandrone.taskgenerators.EventGenerator;
import sandrone.taskgenerators.TaskGenerator;
import sandrone.taskgenerators.TodoGenerator;
import sandrone.util.Messages;

/**
 * Handles the parsing of commands for recurring tasks by generating task templates.
 * This class acts as a sub-dispatcher that delegates the parsing of specific task details
 * to specialist parsers like {@link TodoParser}, {@link DeadlineParser}, and {@link EventParser}.
 *
 * @author Henry Tse
 * @version 0.1
 */
public class RecurParser extends Parser {
    private static final Period ONE_WEEK = Period.ofWeeks(1);

    /**
     * Parses the user input that attempts to add a recurring task (in the form of a task generator)
     * Format of recurring task commnad: recur followed by [todo command / deadline command / event command]
     * Period and nextInitDate defaulted to 1 week and today temporarily.
     *
     * @param arguments The part of the command after "recur" is removed.
     * @return The executable {@code Command} corresponding to the user's request.
     */
    public static AddGeneratorCommand parse(String arguments) throws SandroneException {
        String[] parts = arguments.trim().split("\\s+", 2);
        String subCommandWord = parts[0];
        String subArgs = (parts.length > 1) ? parts[1] : "";

        CommandType type = CommandType.getCommandType(subCommandWord);
        TaskGenerator newTaskGenerator;

        switch (type) {
        case TODO:
            newTaskGenerator = new TodoGenerator(
                    TodoParser.createNewTodo(subArgs), ONE_WEEK, LocalDate.now());
            break;
        case DEADLINE:
            newTaskGenerator = new DeadlineGenerator(
                    DeadlineParser.createNewDeadline(subArgs), ONE_WEEK, LocalDate.now());
            break;
        case EVENT:
            newTaskGenerator = new EventGenerator(
                    EventParser.createNewEvent(subArgs), ONE_WEEK, LocalDate.now());
            break;
        default:
            throw new SandroneException(Messages.ERROR_RECUR_TYPE);
        }

        return new AddGeneratorCommand(newTaskGenerator);
    }

}
