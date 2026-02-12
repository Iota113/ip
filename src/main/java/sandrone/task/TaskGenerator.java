package sandrone.task;

import java.time.Period;

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

    /**
     * Constructs a {@code TaskGenerator} task with a description and a frequency.
     *
     * @param description The description of the task.
     * @param frequency The frequency at which a new task is generated.
     */
    public TaskGenerator(String description, Period frequency) {
        this.description = description;
        this.frequency = frequency;
    }

    public abstract Task createInstance();
}