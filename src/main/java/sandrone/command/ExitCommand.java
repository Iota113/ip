package sandrone.command;

import sandrone.AppState;
import sandrone.ui.SandroneUi;
import sandrone.util.Storage;

/**
 * Represents a command to exit the Sandrone application.
 *
 * @author Henry Tse
 * @version 0.1
 */
public class ExitCommand extends Command {

    @Override
    public String execute(AppState appState, SandroneUi ui, Storage storage) {
        return ui.getFarewell();
    }
    @Override
    public boolean isExit() {
        return true;
    }
}
