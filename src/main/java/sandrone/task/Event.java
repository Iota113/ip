package sandrone.task;

import java.time.LocalDateTime;

import sandrone.parser.DateParser;
import sandrone.util.DateUtils;

/**
 * Represents a task that occurs within a specific time range.
 * An {@code Event} task includes a description, a start date, and an end date.
 *
 * @author Henry Tse
 * @version 0.1
 */
public class Event extends Task {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    /**
     * Constructs an {@code Event} task with a description and a date range.
     *
     * @param description   The description of the event.
     * @param startDateTime The starting date and time of the event.
     * @param endDateTime   The ending date and time of the event.
     */
    public Event(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public String getTaskTypeIcon() {
        return "E";
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public String getDurationString() {
        String formattedStart = DateParser.formatDisplayDateTime(this.startDateTime);
        String formattedEnd = DateParser.formatDisplayDateTime(this.endDateTime);
        return " (from: " + formattedStart + " to: " + formattedEnd + ")";
    }

    @Override
    public String toString() {
        return super.toString() + this.getDurationString();
    }

    @Override
    public String toFileFormat() {
        String startStr = this.startDateTime.format(DateUtils.DATE_TIME_FORMATTER);
        String endStr = this.endDateTime.format(DateUtils.DATE_TIME_FORMATTER);

        return getTaskTypeIcon() + " | "
                + getStatusIcon() + " | "
                + getRecurrenceIcon() + " | "
                + this.description + " | "
                + startStr + " | "
                + endStr;
    }
}
