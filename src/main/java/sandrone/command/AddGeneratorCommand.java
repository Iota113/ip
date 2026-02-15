package sandrone.command;

import sandrone.AppState;
import sandrone.taskgenerators.TaskGenerator;
import sandrone.taskgenerators.TaskGeneratorList;
import sandrone.ui.SandroneUi;
import sandrone.util.Storage;

/**
 * Represents a command to add a new task generator to the system.
 * This command handles the addition of a recurring task rule, ensures it is
 * saved to persistent storage, and provides feedback to the user via the UI.
 */
public class AddGeneratorCommand extends Command {
    private TaskGenerator newTaskGenerator;

    public AddGeneratorCommand(TaskGenerator newTaskGenerator) {
        this.newTaskGenerator = newTaskGenerator;
    }

    @Override
    public String execute(AppState appState, SandroneUi ui, Storage storage) {
        TaskGeneratorList taskGeneratorList = appState.getGeneratorList();
        taskGeneratorList.addTaskGenerator(newTaskGenerator);
        storage.saveGenerators(taskGeneratorList);
        int totalCount = taskGeneratorList.getGeneratorCount();
        return ui.showTaskGeneratorAdded(newTaskGenerator, totalCount);
    }
}
