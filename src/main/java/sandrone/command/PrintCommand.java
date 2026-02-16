package sandrone.command;

import sandrone.AppState;
import sandrone.task.TaskList;
import sandrone.taskgenerators.TaskGeneratorList;
import sandrone.ui.SandroneUi;
import sandrone.util.Storage;

/**
 * Represents a command to print all tasks in the task list.
 * It retrieves all tasks from the task list and passes it to SandroneUi to print.
 *
 * @author Henry Tse
 * @version 0.1
 */
public class PrintCommand extends Command {
    @Override
    public String execute(AppState appState, SandroneUi ui, Storage storage) {
        TaskList taskList = appState.getTaskList();
        TaskGeneratorList taskGeneratorList = appState.getGeneratorList();
        return ui.getDebugState(taskList.getAllTasks(), taskGeneratorList.getAllGenerators());
    }
}
