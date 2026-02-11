package sandrone.command;

import sandrone.task.TaskList;
import sandrone.ui.SandroneUi;
import sandrone.util.Storage;

/**
 * Represents a command to mark a specific task as completed.
 * This class identifies a task by its index and changes its status,
 * followed by updating the storage.
 *
 * @author Henry Tse
 * @version 0.1
 */
public class MarkCommand extends Command {
    private int taskIndex;

    /**
     * Constructs a {@code MarkCommand} with the specified index.
     *
     * @param taskIndex The 0-based index of the task in the task list.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList taskList, SandroneUi ui, Storage storage) {
        taskList.setTaskStatus(taskIndex, true);
        storage.saveTasks(taskList);
        return ui.getPleasedResponse();
    }
}
