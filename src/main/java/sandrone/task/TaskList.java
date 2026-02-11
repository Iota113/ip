package sandrone.task;

import java.util.ArrayList;

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
     * @param id The 0-based index of the task in the list.
     * @param isMark {@code true} to mark as completed, {@code false} to unmark.
     */
    public void setTaskStatus(int id, boolean isMark) {
        if (id > tasks.size()) {
            System.out.println("You do not have that many tasks.");
        }

        if (isMark) {
            tasks.get(id).mark();
        } else {
            tasks.get(id).unmark();
        }
    }

    /**
     * Removes a task from the list based on its index.
     *
     * @param id The 0-based index of the task to be removed.
     * @return A message confirming the deletion.
     */
    public String deleteTask(int id) {
        tasks.remove(id);
        return "Your task has been deleted.";
    }

    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }

    public int getTasksCount() {
        return this.tasks.size();
    }

    public Task getTask(int id) {
        return this.tasks.get(id);
    }

    /**
     * Filters the task list for tasks containing the specified keyword.
     *
     * @param userInput The keyword to search for within task descriptions.
     * @return A list of tasks that match the search criteria.
     */
    public ArrayList<Task> getMatchingTasks(String userInput) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.getDescription().contains(userInput)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
