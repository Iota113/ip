package sandrone.command;

import sandrone.AppState;
import sandrone.exception.SandroneException;
import sandrone.task.Task;
import sandrone.task.TaskList;
import sandrone.ui.SandroneUi;
import sandrone.util.Storage;

/**
 * Represents a command to unmark a specific task as completed.
 * This class identifies a task by its index and changes its status,
 * followed by updating the storage.
 *
 * @author Henry Tse
 * @version 0.1
 */
public class UnmarkCommand extends Command {
    private int taskIndex;

    /**
     * Constructs a {@code UnmarkCommand} with the specified index.
     *
     * @param taskIndex The 0-based index of the task in the task list.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(AppState appState, SandroneUi ui, Storage storage) throws SandroneException {
        TaskList taskList = appState.getTaskList();
        taskList.setTaskStatus(taskIndex, false);
        boolean isMarked = taskList.getTask(taskIndex).isMarked();
        assert !isMarked : "Task List did not unmark the task properly~";
        storage.saveTasks(taskList);
        Task task = taskList.getTask(taskIndex);
        return ui.showTaskUnmarked(task);
    }
}
