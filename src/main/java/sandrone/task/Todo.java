package sandrone.task;

/**
 * Represents a simple task without any date or time constraints.
 * A {@code Todo} task only contains a description and a completion status.
 *
 * @author Henry Tse
 * @version 0.1
 */
public class Todo extends Task {

    /**
     * Constructs a {@code Todo} task with the specified description.
     *
     * @param desc The description of the task.
     */
    public Todo(String desc) {
        super(desc);
    }

    @Override
    public String getTaskTypeIcon() {
        return "T";
    }

    @Override
    public String toFileFormat() {
        return "T | " + getStatusIcon() + " | " + this.desc;
    }
}
