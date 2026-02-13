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
     * @param taskBlueprint The blueprint of a task to be generated.
     * @param frequency    The frequency at which a new task is generated.
     * @param nextInitDate The next date at which a new task is generated.
     */
    public TodoGenerator(Task taskBlueprint, Period frequency, LocalDate nextInitDate) {
        super(taskBlueprint, frequency, nextInitDate);
    }

    @Override
    public Task createInstance() {
        Task newRecurringTask = new Todo(this.taskDescription);
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
                + this.taskDescription;
    }
}
