package sandrone.command;

import sandrone.AppState;
import sandrone.task.TaskList;
import sandrone.taskgenerators.TaskGenerator;
import sandrone.taskgenerators.TaskGeneratorList;
import sandrone.ui.SandroneUi;
import sandrone.util.Storage;

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
