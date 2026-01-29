package sandrone.task;

import sandrone.exception.SandroneException;

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
     * @throws SandroneException If the description is empty or only contains the word "todo".
     */
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
