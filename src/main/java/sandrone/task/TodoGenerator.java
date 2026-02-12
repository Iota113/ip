package sandrone.task;

import java.time.LocalDate;
import java.time.Period;

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
        return new Todo(description);
    }
}
