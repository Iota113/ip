package sandrone.ui;

import sandrone.task.Task;
import sandrone.task.TaskList;

import java.util.ArrayList;

public class SandroneUi {
    private static final String greetings =
            "Look very closely, for standing before you is none other than Marionette.\n" +
                    "Seventh of the Fatui Harbingers.";
    private static final String farewell = "Ad astra abyssosque! Welcome to Nod-Krai, dominion of the Fatui.";

    public void showGreetings () {
        printLine();
        System.out.println(greetings);
        printLine();
    }

    public void showFarewell() {
        printLine();
        System.out.println(farewell);
        printLine();
    }

    public void printTasks(ArrayList<Task> tasks) {
        System.out.println("Your List:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(
                    (i + 1) + "."
                            + "[" + tasks.get(i).getStatusIcon() + "]"
                            + "[" + tasks.get(i).getTaskType() + "] "
                            + tasks.get(i).getDescription());
        }
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
