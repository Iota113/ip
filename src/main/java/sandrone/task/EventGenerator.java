package sandrone.task;

import java.time.LocalDate;
import java.time.Period;

public class EventGenerator extends TaskGenerator{
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Constructs an {@code EventGenerator} task with a description, a frequency,
     * a start date and an end date (both that updates regularly).
     *
     * @param description The description of the task.
     * @param frequency   The frequency at which a new task is generated.
     * @param startDate   The start date for the latest generated event.
     * @param endDate     The end date for the latest generated event.
     */
    public EventGenerator(String description, Period frequency, LocalDate startDate, LocalDate endDate) {
        super(description, frequency);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public Task createInstance() {
        Task newTask = new Event(description, startDate, endDate);
        advanceDates();
        return newTask;
    }

    private void advanceDates() {
        this.startDate = this.startDate.plus(this.frequency);
        this.endDate = this.endDate.plus(this.frequency);
    }
}
