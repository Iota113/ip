package sandrone.command;

import sandrone.task.TaskList;
import sandrone.ui.SandroneUi;
import sandrone.util.Storage;

public class UnmarkCommand extends Command {
    private final int TASK_INDEX;

    public UnmarkCommand(int TASK_INDEX) {
        this.TASK_INDEX = TASK_INDEX;
    }

    @Override
    public void execute(TaskList tasks, SandroneUi ui, Storage storage) {
        tasks.setTaskStatus(TASK_INDEX, false);
        storage.saveTasks(tasks.getAllTasks());
        ui.printUnhappyResponse();
    }
}
