import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import sandrone.Sandrone;
import sandrone.exception.SandroneException;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Sandrone sandrone;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/mambo.jpg"));
    private Image sandroneImage = new Image(this.getClass().getResourceAsStream("/images/sandrone.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Sandrone instance and calls a welcome message */
    public void setSandrone(Sandrone s) {
        this.sandrone = s;
        showWelcomeMessage();
    }

    private void showWelcomeMessage() {
        String greeting = sandrone.getGreetings();
        dialogContainer.getChildren().add(
                DialogBox.getSandroneDialog(greeting, sandroneImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response;
        String styleClass = "utility";

        try {
            response = sandrone.getResponse(input);

            String command = input.split("\\s+")[0].toLowerCase();

            styleClass = switch (command) {
            case "todo", "deadline", "event" -> "add";
            case "recur", "drecur", "sync" -> "recur";
            default -> "utility";
            };

        } catch (SandroneException e) {
            response = e.getMessage();
            styleClass = "error";
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSandroneDialog(response, sandroneImage, styleClass)
        );
        userInput.clear();

        if (sandrone.shouldExit()) {
            PauseTransition delay = new PauseTransition(Duration.seconds(0.7));
            delay.setOnFinished(event -> {
                Platform.exit();
                System.exit(0);
            });
            delay.play();
        }
    }
}
