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
     * @param description The description of the task.
     * @param frequency   The frequency at which a new task is generated.
     * @param nextStartDate   The start date for the latest generated event.
     * @param nextEndDate     The end date for the latest generated event.
     */
    public EventGenerator(String description, Period frequency, LocalDate nextInitDate, LocalDate nextStartDate, LocalDate nextEndDate) {
        super(description, frequency, nextInitDate);
        this.nextStartDate = nextStartDate;
        this.nextEndDate = nextEndDate;
    }

    @Override
    public String toFileFormat() {
        return getTaskTypeIcon() + " | "
                + "P1W" + " | "
                + "NextInitDate:" + this.nextInitDate + " | "
                + this.description + " | "
                + this.nextStartDate + " | "
                + this.nextEndDate;
    }

    @Override
    public Task createInstance() {
        Task newRecurringTask = new Event(description, nextStartDate, nextEndDate);
        newRecurringTask.setRecurring(true);
        advanceDates();
        return newRecurringTask;
    }

    private void advanceDates() {
        this.nextStartDate = this.nextStartDate.plus(this.frequency);
        this.nextEndDate = this.nextEndDate.plus(this.frequency);
    }
}
