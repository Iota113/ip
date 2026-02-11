package sandrone.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import sandrone.task.Deadline;
import sandrone.task.Event;
import sandrone.task.Task;
import sandrone.task.TaskList;
import sandrone.task.Todo;

/**
 * Handles persistent storage for the chatbot.
 * This class is responsible for reading from and writing task data to a
 * local file, ensuring that the user's task list is preserved across sessions.
 *
 * @author Henry Tse
 * @version 0.1
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a {@code Storage} object and prepares the necessary file structure.
     *
     * @param filePath The path to the local data file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        prepareFile();
    }

    /**
     * Ensures the data file and its parent directories exist.
     * If the directories or file are missing, they are created automatically.
     */
    public void prepareFile() {
        try {
            Path path = Path.of(filePath);
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

    /**
     * Writes the current list of tasks to the local file.
     * Tasks are converted into a pipe-separated string format before saving.
     *
     * @param taskList The TaskList object to be saved
     */
    public void saveTasks(TaskList taskList) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            ArrayList<Task> tasks = taskList.getAllTasks();
            for (Task task : tasks) {
                writer.println(task.toFileFormat());
            }
        } catch (IOException e) {
            System.out.println("Failed to save tasks: " + e.getMessage());
        }
    }

    /**
     * Reads task data from the local file and reconstructs the task list.
     * Parses each line of the file into specific {@code Todo}, {@code Deadline},
     * or {@code Event} objects.
     *
     * @return An {@code ArrayList} of tasks retrieved from the file.
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        Path path = Path.of(filePath);

        if (!Files.exists(path)) {
            return loadedTasks;
        }

        try {
            List<String> lines = Files.readAllLines(path);

            for (String line : lines) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("X");
                String description = parts[2];

                switch (type) {
                case "T":
                    Todo todo = new Todo(description);
                    if (isDone) {
                        todo.mark();
                    }
                    loadedTasks.add(todo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(description, Pulonia.parseDate(parts[3]));
                    if (isDone) {
                        deadline.mark();
                    }
                    loadedTasks.add(deadline);
                    break;
                case "E":
                    Event event = new Event(description, Pulonia.parseDate(parts[3]), Pulonia.parseDate(parts[4]));
                    if (isDone) {
                        event.mark();
                    }
                    loadedTasks.add(event);
                    break;
                default:
                    // do nothing
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        return loadedTasks;
    }
}
