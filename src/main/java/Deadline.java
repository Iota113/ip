public class Deadline extends Task{
    private String deadline;

    public Deadline(String input) throws SandroneException {
        super(input);

        if (!input.contains(" /by ")) {
            throw new SandroneException("Incomplete command! A deadline needs a ' /by ' component.");
        }

        String[] parts = desc.split(" /by ");
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new SandroneException("The deadline cannot be empty.");
        }

        this.desc = parts[0].substring(8).trim();
        this.deadline = parts[1];

        if (this.desc.isEmpty()) {
            throw new SandroneException("The task cannot be empty.");
        }

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
