package sandrone;

import java.time.LocalDate;

import sandrone.exception.SandroneException;
import sandrone.task.Task;
import sandrone.task.TaskList;
import sandrone.taskgenerators.TaskGenerator;
import sandrone.taskgenerators.TaskGeneratorList;
import sandrone.util.Storage;

/**
 * Manages the current state of the Sandrone application.
 * It acts as a bridge between the task list, the generator rules, and persistent storage,
 * coordinating the synchronization and availability of data.
 */
public class AppState {
    private final TaskList taskList;
    private final TaskGeneratorList generatorList;

    /**
     * Constructs a new AppState by loading existing data from storage.
     *
     * @param storage The storage utility used to initialize task and generator lists.
     */
    public AppState(Storage storage) {
        this.taskList = new TaskList(storage);
        this.generatorList = new TaskGeneratorList(storage);
    }

    /**
     * Retrieves the list of active task instances.
     *
     * @return The {@code TaskList} containing all loaded and newly generated tasks.
     */
    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Retrieves the list of task generator rules.
     *
     * @return The {@code TaskGeneratorList} managing recurrence logic.
     */
    public TaskGeneratorList getGeneratorList() {
        return generatorList;
    }

    /**
     * Synchronizes the task list with the generator rules based on the current date.
     * This method iterates through all generators and spawns new task instances for any
     * missed or current initialization dates, advancing the generator state to prevent
     * duplicate task creation.
     */
    public void generateTasks() throws SandroneException {
        LocalDate today = LocalDate.now();

        for (TaskGenerator gen : generatorList.getAllGenerators()) {
            // While loop handles "catch-up" (e.g., if you didn't open Sandrone for a week)
            while (!gen.getNextInitDate().isAfter(today)) {
                // 1. Generate the Task instance
                Task newTask = gen.createInstance();

                // 2. Add to TaskList
                taskList.addTask(newTask);

                // 3. Advance the generator state (crucial for duplicate prevention!)
                gen.advance();
            }
        }
    }
}
