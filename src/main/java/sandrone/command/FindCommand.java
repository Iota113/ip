package sandrone.command;

import sandrone.task.Task;
import sandrone.task.TaskList;
import sandrone.ui.SandroneUi;
import sandrone.util.Storage;

import java.util.ArrayList;

public class FindCommand extends Command {

    private String userInput;

    public FindCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList taskList, SandroneUi ui, Storage storage){
        ArrayList<Task> matchingTasks = taskList.getMatchingTasks(userInput);
        ui.printTasks(matchingTasks);
    }
}
