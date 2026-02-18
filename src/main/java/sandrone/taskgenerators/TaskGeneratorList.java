package sandrone.taskgenerators;

import java.util.ArrayList;
import java.util.List;

import sandrone.exception.SandroneException;
import sandrone.util.Messages;
import sandrone.util.Storage;

/**
 * Manages the collection of task generators in the Sandrone chatbot.
 * Mirroing the Task List class, this is essentially a wrapper class for
 * an ArrayList that provides methods to add, delete, find, modify the status
 * of tasks generators and call on them to create an instance of a task.
 *
 * @author Henry Tse
 * @version 0.2
 */
public class TaskGeneratorList {
    private ArrayList<TaskGenerator> taskGenerators;

    public TaskGeneratorList(Storage storage) {
        this.taskGenerators = storage.loadGenerators();
    }

    private void checkValidIndex(int taskIndex) throws SandroneException {
        if (taskIndex > taskGenerators.size()) {
            throw new SandroneException(Messages.ERROR_INDEX_OUT_OF_RANGE);
        }
    }

    /**
     * Adds a new task generator to the collection.
     *
     * @param newTaskGenerator The task generator to be added.
     */
    public void add(TaskGenerator newTaskGenerator) {
        this.taskGenerators.add(newTaskGenerator);
    }

    public List<TaskGenerator> getAll() {
        return this.taskGenerators;
    }

    /**
     * Removes and returns the task at the specified index from the task generator list.
     *
     * @param index The 0-based index of the task generator to be removed.
     * @return The {@code TaskGenerator} object that was removed.
     * @throws SandroneException If the provided index is out of bounds.
     */
    public TaskGenerator delete(int index) throws SandroneException {
        checkValidIndex(index);
        return this.taskGenerators.remove(index);
    }

    public int getCount() {
        return this.taskGenerators.size();
    }

}
