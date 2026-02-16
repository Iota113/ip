package sandrone.command;

import sandrone.AppState;
import sandrone.taskgenerators.TaskGeneratorList;
import sandrone.ui.SandroneUi;
import sandrone.util.Storage;

/**
 * Represents a command to remove an existing task generator from the application.
 * This command deletes a recurring task rule based on its index in the list,
 * updates the persistent storage to reflect the change, and provides feedback to the user.
 */
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
