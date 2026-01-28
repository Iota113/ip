import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDate from;
    private LocalDate to;

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
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("EEEE, MMM dd yyyy");
        String formattedFrom = this.from.format(customFormatter);
        String formattedTo = this.to.format(customFormatter);
        return this.desc + " (from: " + formattedFrom + " to: " + formattedTo + ")";
    }

    @Override
    public String toFileFormat() {
        return "E | " + getStatusIcon() + " | " + this.desc + " | " + from + " | " + to;
    }
}
