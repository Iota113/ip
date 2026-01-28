import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        prepareFile();
    }

    private void prepareFile() {
        try {
            Path path = Paths.get(filePath);
            Path parentDir = path.getParent();

            if (parentDir != null && !Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
                System.out.println("Directory created: " + parentDir);
            }

            if (!Files.exists(path)) {
                Files.createFile(path);
                System.out.println("New data file created: " + filePath);
            }
        } catch (IOException e) {
            System.err.println("Could not initialize storage: " + e.getMessage());
        }
    }

    public void saveTasks(List<Task> tasks) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.println(task.toFileFormat());
            }
        } catch (IOException e) {
            System.out.println("Failed to save tasks: " + e.getMessage());
        }
    }
}
