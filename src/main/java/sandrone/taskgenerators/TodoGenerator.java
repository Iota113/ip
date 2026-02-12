package sandrone.taskgenerators;

import java.time.LocalDate;
import java.time.Period;

import sandrone.task.Task;
import sandrone.task.Todo;

/**
 * Represents a Todo generator for the Sandrone chatbot
 *
 * @author Henry Tse
 * @version 0.2
 */
public class TodoGenerator extends TaskGenerator {

    /**
     * Constructs a {@code TodoGenerator} task with a description and a frequency.
     *
     * @param description  The description of the task.
     * @param frequency    The frequency at which a new task is generated.
     * @param nextInitDate
     */
    public TodoGenerator(String description, Period frequency, LocalDate nextInitDate) {
        super(description, frequency, nextInitDate);
    }

    @Override
    public Task createInstance() {
        Task newRecurringTask = new Todo(description);
        newRecurringTask.setRecurring(true);
        return newRecurringTask;
    }

    @Override
    public String getTaskTypeIcon() {
        return "T";
    }

    @Override
    public String toFileFormat() {
        return getTaskTypeIcon() + " | "
                + "P1W" + " | "
                + "NextInitDate:" + this.nextInitDate + " | "
                + this.description;
    }
}
