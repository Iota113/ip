package sandrone.parser;

import sandrone.command.AddCommand;
import sandrone.exception.SandroneException;
import sandrone.task.Task;
import sandrone.task.Todo;
import sandrone.util.Messages;

/**
 * Handles the parsing and validation of input strings specifically for {@link Todo} tasks.
 * As a specialist parser, it ensures that basic tasks are correctly initialized with
 * a non-empty description before being passed to the execution layer.
 *
 * @author Henry Tse
 * @version 0.1
 */
public class TodoParser extends Parser {

    /**
     * Translates raw user arguments into an executable {@link AddCommand}.
     * This serves as the primary entry point used by the main dispatcher (Pulonia).
     *
     * @param arguments The command arguments excluding the keyword (e.g., "read book").
     * @return An {@code AddCommand} configured to add the interpreted Todo task.
     * @throws SandroneException If the task description is empty.
     */
    public static AddCommand parse(String arguments) throws SandroneException {
        String taskDescription = arguments;
        if (taskDescription.isEmpty()) {
            throw new SandroneException(Messages.ERROR_EMPTY_DESCRIPTION);
        }
        Task newTodo = createNewTodo(taskDescription);
        return new AddCommand(newTodo);
    }

    public static Todo createNewTodo(String taskDescription) {
        return new Todo(taskDescription);
    }
}
