package sandrone.command;

<<<<<<< HEAD
import java.util.ArrayList;

=======
>>>>>>> branch-Level-9
import sandrone.task.Task;
import sandrone.task.TaskList;
import sandrone.ui.SandroneUi;
import sandrone.util.Storage;

<<<<<<< HEAD
/**
 * Represents a command to search for tasks that contain a specific keyword.
 * This class filters the task list and displays only the
 * results that match the user's search keyword.
 *
 * @author Henry Tse
 * @version 0.1
 */
=======
import java.util.ArrayList;

>>>>>>> branch-Level-9
public class FindCommand extends Command {

    private String userInput;

<<<<<<< HEAD
    /**
     * Constructs a {@code FindCommand} with the specified search string.
     *
     * @param userInput The keyword to look for within task descriptions.
     */
=======
>>>>>>> branch-Level-9
    public FindCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
<<<<<<< HEAD
    public void execute(TaskList taskList, SandroneUi ui, Storage storage) {
        ArrayList<Task> matchingTasks = taskList.getMatchingTasks(userInput);
        ui.showTasks(matchingTasks);
=======
    public void execute(TaskList taskList, SandroneUi ui, Storage storage){
        ArrayList<Task> matchingTasks = taskList.getMatchingTasks(userInput);
        ui.printTasks(matchingTasks);
>>>>>>> branch-Level-9
    }
}
