package sandrone.task;

import java.time.LocalDate;

import sandrone.exception.SandroneException;
import sandrone.util.Pulonia;

/**
 * Represents a task that occurs within a specific time range.
 * An {@code Event} task includes a description, a start date, and an end date.
 *
 * @author Henry Tse
 * @version 0.1
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs an {@code Event} task with a description and a date range.
     *
     * @param desc The description of the event.
     * @param from The starting date of the event.
     * @param to The ending date of the event.
     */
    public Event(String desc, LocalDate from, LocalDate to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String getDescription() {
        return this.desc + this.getDurationString();
    }

    public String getDurationString() {
        String formattedFrom = Pulonia.formatDate(this.from);
        String formattedTo = Pulonia.formatDate(this.to);
        return " (from: " + formattedFrom + " to: " + formattedTo + ")";
    }

    @Override
    public String toString() {
        return super.toString() + this.getDurationString();
    }

    @Override
    public String toFileFormat() {
        return "E | " + getStatusIcon() + " | " + this.desc + " | " + from + " | " + to;
    }
}
