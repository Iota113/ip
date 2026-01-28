public class Deadline extends Task{
    private String by;

    public Deadline(String desc, String by) {
        super(desc);
        this.by = by;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String getDescription() {
        return this.desc + " (by: " + this.by + ")";
    }

    @Override
    public String toFileFormat() {
        return "D | " + getStatusIcon() + " | " + this.desc + " | " + this.by;
    }
}
