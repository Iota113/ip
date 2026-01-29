package sandrone.command;

import sandrone.task.TaskList;
import sandrone.ui.SandroneUi;
import sandrone.util.Storage;

public class PrintCommand extends Command {
    @Override
    public void execute(TaskList taskList, SandroneUi ui, Storage storage) {
        ui.printTasks(taskList.getAllTasks());
    }
}
