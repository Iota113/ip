package sandrone.taskgenerators;

import java.time.LocalDate;
import java.time.Period;

import sandrone.task.Event;
import sandrone.task.Task;

public class EventGenerator extends TaskGenerator{
    private LocalDate nextStartDate;
    private LocalDate nextEndDate;

    /**
     * Constructs an {@code EventGenerator} task with a description, a frequency,
     * a start date and an end date (both that updates regularly).
     *
     * @param eventBlueprint
     * @param frequency      The frequency at which a new task is generated.
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
        advanceDates();
        return newRecurringTask;
    }

    private void advanceDates() {
        this.nextStartDate = this.nextStartDate.plus(this.frequency);
        this.nextEndDate = this.nextEndDate.plus(this.frequency);
    }
}
