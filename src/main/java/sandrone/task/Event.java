package sandrone.task;

import java.time.LocalDate;

import sandrone.util.Pulonia;

/**
 * Represents a task that occurs within a specific time range.
 * An {@code Event} task includes a description, a start date, and an end date.
 *
 * @author Henry Tse
 * @version 0.1
 */
public class Event extends Task {
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Constructs an {@code Event} task with a description and a date range.
     *
     * @param desc The description of the event.
     * @param startDate The starting date of the event.
     * @param endDate The ending date of the event.
     */
    public Event(String desc, LocalDate startDate, LocalDate endDate) {
        super(desc);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String getTaskTypeIcon() {
        return "E";
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getDurationString() {
        String formattedFrom = Pulonia.formatDate(this.startDate);
        String formattedTo = Pulonia.formatDate(this.endDate);
        return " (from: " + formattedFrom + " to: " + formattedTo + ")";
    }

    @Override
    public String toString() {
        return super.toString() + this.getDurationString();
    }

    @Override
    public String toFileFormat() {
        return getTaskTypeIcon() + " | "
                + getStatusIcon() + " | "
                + getRecurrenceIcon() + " | "
                + this.description + " | "
                + this.startDate + " | "
                + this.endDate;
    }
}
