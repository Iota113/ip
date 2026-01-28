import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDate by;

    public Deadline(String desc, LocalDate by) {
        super(desc);
        this.by = by;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String getDescription() {
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("EEEE, MMM dd yyyy");
        String formattedDate = this.by.format(customFormatter);
        return this.desc + " (by: " + formattedDate + ")";
    }

    @Override
    public String toFileFormat() {
        return "D | " + getStatusIcon() + " | " + this.desc + " | " + this.by;
    }
}
