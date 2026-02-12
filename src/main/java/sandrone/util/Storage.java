package sandrone.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import sandrone.exception.SandroneException;
import sandrone.task.Deadline;
import sandrone.task.Event;
import sandrone.task.Task;
import sandrone.task.TaskList;
import sandrone.task.Todo;
import sandrone.taskgenerators.TaskGenerator;
import sandrone.taskgenerators.TaskGeneratorList;

/**
 * Handles persistent storage for the chatbot.
 * This class is responsible for reading from and writing task data to a
 * local file, ensuring that the user's task list is preserved across sessions.
 *
 * @author Henry Tse
 * @version 0.1
 */
public class Storage {
    private String taskFilePath;
    private String generatorFilePath;

    /**
     * Constructs a {@code Storage} object and prepares the necessary file structure.
     *
     * @param taskFilePath The path to the local data file storing active tasks.
     * @param genFilePath The path to the local data file storing task generators.
     */
    public Storage(String taskFilePath, String genFilePath) {
        this.taskFilePath = taskFilePath;
        this.generatorFilePath = genFilePath;
        prepareFile(this.taskFilePath);
        prepareFile(generatorFilePath);
    }

    /**
     * Ensures the data file and its parent directories exist.
     * If the directories or file are missing, they are created automatically.
     */
    public void prepareFile(String pathStr) {
        try {
            Path path = Path.of(taskFilePath);
            Path parentDir = path.getParent();

            if (parentDir != null && !Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
                System.out.println("Directory created: " + parentDir);
            }

            if (!Files.exists(path)) {
                Files.createFile(path);
                System.out.println("New data file created: " + taskFilePath);
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
        saveToFile(taskFilePath, taskList.getAllTasks(), "Failed to save tasks");
    }

    public void saveGenerators(TaskGeneratorList genList) {
        saveToFile(generatorFilePath, genList.getAllGenerators(), "Failed to save generators");
    }

    /**
     * Generic helper to save any list of objects that can be converted to file format.
     * * @param filePath The destination file.
     * @param items    A list of objects (Tasks or Generators).
     * @param errorMsg The context-specific error message.
     */
    private void saveToFile(String filePath, List<?> items, String errorMsg) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Object item : items) {
                if (item instanceof Task) {
                    writer.println(((Task) item).toFileFormat());
                } else if (item instanceof TaskGenerator) {
                    writer.println(((TaskGenerator) item).toFileFormat());
                }
            }
        } catch (IOException e) {
            System.out.println(errorMsg + ": " + e.getMessage());
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
        Path path = Path.of(taskFilePath);

        if (!Files.exists(path)) return loadedTasks;

        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] dataComponents = parseCoreData(line);

                Task task = getTask(dataComponents);

                loadedTasks.add(task);
            }
        } catch (Exception e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return loadedTasks;
    }

    private static Task getTask(String[] dataComponents) throws SandroneException {
        Task task;

        String type = dataComponents[0];
        boolean isDone = dataComponents[0].equals("X");
        boolean isRecurring = dataComponents[2].equals("R");
        String description = dataComponents[3];

        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            LocalDate dueDate = Pulonia.parseDate(dataComponents[4].trim());
            task = new Deadline(description, dueDate);
            break;
        case "E":
            LocalDate startDate = Pulonia.parseDate(dataComponents[4].trim());
            LocalDate endDate = Pulonia.parseDate(dataComponents[5].trim());
            task = new Event(description, startDate, endDate);
            break;
        default:
            // This should be unreachable
            assert false : "Unknown task type: " + type;
            return null;
        }

        if (isDone) {
            task.mark();
        }

        task.setRecurring(isRecurring);
        return task;
    }

    private String[] parseCoreData(String line) {
        // Split once and trim all parts immediately to avoid alignment issues
        String[] parts = line.split(" \\| ");
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        return parts;
    }
}
