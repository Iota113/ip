package sandrone.taskgenerators;

import java.time.LocalDate;
import java.time.Period;

import sandrone.task.Deadline;
import sandrone.task.Task;

/**
 * Represents a Deadline generator for the Sandrone chatbot
 *
 * @author Henry Tse
 * @version 0.2
 */
public class DeadlineGenerator extends TaskGenerator {
    private LocalDate nextDueDate;

    /**
     * Constructs a {@code DeadlineGenerator} task with a description, a frequency
     * and an (updating) due date.
     *
     * @param deadlineBlueprint  The blueprint of a deadline task to be generated.
     * @param frequency    The frequency at which a new task is generated.
     * @param nextInitDate The next date at which a new task is generated.
     */
    public DeadlineGenerator(Deadline deadlineBlueprint, Period frequency, LocalDate nextInitDate) {
        super(deadlineBlueprint, frequency, nextInitDate);
        this.nextDueDate = deadlineBlueprint.getDueDate();
    }

    @Override
    public String getTaskTypeIcon() {
        return "D";
    }

    @Override
    public Task createInstance() {
        Task newRecurringTask = new Deadline(taskDescription, nextDueDate);
        newRecurringTask.setRecurring(true);
        return newRecurringTask;
    }

    @Override
    public String toFileFormat() {
        return getTaskTypeIcon() + " | "
                + "P1W" + " | "
                + "NextInitDate:" + this.nextInitDate + " | "
                + this.taskDescription + " | "
                + this.nextDueDate;
    }

    public static int extractIndex(String userInput) {
        // "\\s+" handles one or more spaces
        String[] parts = userInput.trim().split("\\s+");
        if (parts.length < 2) {
            throw new IllegalArgumentException("Please provide an index number.");
        }
        return Integer.parseInt(parts[1]) - 1;
    }

    @Override
    public void advance() {
        this.nextInitDate = this.nextInitDate.plus(frequency);
        this.nextDueDate = this.nextDueDate.plus(frequency);
    }

}
