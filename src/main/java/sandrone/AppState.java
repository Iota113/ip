package sandrone;

import java.time.LocalDate;

import sandrone.task.Task;
import sandrone.taskgenerators.TaskGenerator;
import sandrone.taskgenerators.TaskGeneratorList;
import sandrone.task.TaskList;
import sandrone.util.Storage;

public class AppState {
    private final TaskList taskList;
    private final TaskGeneratorList generatorList;

    public AppState(Storage storage) {
        this.taskList = new TaskList(storage);
        this.generatorList = new TaskGeneratorList(storage);
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public TaskGeneratorList getGeneratorList() {
        return generatorList;
    }

    public void generateTasks() {
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
