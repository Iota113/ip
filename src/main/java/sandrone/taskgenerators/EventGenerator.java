package sandrone.taskgenerators;

import java.time.LocalDate;
import java.time.Period;

import sandrone.task.Event;
import sandrone.task.Task;

/**
 * Represents a generator for recurring {@code Event} tasks.
 * This class manages the automated creation of tasks that span a duration,
 * advancing both the initialization date and the event's start and end dates
 * according to a specified frequency.
 */
public class EventGenerator extends TaskGenerator {
    private LocalDate nextStartDate;
    private LocalDate nextEndDate;

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
        this.nextStartDate = eventBlueprint.getStartDate();
        this.nextEndDate = eventBlueprint.getEndDate();
    }

    @Override
    public String getTaskTypeIcon() {
        return "E";
    }

    @Override
    public String toFileFormat() {
        return getTaskTypeIcon() + " | "
                + "P1W" + " | "
                + "NextInitDate:" + this.nextInitDate + " | "
                + this.taskDescription + " | "
                + this.nextStartDate + " | "
                + this.nextEndDate;
    }

    @Override
    public Task createInstance() {
        Task newRecurringTask = new Event(taskDescription, nextStartDate, nextEndDate);
        newRecurringTask.setRecurring(true);
        return newRecurringTask;
    }

    @Override
    public void advance() {
        this.nextInitDate = this.nextInitDate.plus(this.frequency);
        this.nextStartDate = this.nextStartDate.plus(this.frequency);
        this.nextEndDate = this.nextEndDate.plus(this.frequency);
    }
}
