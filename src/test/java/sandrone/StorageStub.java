package sandrone;

import java.util.ArrayList;
import sandrone.task.Task;
import sandrone.task.TaskList;
import sandrone.util.Storage;

public class StorageStub extends Storage {
    public StorageStub() {
        super("unused/path");
    }

    @Override
    public void prepareFile() {
        // do nothing
    }

    @Override
    public void saveTasks(TaskList taskList) {
        // do nothing
    }

    @Override
    public ArrayList<Task> loadTasks() {
        return new ArrayList<>();
    }
}