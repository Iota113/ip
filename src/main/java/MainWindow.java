import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javafx.util.Duration;
import sandrone.Sandrone;

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
    @FXML
    private Button sendButton;

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
        String response = sandrone.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSandroneDialog(response, sandroneImage)
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
