package sandrone;
import sandrone.command.Command;
import sandrone.exception.SandroneException;
import sandrone.util.Storage;
import sandrone.task.TaskList;
import sandrone.ui.SandroneUi;
import sandrone.util.Pulonia;
import java.util.Scanner;


public class Sandrone {
    private SandroneUi ui;
    private TaskList taskList;
    private Storage listData;

    public Sandrone(String filePath) {
        this.ui = new SandroneUi();
        this.listData = new Storage(filePath);

    }

    public void run() {
        this.taskList = new TaskList(this.listData);
        ui.showGreetings();

        Scanner scn = new Scanner(System.in);
        String userInput = scn.nextLine();

        while (!userInput.equals("bye")) {
            try {
                ui.printLine();
                Command c = Pulonia.parseCommand(userInput);
                c.execute(this.taskList, this.ui, this.listData);
            } catch (SandroneException e) {
                System.out.println(e.getMessage());
            }

            ui.printLine();
            userInput = scn.nextLine();
        }
        ui.showFarewell();

    }

    public static void main(String[] args) {
        new Sandrone("./data/sandrone_task_list.txt").run();
    }

}
