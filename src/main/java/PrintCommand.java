public class PrintCommand extends Command {
    @Override
    public void execute(TaskList tasks, SandroneUi ui, Storage storage) {
        ui.printTaskList(tasks);
    }
}
