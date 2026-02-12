package sandrone;

import sandrone.command.Command;
import sandrone.exception.SandroneException;
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
    private AppState appState;
    private Storage storage;
    private boolean isExit = false;

    /**
     * Initializes the chatbot with the storage file path.
     *
     * @param filePath The relative path to the local text file where tasks are saved.
     */
    public Sandrone(String filePath) {
        this.ui = new SandroneUi();
        this.storage = new Storage(filePath);
        this.appState = new AppState(storage);
    }

    /**
     * Processes user input and returns the chatbot's response.
     */
    public String getResponse(String input) {
        try {
            Command c = Pulonia.parseCommand(input);
            this.isExit = c.isExit();
            return c.execute(this.appState, this.ui, this.storage);
        } catch (SandroneException e) {
            return e.getMessage();
        }
    }

    public String getGreetings() {
        return ui.getGreetings();
    }

    public boolean shouldExit() {
        return isExit;
    }

}
