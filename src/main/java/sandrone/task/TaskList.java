package sandrone.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import sandrone.exception.SandroneException;
import sandrone.util.Messages;
import sandrone.util.Storage;

/**
 * Manages the collection of tasks in the Sandrone chatbot.
 * This is essentially a wrapper class for an ArrayList that provides methods to
 * add, delete, find, and modify the status of tasks.
 *
 * @author Henry Tse
 * @version 0.1
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a {@code TaskList} by loading existing tasks from storage.
     *
     * @param storage The storage utility used to retrieve saved task data.
     */
    public TaskList(Storage storage) {
        this.tasks = storage.loadTasks();
    }

    private void checkValidTaskIndex(int taskIndex) throws SandroneException {
        if (taskIndex > tasks.size()) {
            throw new SandroneException(Messages.ERROR_INDEX_OUT_OF_RANGE);
        }
    }

    /**
     * Adds a new task to the collection.
     *
     * @param newTask The task to be added.
     */
    public void add(Task newTask) throws SandroneException {
        if (tasks.contains(newTask)) {
            throw new SandroneException(Messages.ERROR_DUPLCIATE_DESCRIPTION);
        }
        tasks.add(newTask);
    }

    /**
     * Removes and returns the task at the specified index from the task list.
     *
     * @param taskIndex The 0-based index of the task to be removed.
     * @return The {@code Task} object that was removed.
     * @throws SandroneException If the provided index is out of bounds.
     */
    public Task delete(int taskIndex) throws SandroneException {
        checkValidTaskIndex(taskIndex);
        return this.tasks.remove(taskIndex);
    }

    /**
     * Updates the completion status of a task at the specified index.
     *
     * @param taskIndex The 0-based index of the task in the list.
     * @param isMark {@code true} to mark as completed, {@code false} to unmark.
     */
    public Task setTaskStatus(int taskIndex, boolean isMark) throws SandroneException {
        checkValidTaskIndex(taskIndex);

        if (isMark) {
            tasks.get(taskIndex).mark();
        } else {
            tasks.get(taskIndex).unmark();
        }

        return tasks.get(taskIndex);
    }

    public List<Task> getAll() {
        return this.tasks;
    }

    public int getCount() {
        return this.tasks.size();
    }

    public Task get(int id) {
        return this.tasks.get(id);
    }

    /**
     * Filters the task list for tasks containing the specified keyword.
     *
     * @param keyword The keyword to search for within task descriptions.
     * @return A list of tasks that match the search criteria.
     */
    public List<Task> getMatchingTasks(String keyword) {
        return this.tasks.stream()
                .filter(task -> task.getTaskDescription().contains(keyword))
                .collect(Collectors.toList());
    }
}
