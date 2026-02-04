package sandrone.command;

import sandrone.task.TaskList;
import sandrone.ui.SandroneUi;
import sandrone.util.Storage;

public class ExitCommand extends Command {

    @Override
    public String execute(TaskList taskList, SandroneUi ui, Storage storage) {
        return ui.getFarewell();
    }
    @Override
    public boolean isExit() {
        return true;
    }
}
