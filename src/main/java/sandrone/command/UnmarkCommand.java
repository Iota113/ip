package sandrone.command;

import sandrone.task.TaskList;
import sandrone.ui.SandroneUi;
import sandrone.util.Storage;

public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, SandroneUi ui, Storage storage) {
        taskList.setTaskStatus(taskIndex, false);
        storage.saveTasks(taskList.getAllTasks());
        ui.printUnhappyResponse();
    }
}
