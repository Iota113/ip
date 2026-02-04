package sandrone.command;

import sandrone.task.TaskList;
import sandrone.ui.SandroneUi;
import sandrone.util.Storage;

/**
 * Represents an executable command initiated by the user.
 * This abstract class serves as the foundation for all specific command types
 * within the Sandrone chatbot.
 *
 * @author Henry Tse
 * @version 0.1
 */
public abstract class Command {
    /**
     * Executes the specific logic associated with the command.
     * Subclasses must implement this method to perform actions such as
     * adding tasks, deleting tasks, or displaying the task list.
     *
     * @param taskList The collection of tasks to be operated upon.
     * @param ui The user interface used to interact with or provide feedback to the user.
     * @param storage The file handler used to save or load task data.
     */
    public abstract String execute(TaskList taskList, SandroneUi ui, Storage storage);

    /**
     * Returns true if this command should terminate the application.
     */
    public boolean isExit() {
        return false;
    }
}
