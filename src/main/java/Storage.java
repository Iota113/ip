import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    public ArrayList<Task> loadTasks() {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        Path path = Paths.get(filePath);

        if (!Files.exists(path)) {
            return loadedTasks;
        }

        try {
            List<String> lines = Files.readAllLines(path);

            for (String line : lines) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("X");
                String description = parts[2];

                switch (type) {
                case "T":
                    Todo todo = new Todo(description);
                    if (isDone) todo.mark();
                    loadedTasks.add(todo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(description, parts[3]);
                    if (isDone) deadline.mark();
                    loadedTasks.add(deadline);
                    break;
                case "E":
                    Event event = new Event(description, parts[3], parts[4]);
                    if (isDone) event.mark();
                    loadedTasks.add(event);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        return loadedTasks;
    }
}
