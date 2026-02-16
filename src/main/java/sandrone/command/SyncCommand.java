package sandrone.command;


import sandrone.AppState;
import sandrone.exception.SandroneException;
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
        return "Recurring Tasks added!";
    }
}
