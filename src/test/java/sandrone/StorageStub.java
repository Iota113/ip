package sandrone;

import java.util.ArrayList;

import sandrone.task.Task;
import sandrone.task.TaskList;
import sandrone.util.Storage;

/**
 * A test stub for the {@code Storage} class used to simulate persistent storage.
 * This class overrides storage operations to perform no actions, allowing unit tests
 * to run without creating or modifying actual files on the local disk.
 */
public class StorageStub extends Storage {
    public StorageStub() {
        super("unused/path", "unused/path");
    }

    @Override
    public void prepareFile(String pathStr) {
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
