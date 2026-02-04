package sandrone;

import java.util.Scanner;

import sandrone.command.Command;
import sandrone.exception.SandroneException;
import sandrone.task.TaskList;
import sandrone.ui.SandroneUi;
import sandrone.util.Pulonia;
import sandrone.util.Storage;


/**
 * Represents a task manager that handles user commands.
 * This class serves as the core logic for the Sandrone chatbot,
 * coordinating between the user input and the backend logic.
 *
 * @author Henry Tse
 * @version 0.1
 */
public class Sandrone {
    private SandroneUi ui;
    private TaskList taskList;
    private Storage listData;
    private boolean isExit;

    /**
     * Initializes the chatbot with the storage file path.
     *
     * @param filePath The relative path to the local text file where tasks are saved.
     */
    public Sandrone(String filePath) {
        this.ui = new SandroneUi();
        this.listData = new Storage(filePath);
        this.taskList = new TaskList(this.listData);
    }

    /**
     * Processes user input and returns the chatbot's response.
     */
    public String getResponse(String input) {
        try {
            Command c = Pulonia.parseCommand(input);
            return c.execute(this.taskList, this.ui, this.listData);
        } catch (SandroneException e) {
            return e.getMessage();
        }
    }

    public String getGreetings() {
        return ui.getGreetings();
    }

    /**
     * Starts the main execution loop of the chatbot.
     * Continues to read and execute user commands until the "bye" command is received.
     */
    public void run() {
    //        ui.showGreetings();
    //
    //        Scanner scn = new Scanner(System.in);
    //        String userInput = scn.nextLine();
    //
    //        while (!userInput.equals("bye")) {
    //            try {
    //                ui.printLine();
    //                Command c = Pulonia.parseCommand(userInput);
    //                c.execute(this.taskList, this.ui, this.listData);
    //            } catch (SandroneException e) {
    //                System.out.println(e.getMessage());
    //            }
    //
    //            ui.printLine();
    //            userInput = scn.nextLine();
    //        }
    //        ui.showFarewell();

    }

    public static void main(String[] args) {
    //        new Sandrone("./data/sandrone_task_list.txt").run();
    }



}
