package sandrone.command;

import sandrone.task.TaskList;
import sandrone.ui.SandroneUi;
import sandrone.util.Storage;

public abstract class Command {
    public abstract void execute(TaskList tasks, SandroneUi ui, Storage storage);
}
