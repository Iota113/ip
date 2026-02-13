package sandrone.command;


import sandrone.AppState;
import sandrone.ui.SandroneUi;
import sandrone.util.Storage;

public class SyncCommand extends Command {
    @Override
    public String execute(AppState appState, SandroneUi ui, Storage storage) {
        appState.generateTasks();
        return "Recurring Tasks added!";
    }
}
