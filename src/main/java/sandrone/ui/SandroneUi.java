package sandrone.ui;

import java.util.List;

import sandrone.task.Task;
import sandrone.taskgenerators.TaskGenerator;

/**
 * Handles the user interface and interactions for the Sandrone chatbot.
 * This class is responsible for displaying messages, formatting output,
 * and presenting task information to the user in a stylized manner.
 *
 * @author HenryTse
 * @version 0.1
 */
public class SandroneUi {
    private static final String greetings =
            "Look very closely, for standing before you is none other than Marionette.\n"
                    + "Seventh of the Fatui Harbingers.";
    private static final String farewell = "Ad astra abyssosque! Welcome to Nod-Krai, dominion of the Fatui.";
    private static final String pleasedResponse = "Very well.";
    private static final String unhappyResponse = "Utterly risible.";

    public String getGreetings() {
        return greetings;
    }

    public String getFarewell() {
        return farewell;
    }

    /**
     * Converts a list of objects into a numbered, formatted string.
     * @param items The list to display.
     * @param header The title for the list (e.g., "Your list:").
     * @param emptyMsg The message to return if the list is empty.
     * @return A formatted string.
     */
    private <T> String listToString(List<T> items, String header, String emptyMsg) {
        if (items.isEmpty()) {
            return emptyMsg;
        }

        StringBuilder sb = new StringBuilder(header).append("\n");
        for (int i = 0; i < items.size(); i++) {
            sb.append(i + 1).append(". ").append(items.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Prints a formatted list of matching tasks
     *
     * @param tasks   The list of tasks to be displayed.
     */
    public String getMatchingTasks(List<Task> tasks) {
        return listToString(tasks, "Here are the tasks that contain the keyword:", "No such task exists!");
    }

    /**
     * Prints all active tasks and recurring tasks.
     *
     * @param tasks The list of active tasks to be displayed.
     * @param generators The list of recurring tasks to be displayed.
     */
    public String getAll(List<Task> tasks, List<TaskGenerator> generators) {
        return listToString(tasks, "[Your Active Tasks]", "No tasks in memory.") + "\n"
                + listToString(generators, "[Recurring Tasks]", "No generators active.");
    }

    /**
     * Displays a confirmation message when a task is successfully added.
     * The message includes the current total task count and a stylized
     * representation of the newly added task.
     *
     * @param task The task that has been added to the list.
     * @param totalCount The new total number of tasks in the list.
     */
    public String showTaskAdded(Task task, int totalCount) {
        StringBuilder sb = new StringBuilder("Very well. You have " + totalCount + " task(s) now.");
        sb.append(task);
        return sb.toString();
    }

    /**
     * Displays a confirmation message when a task generator is successfully added.
     * The message includes the current total task count and a stylized
     * representation of the newly added task.
     *
     * @param taskGenerator The task gnerator that has been added to the list.
     * @param totalCount The new total number of tasks in the list.
     */
    public String showTaskGeneratorAdded(TaskGenerator taskGenerator, int totalCount) {
        StringBuilder sb = new StringBuilder("Very well. You have " + totalCount + " recurring task(s) now.");
        sb.append(taskGenerator);
        return sb.toString();
    }

    public String getPleasedResponse() {
        return pleasedResponse;
    }

    public String getUnhappyResponse() {
        return unhappyResponse;
    }

}
