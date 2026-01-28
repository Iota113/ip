import java.time.LocalDate;

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
        String formattedBy = Pulonia.formatDate(this.by);
        return this.desc + " (by: " + formattedBy + ")";
    }

    @Override
    public String toFileFormat() {
        return "D | " + getStatusIcon() + " | " + this.desc + " | " + this.by;
    }
}
