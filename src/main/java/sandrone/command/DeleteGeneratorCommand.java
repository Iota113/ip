package sandrone.command;

import sandrone.AppState;
import sandrone.task.TaskList;
import sandrone.taskgenerators.TaskGeneratorList;
import sandrone.ui.SandroneUi;
import sandrone.util.Storage;

public class DeleteGeneratorCommand extends Command {
    private int taskGeneratorIndex;

    /**
     * Constructs a {@code DeleteGeneratorCommand} with the specified index.
     *
     * @param taskGeneratorIndex The 0-based index of the task generator to be removed from the list.
     */
    public DeleteGeneratorCommand(int taskGeneratorIndex) {
        this.taskGeneratorIndex = taskGeneratorIndex;
    }

    @Override
    public String execute(AppState appState, SandroneUi ui, Storage storage) {
        TaskGeneratorList taskGeneratorList = appState.getGeneratorList();

        taskGeneratorList.deleteTaskGenerator(taskGeneratorIndex);

        storage.saveGenerators(taskGeneratorList);

        return ui.getPleasedResponse();
    }
}
