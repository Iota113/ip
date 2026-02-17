package sandrone.taskgenerators;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import sandrone.task.Event;
import sandrone.task.Task;
import sandrone.util.DateUtils;

/**
 * Represents a generator for recurring {@code Event} tasks.
 * This class manages the automated creation of tasks that span a duration,
 * advancing both the initialization date and the event's start and end dates
 * according to a specified frequency.
 */
public class EventGenerator extends TaskGenerator {
    private LocalDateTime nextStartDateTime;
    private LocalDateTime nextEndDateTime;

    /**
     * Constructs an {@code EventGenerator} task with a description, a frequency,
     * a start date and an end date (both that updates regularly).
     *
     * @param eventBlueprint The blueprint of an event task to be generated.
     * @param frequency      The frequency at which a new task is generated.
     * @param nextInitDate The next date at which a new task is generated.
     */
    public EventGenerator(Event eventBlueprint, Period frequency, LocalDate nextInitDate) {
        super(eventBlueprint, frequency, nextInitDate);
        this.nextStartDateTime = eventBlueprint.getStartDateTime();
        this.nextEndDateTime = eventBlueprint.getEndDateTime();
    }

    @Override
    public String getTaskTypeIcon() {
        return "E";
    }

    @Override
    public String toFileFormat() {
        String nextStartStr = this.nextStartDateTime.format(DateUtils.DATE_TIME_FORMATTER);
        String nextEndStr = this.nextEndDateTime.format(DateUtils.DATE_TIME_FORMATTER);

        return getTaskTypeIcon() + " | "
                + "P1W" + " | "
                + "NextInitDate:" + this.nextInitDate + " | "
                + this.taskDescription + " | "
                + nextStartStr + " | "
                + nextEndStr;
    }

    @Override
    public Task createInstance() {
        Task newRecurringTask = new Event(taskDescription, nextStartDateTime, nextEndDateTime);
        newRecurringTask.setRecurring(true);
        return newRecurringTask;
    }

    @Override
    public void advance() {
        this.nextInitDate = this.nextInitDate.plus(this.frequency);
        this.nextStartDateTime = this.nextStartDateTime.plus(this.frequency);
        this.nextEndDateTime = this.nextEndDateTime.plus(this.frequency);
    }
}
