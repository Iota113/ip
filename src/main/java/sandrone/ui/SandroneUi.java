package sandrone.ui;

import java.util.ArrayList;
import java.util.List;

import sandrone.task.Task;

/**
 * Handles the user interface and interactions for the Sandrone chatbot.
 * This class is responsible for displaying messages, formatting output,
 * and presenting task information to the user in a stylized manner.
 *
 * @author Henry Tse
 * @version 0.1
 */
public class SandroneUi {
    private static final String greetings =
            "Look very closely, for standing before you is none other than Marionette.\n"
                    + "Seventh of the Fatui Harbingers.";
    private static final String farewell = "Ad astra abyssosque! Welcome to Nod-Krai, dominion of the Fatui.";
    private static final String pleasedResponse = "Very well.";
    private static final String unhappyResponse = "Utterly risible.";

    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the initial greeting message to the user.
     */
    public String getGreetings() {
        return greetings;
    }

    /**
     * Displays the farewell message when the user exits the application.
     */
    public String getFarewell() {
        return farewell;
    }

    /**
     * Prints a formatted list of tasks to the console.
     * Each task is displayed with its index, status icon, type, and description.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public String getTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            return "Your list is currently empty!";
        }

        StringBuilder sb = new StringBuilder("Your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Displays a confirmation message when a task is successfully added.
     * The message includes the current total task count and a stylized
     * representation of the newly added task.
     *
     * @param task The task that has been added to the list.
     * @param totalCount The new total number of tasks in the list.
     */
    public String getTaskAdded(Task task, int totalCount) {
        StringBuilder sb = new StringBuilder("Very well. You have " + totalCount + " task(s) now.");
        sb.append(task);
        return sb.toString();
    }

    public String getPleasedResponse() {
        return pleasedResponse;
    }

    public String getUnhappyResponse() {
        return unhappyResponse;
    }

}
