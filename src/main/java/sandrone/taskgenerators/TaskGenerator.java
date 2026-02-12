package sandrone.taskgenerators;

import java.time.LocalDate;
import java.time.Period;

import sandrone.task.Task;

/**
 * Represents a task generator in the Sandrone chatbot for recurring tasks.
 * This abstract class provides the basis for the generators
 * of Todo, Deadline and Event.
 *
 * @author Henry Tse
 * @version 0.2
 */
public abstract class TaskGenerator {
    protected String description;
    protected Period frequency;
    protected LocalDate nextInitDate;

    /**
     * Constructs a {@code TaskGenerator} task with a description and a frequency.
     *
     * @param description The description of the task.
     * @param frequency The frequency at which a new task is generated.
     */
    public TaskGenerator(String description, Period frequency, LocalDate nextInitDate) {
        this.description = description;
        this.frequency = frequency;
        this.nextInitDate = nextInitDate;
    }

    public String getTaskTypeIcon() {
        return "–";
    }

    public abstract String toFileFormat();

    public abstract Task createInstance();

    @Override
    public String toString() {
        return String.format("[%s] %s (Every %s, Next: %s)",
                this.getClass().getSimpleName().replace("Generator", ""),
                description,
                frequency,
                nextInitDate);
    }
}