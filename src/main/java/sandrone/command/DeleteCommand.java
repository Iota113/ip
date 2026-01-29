package sandrone.command;

import sandrone.task.TaskList;
import sandrone.ui.SandroneUi;
import sandrone.util.Storage;

public class DeleteCommand extends Command {
    private final int TASK_INDEX;

    public DeleteCommand(int TASK_INDEX) {
        this.TASK_INDEX = TASK_INDEX;
    }

    @Override
    public void execute(TaskList taskList, SandroneUi ui, Storage storage) {
        taskList.deleteTask(TASK_INDEX);
        storage.saveTasks(taskList.getAllTasks());
        ui.printPleasedResponse();
    }

}
