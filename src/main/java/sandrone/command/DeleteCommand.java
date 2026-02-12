package sandrone.command;

import sandrone.AppState;
import sandrone.task.TaskList;
import sandrone.ui.SandroneUi;
import sandrone.util.Storage;

/**
 * Represents a command to remove a task from the task list.
 * This class handles the logic of identifying a task by its index,
 * removing it from the collection, and updating the local storage.
 *
 * @author Henry Tse
 * @version 0.1
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Constructs a {@code DeleteCommand} with the specified index.
     *
     * @param taskIndex The 0-based index of the task to be removed from the list.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(AppState appState, SandroneUi ui, Storage storage) {
        TaskList taskList = appState.getTaskList();

        int oldTasksCount = taskList.getTasksCount();
        taskList.deleteTask(taskIndex);

        int newTasksCount = taskList.getTasksCount();
        assert newTasksCount == oldTasksCount - 1 : "The size of the Tasks ArrayList is not updated properly~";

        storage.saveTasks(taskList);
        return ui.getPleasedResponse();
    }

}
