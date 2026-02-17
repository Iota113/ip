package sandrone;

import sandrone.command.Command;
import sandrone.exception.SandroneException;
import sandrone.parser.Pulonia;
import sandrone.ui.SandroneUi;
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
     * @param taskPath The relative path to the local text file where tasks are saved.
     * @param generatorPath The relative path to the local text file where task generators are saved.
     */
    public Sandrone(String taskPath, String generatorPath) {
        this.ui = new SandroneUi();
        this.storage = new Storage(taskPath, generatorPath);
        this.appState = new AppState(storage);
    }

    /**
     * Processes user input and returns the chatbot's response.
     */
    public String getResponse(String input) throws SandroneException {
        Command c = Pulonia.parseCommand(input);
        this.isExit = c.isExit();
        return c.execute(this.appState, this.ui, this.storage);
    }

    public String getGreetings() {
        return ui.getGreetings();
    }

    public boolean shouldExit() {
        return isExit;
    }

}
