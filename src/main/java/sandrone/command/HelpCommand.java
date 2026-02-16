package sandrone.command;

import sandrone.AppState;
import sandrone.ui.SandroneUi;
import sandrone.util.Messages;
import sandrone.util.Storage;

/**
 * Represents a command to print all valid commands for user to see.
 *
 * @author Henry Tse
 * @version 0.1
 */
public class HelpCommand extends Command {
    @Override
    public String execute(AppState appState, SandroneUi ui, Storage storage) {
        return Messages.MESSAGE_VALID_COMMANDS;
    }
}
