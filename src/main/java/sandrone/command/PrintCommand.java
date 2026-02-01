package sandrone.command;

import sandrone.task.TaskList;
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
    public void execute(TaskList taskList, SandroneUi ui, Storage storage) {
<<<<<<< HEAD
        ui.showTasks(taskList.getAllTasks());
=======
        ui.printTasks(taskList.getAllTasks());
>>>>>>> branch-Level-9
    }
}
