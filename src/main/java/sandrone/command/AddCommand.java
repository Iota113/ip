package sandrone.command;

import java.util.ArrayList;

import sandrone.task.Task;
import sandrone.task.TaskList;
import sandrone.ui.SandroneUi;
import sandrone.util.Storage;

/**
 * Represents a command to add a new task to the chatbot's task list.
 * This class handles the logic of appending a task, triggering a save
 * to local storage, and providing feedback to the user.
 *
 * @author Henry Tse
 * @version 0.1
 */
public class AddCommand extends Command {
    private Task newTask;

    /**
     * Constructs an {@code AddCommand} with the task to be processed.
     *
     * @param newTask The specific {@code Task} object (Todo, Deadline, or Event)
     *                that this command will add to the list upon execution.
     */
    public AddCommand(Task newTask) {
        this.newTask = newTask;
    }

    @Override
    public void execute(TaskList taskList, SandroneUi ui, Storage storage) {
        taskList.addTask(this.newTask);
        ArrayList<Task> tasks = taskList.getAllTasks();
        storage.saveTasks(tasks);

        int count = taskList.getAllTasks().size();
        ui.showTaskAdded(this.newTask, count);
    }
}
