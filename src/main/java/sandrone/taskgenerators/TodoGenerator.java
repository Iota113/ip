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
    public TodoGenerator(String description, LocalDate nextDate, Period frequency) {
        super(description, frequency);
    }

    @Override
    public Task createInstance() {
        Task newRecurringTask = new Todo(description);
        newRecurringTask.setRecurring(true);
        return newRecurringTask;
    }
}
