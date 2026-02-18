package sandrone.command;

import sandrone.AppState;
import sandrone.exception.SandroneException;
import sandrone.task.Task;
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
    public String execute(AppState appState, SandroneUi ui, Storage storage) throws SandroneException {
        TaskList taskList = appState.getTaskList();
        taskList.setTaskStatus(taskIndex, true);
        boolean isMarked = taskList.get(taskIndex).isMarked();
        assert isMarked : "Task List did not mark the task properly~";
        storage.saveTasks(taskList);
        Task task = taskList.get(taskIndex);
        return ui.showTaskMarked(task);
    }
}
