package sandrone.command;

import java.util.ArrayList;

import sandrone.task.Task;
import sandrone.task.TaskList;
import sandrone.ui.SandroneUi;
import sandrone.util.Storage;

/**
 * Represents a command to search for tasks that contain a specific keyword.
 * This class filters the task list and displays only the
 * results that match the user's search keyword.
 *
 * @author Henry Tse
 * @version 0.1
 */

public class FindCommand extends Command {

    private String userInput;

    /**
     * Constructs a {@code FindCommand} with the specified search string.
     *
     * @param userInput The keyword to look for within task descriptions.
     */

    public FindCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public String execute(TaskList taskList, SandroneUi ui, Storage storage) {
        ArrayList<Task> matchingTasks = taskList.getMatchingTasks(userInput);
        return ui.getTasks(matchingTasks);
    }

}
