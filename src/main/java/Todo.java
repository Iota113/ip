public class Todo extends Task {

    public Todo(String desc) throws SandroneException {
        super(desc);

        if (desc.trim().equals("todo")) {
            throw new SandroneException("The description of a todo cannot be empty.");
        }
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String toFileFormat() {
        return "T | " + getStatusIcon() + " | " + this.desc;
    }
}
