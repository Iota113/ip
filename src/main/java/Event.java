import java.time.LocalDate;

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
        String formattedFrom = Pulonia.formatDate(this.from);
        String formattedTo = Pulonia.formatDate(this.to);
        return this.desc + " (from: " + formattedFrom + " to: " + formattedTo + ")";
    }

    @Override
    public String toFileFormat() {
        return "E | " + getStatusIcon() + " | " + this.desc + " | " + from + " | " + to;
    }
}
