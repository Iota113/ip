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
}
