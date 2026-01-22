public class Event extends Task{
    private String from;
    private String to;

    public Event(String input) {
        super(input);
        this.desc = input.split(" /from ")[0].replace("event ", "");
        this.from = input.split(" /from ")[1].split( " to ")[0];
        this.to = input.split(" /to ")[1];
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String getDescription() {
        return this.desc + " (from: " + this.from + " to: " + this.to + ")";
    }
}
