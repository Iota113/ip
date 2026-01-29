package sandrone.command;

import java.util.ArrayList;

import sandrone.task.Task;
import sandrone.task.TaskList;
import sandrone.ui.SandroneUi;
import sandrone.util.Storage;

public class FindCommand extends Command {

    private String userInput;

    public FindCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList taskList, SandroneUi ui, Storage storage) {
        ArrayList<Task> matchingTasks = taskList.getMatchingTasks(userInput);
        ui.printTasks(matchingTasks);
    }
}
