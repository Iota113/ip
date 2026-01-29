public class DeleteCommand extends Command {
    private final int TASK_INDEX;

    public DeleteCommand(int TASK_INDEX) {
        this.TASK_INDEX = TASK_INDEX;
    }

    @Override
    public void execute(TaskList tasks, SandroneUi ui, Storage storage) {
        tasks.deleteTask(TASK_INDEX);
        storage.saveTasks(tasks.getTasks());
        ui.printPleasedResponse();
    }

}
