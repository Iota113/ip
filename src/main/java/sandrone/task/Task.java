package sandrone.task;

import java.util.Objects;

/**
 * Represents a generic task in the Sandrone chatbot.
 * This abstract class provides the base functionality for all tasks,
 * including a description and a completion status.
 *
 * @author Henry Tse
 * @version 0.1
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected boolean isRecurring;

    /**
     * Constructs a new {@code Task} with the given description.
     * By default, the task is initialized as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        assert !description.isEmpty() : "Pulonia failed to check for empty descriptions!";
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : "·"); // mark done task with X
    }

    public String getRecurrenceIcon() {
        return (isRecurring ? "R" : "·");
    }

    public String getTaskDescription() {
        return this.description;
    }

    public String getTaskTypeIcon() {
        return "·";
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public boolean isMarked() {
        return this.isDone;
    }

    public abstract String toFileFormat();

    @Override
    public String toString() {
        return String.format("[%s][%s][%s] %s", getStatusIcon(), getTaskTypeIcon(), getRecurrenceIcon(), description);
    }

    public void setRecurring(boolean isRecurring) {
        this.isRecurring = isRecurring;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Task)) {
            return false;
        }

        Task other = (Task) o;
        return this.description.trim().equalsIgnoreCase(other.description.trim());
    }

    @Override
    public int hashCode() {
        return Objects.hash(description.toLowerCase().trim());
    }
}
