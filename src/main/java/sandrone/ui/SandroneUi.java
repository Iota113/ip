package sandrone.ui;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import sandrone.task.Task;
import sandrone.taskgenerators.TaskGenerator;
import sandrone.util.Messages;

/**
 * Handles the user interface and interactions for the Sandrone chatbot.
 * This class is responsible for displaying messages, formatting output,
 * and presenting task information to the user in a stylized manner.
 *
 * @author HenryTse
 * @version 0.1
 */
public class SandroneUi {
    public String getGreetings() {
        return Messages.GREETINGS;
    }
    public String getFarewell() {
        return Messages.FAREWELL;
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

        String listBody = IntStream.range(0, items.size())
                .mapToObj(i -> String.format("(%d) %s", i + 1, items.get(i)))
                .collect(Collectors.joining("\n"));

        return header + "\n" + listBody;
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
        // Two newlines create a clear "invisible" gap between sections
        StringJoiner sj = new StringJoiner("\n\n");

        sj.add(listToString(tasks, "Your Active Tasks:", "No tasks."));
        sj.add(listToString(generators, "Your Recurring Tasks:", "No recurring tasks~"));

        return sj.toString();
    }

    /** 1. Base level: Just the action and the item (e.g., for Marking/Unmarking) */
    private String formatAction(String message, Object item) {
        return message + item + "\n";
    }

    /** 2. Middle level: Action, item, and a general count (e.g., for Tasks) */
    private String formatAction(String message, Object item, int count) {
        return formatAction(message, item) + String.format("You have %d task(s) now.", count);
    }

    /** 3. Specific level: Action, item, count, and a specific qualifier (e.g., for Recurring) */
    private String formatAction(String message, Object item, int count, String qualifier) {
        return formatAction(message, item) + String.format("You have %d %s task(s) now.", count, qualifier);
    }

    /**
     * Displays a confirmation message when a t is successfully added.
     * The message includes the current total t count and a stylized
     * representation of the newly added t.
     *
     * @param t The t that has been added to the list.
     * @param count The new total number of tasks in the list.
     */
    public String showTaskAdded(Task t, int count) {
        return formatAction(Messages.MESSAGE_TASK_ADDED, t, count);
    }

    public String showTaskDeleted(Task t, int count) {
        return formatAction(Messages.MESSAGE_TASK_DELETED, t, count);
    }

    public String showTaskMarked(Task t) {
        return formatAction(Messages.MESSAGE_TASK_MARKED, t);
    }

    public String showTaskUnmarked(Task t) {
        return formatAction(Messages.MESSAGE_TASK_UNMARKED, t);
    }

    /**
     * Displays a confirmation message when a task generator is successfully added.
     * The message includes the current total task count and a stylized
     * representation of the newly added task.
     *
     * @param tg The task gnerator that has been added to the list.
     * @param count The new total number of tasks in the list.
     */
    public String showTaskGeneratorAdded(TaskGenerator tg, int count) {
        return formatAction(Messages.MESSAGE_RECURRING_TASK_ADDED, tg, count, "recurring");
    }

    public String showTaskGeneratorDeleted(TaskGenerator tg, int count) {
        return formatAction(Messages.MESSAGE_RECURRING_TASK_DELETED, tg, count, "recurring");
    }

}
