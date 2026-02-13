package sandrone.taskgenerators;

import java.util.ArrayList;
import java.util.List;

import sandrone.util.Storage;

/**
 * Manages the collection of task generators in the Sandrone chatbot.
 * Mirroing the Task List class, this is essentially a wrapper class for
 * an ArrayList that provides methods to add, delete, find, modify the status
 * of tasks generators and call on them to create an instance of a task.
 *
 * @author Henry Tse
 * @version 0.2
 */
public class TaskGeneratorList {
    private ArrayList<TaskGenerator> taskGenerators;

    public TaskGeneratorList(Storage storage) {
        this.taskGenerators = storage.loadGenerators();
    }

    /**
     * Adds a new task generator to the collection.
     *
     * @param newTaskGenerator The task generator to be added.
     */
    public void addTaskGenerator(TaskGenerator newTaskGenerator) {
        this.taskGenerators.add(newTaskGenerator);
    }

    public List<TaskGenerator> getAllGenerators() {
        return this.taskGenerators;
    }

    public void deleteTaskGenerator(int taskGeneratorIndex) {
        this.taskGenerators.remove(taskGeneratorIndex);
    }

    public int getGeneratorCount() {
        return this.taskGenerators.size();
    }

}
