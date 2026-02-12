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
     * @param description The description of the task.
     * @param frequency The frequency at which a new task is generated.
     * @param nextDueDate The due date for the latest Deadline Task generated.
     */
    public DeadlineGenerator(String description, Period frequency, LocalDate nextDueDate) {
        super(description, frequency);
        this.nextDueDate = nextDueDate;
    }

    @Override
    public Task createInstance() {
        Task newRecurringTask = new Deadline(description, nextDueDate);
        newRecurringTask.setRecurring(true);
        advanceDueDate();
        return newRecurringTask;
    }

    private void advanceDueDate() {
        this.nextDueDate = this.nextDueDate.plus(this.frequency);
    }
}
