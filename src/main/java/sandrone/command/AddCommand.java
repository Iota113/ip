package sandrone.command;

import java.util.ArrayList;

import sandrone.task.Task;
import sandrone.task.TaskList;
import sandrone.ui.SandroneUi;
import sandrone.util.Storage;

public class AddCommand extends Command {
    private Task newTask;

    public AddCommand(Task newtask) {
        this.newTask = newtask;
    }

    @Override
    public void execute(TaskList taskList, SandroneUi ui, Storage storage) {
        taskList.addTask(this.newTask);
        ArrayList<Task> tasks = taskList.getAllTasks();
        storage.saveTasks(tasks);

        int count = taskList.getAllTasks().size();
        String addTaskMessage = "Very well. You have " + count + " task(s) now.\n"
                + count + "."
                + "[" + tasks.get(count - 1).getStatusIcon() + "]"
                + "[" + tasks.get(count - 1).getTaskType() + "] "
                + tasks.get(count - 1).getDescription();
        System.out.println(addTaskMessage);
    }
}
