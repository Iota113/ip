public class Deadline extends Task{
    private String deadline;

    public Deadline(String input) {
        super(input);
        String[] parts = desc.split(" /by ");
        this.desc = parts[0].replace("deadline ", "");
        this.deadline = parts[1];
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String getDescription() {
        return this.desc + " (by: " + this.deadline + ")";
    }
}
