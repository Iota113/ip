package sandrone.task;

import java.time.LocalDate;

import sandrone.util.Pulonia;

/**
 * Represents a task with a specific deadline.
 * A {@code Deadline} object contains a description and a date by which
 * the task must be completed, represented as a {@code LocalDate}.
 *
 * @author Henry Tse
 * @version 0.1
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructs a {@code Deadline} task with a description and a due date.
     *
     * @param desc The description of the task.
     * @param by The date the task is due.
     */
    public Deadline(String desc, LocalDate by) {
        super(desc);
        this.by = by;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String getDescription() {
        String formattedBy = Pulonia.formatDate(this.by);
        return this.desc + " (by: " + formattedBy + ")";
    }

    @Override
    public String toFileFormat() {
        return "D | " + getStatusIcon() + " | " + this.desc + " | " + this.by;
    }
}
