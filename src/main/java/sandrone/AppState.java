package sandrone;

import sandrone.taskgenerators.TaskGeneratorList;
import sandrone.task.TaskList;
import sandrone.util.Storage;

public class AppState {
    private final TaskList taskList;
    private final TaskGeneratorList generatorList;

    public AppState(Storage listData) {
        this.taskList = new TaskList(listData);
        this.generatorList = new TaskGeneratorList();
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public TaskGeneratorList getGeneratorList() {
        return generatorList;
    }
}
