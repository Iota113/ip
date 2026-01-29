public class AddCommand extends Command {
    private Task newTask;

    public AddCommand(Task newtask) {
        this.newTask = newtask;
    }

    @Override
    public void execute(TaskList tasks, SandroneUi ui, Storage storage) {
        tasks.addTask(this.newTask);
        storage.saveTasks(tasks.getTasks());
        ui.printPleasedResponse();
    }
}
