package sandrone.task;

import java.time.LocalDate;

import sandrone.util.Pulonia;

/**
 * Represents a task with a specific deadline.
 * A {@code Deadline} object contains a description and a date dueDate which
 * the task must be completed, represented as a {@code LocalDate}.
 *
 * @author Henry Tse
 * @version 0.1
 */
public class Deadline extends Task {
    private LocalDate dueDate;

    /**
     * Constructs a {@code Deadline} task with a description and a due date.
     *
     * @param desc The description of the task.
     * @param dueDate The date the task is due.
     */
    public Deadline(String desc, LocalDate dueDate) {
        super(desc);
        this.dueDate = dueDate;
    }

    @Override
    public String getTaskTypeIcon() {
        return "D";
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getDueDateString() {
        String formattedBy = Pulonia.formatDate(this.dueDate);
        return " (by: " + formattedBy + ")";
    }

    @Override
    public String toString() {
        return super.toString() + this.getDueDateString();
    }

    @Override
    public String toFileFormat() {
        return getTaskTypeIcon() + " | "
                + getStatusIcon() + " | "
                + getRecurrenceIcon() + " | "
                + this.description + " | "
                + this.dueDate;
    }
}
