package sandrone;

import java.util.ArrayList;
import java.util.List;

import sandrone.task.Task;
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
    public void saveTasks(List<Task> tasks) {
        // do nothing
    }

    @Override
    public ArrayList<Task> loadTasks() {
        return new ArrayList<>();
    }
}