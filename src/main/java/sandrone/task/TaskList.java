package sandrone.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
     * @param listData The storage utility used to retrieve saved task data.
     */
    public TaskList(Storage listData) {
        this.tasks = listData.loadTasks();
    }

    /**
     * Adds a new task to the collection.
     *
     * @param newTask The task to be added.
     */
    public void addTask(Task newTask) {
        this.tasks.add(newTask);
    }

    /**
     * Updates the completion status of a task at the specified index.
     *
     * @param taskIndex The 0-based index of the task in the list.
     * @param isMark {@code true} to mark as completed, {@code false} to unmark.
     */
    public void setTaskStatus(int taskIndex, boolean isMark) {
        checkValidTaskIndex(taskIndex);

        if (isMark) {
            tasks.get(taskIndex).mark();
        } else {
            tasks.get(taskIndex).unmark();
        }
    }

    private void checkValidTaskIndex(int taskIndex) {
        if (taskIndex > tasks.size()) {
            System.out.println("You do not have that many tasks.");
        }
    }

    /**
     * Removes a task from the list based on its index.
     *
     * @param taskIndex The 0-based index of the task to be removed.
     */
    public void deleteTask(int taskIndex) {
        checkValidTaskIndex(taskIndex);
        tasks.remove(taskIndex);
    }

    public List<Task> getAllTasks() {
        return this.tasks;
    }

    public int getTaskCount() {
        return this.tasks.size();
    }

    public Task getTask(int id) {
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
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());
    }
}
