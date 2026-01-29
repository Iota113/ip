package sandrone.ui;

import java.util.ArrayList;

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

    /**
     * Displays the initial greeting message to the user.
     */
    public void showGreetings() {
        printLine();
        System.out.println(greetings);
        printLine();
    }

    /**
     * Displays the farewell message when the user exits the application.
     */
    public void showFarewell() {
        printLine();
        System.out.println(farewell);
        printLine();
    }

    /**
     * Prints a formatted list of tasks to the console.
     * Each task is displayed with its index, status icon, type, and description.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void showTasks(ArrayList<Task> tasks) {
        System.out.println("Your List:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "."
                            + "[" + tasks.get(i).getStatusIcon() + "]"
                            + "[" + tasks.get(i).getTaskType() + "] "
                            + tasks.get(i).getDescription());
        }
    }

    /**
     * Displays a confirmation message when a task is successfully added.
     * The message includes the current total task count and a stylized
     * representation of the newly added task.
     *
     * @param task The task that has been added to the list.
     * @param totalCount The new total number of tasks in the list.
     */
    public void showTaskAdded(Task task, int totalCount) {
        System.out.println("Very well. You have " + totalCount + " task(s) now.");
        System.out.println("[" + task.getTaskType() + "][" + task.getStatusIcon() + "] " + task.getDescription());
    }

    public void printPleasedResponse() {
        System.out.println("Very well.");
    }

    public void printUnhappyResponse() {
        System.out.println("Utterly risible.");
    }

    public void printLine() {
        System.out.println("____________________________________________________________");
    }

}
