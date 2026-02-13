import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import sandrone.Sandrone;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private String taskPath = "./data/sandrone_task_list.txt";
    private String generatorPath = "./data/sandrone_task_generator_list.txt";

    private Sandrone sandrone = new Sandrone(taskPath, generatorPath);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSandrone(sandrone);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
