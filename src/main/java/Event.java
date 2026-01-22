public class Event extends Task{
    private String from;
    private String to;

    public Event(String input) throws SandroneException {
        super(input);
        if (!(input.contains(" /from ") && input.contains(" /to"))) {
            throw new SandroneException("Incomplete command! An Event needs a ' /from ' and ' /to ' component.");
        }

        // Remove "event"
        input = input.substring(5).trim();
        String[] desc_part = input.split("/from");
        this.desc = desc_part[0].trim();
        if (this.desc.isEmpty()) {
            throw new SandroneException("The task description is empty!");
        }

        String[] time_parts = desc_part[1].split("/to");
        if (time_parts.length < 2) {
            if (time_parts[0].trim().isEmpty()) throw new SandroneException("Both from and to fields are empty!");
            throw new SandroneException("The to field is empty!");
        }
        this.from = time_parts[0].trim();
        this.to = time_parts[1].trim();
        if (this.from.isEmpty()) throw new SandroneException("The from field is empty!");

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
