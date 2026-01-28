public class Event extends Task{
    private String from;
    private String to;

    public Event(String desc, String from, String to) {
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
        return this.desc + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String toFileFormat() {
        return "E | " + getStatusIcon() + " | " + this.desc + " | " + from + " | " + to;
    }
}
