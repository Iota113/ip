package sandrone.command;

import sandrone.task.TaskList;
import sandrone.ui.SandroneUi;
import sandrone.util.Storage;

public abstract class Command {
    public abstract void execute(TaskList taskList, SandroneUi ui, Storage storage);
}
