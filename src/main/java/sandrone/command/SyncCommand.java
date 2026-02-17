package sandrone.command;


import sandrone.AppState;
import sandrone.exception.SandroneException;
import sandrone.task.TaskList;
import sandrone.taskgenerators.TaskGeneratorList;
import sandrone.ui.SandroneUi;
import sandrone.util.Storage;

/**
 * Represents a command to synchronize the application state by generating task instances.
 * This command triggers the logic that evaluates recurring task rules (generators) against
 * the current date to spawn any due tasks into the active task list.
 */
public class SyncCommand extends Command {
    @Override
    public String execute(AppState appState, SandroneUi ui, Storage storage) throws SandroneException {
        appState.generateTasks();
        TaskList taskList = appState.getTaskList();
        TaskGeneratorList taskGeneratorList = appState.getGeneratorList();
        storage.saveTasks(taskList);
        storage.saveGenerators(taskGeneratorList);
        return "Recurring Tasks added!";
    }
}
