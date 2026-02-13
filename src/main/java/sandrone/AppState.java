package sandrone;

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

//    public void sync(TaskList tasks, TaskGeneratorList generators) {
//        LocalDate today = LocalDate.now();
//
//        for (TaskGenerator gen : generators.getAllGenerators()) {
//            // While loop handles "catch-up" (e.g., if you didn't open Sandrone for a week)
//            while (!gen.getNextInitDate().isAfter(today)) {
//                // 1. Generate the Task instance
//                Task newTask = gen.createInstance();
//
//                // 2. Add to TaskList
//                tasks.addTask(newTask);
//
//                // 3. Advance the generator state (crucial for duplicate prevention!)
//                gen.advance();
//            }
//        }
//    }
}
