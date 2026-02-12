package sandrone.taskgenerators;

import java.util.ArrayList;

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

    public TaskGeneratorList() {
        this.taskGenerators = new ArrayList<>();
    }

    /**
     * Adds a new task generator to the collection.
     *
     * @param newTaskGenerator The task generator to be added.
     */
    public void addTaskGenerator(TaskGenerator newTaskGenerator) {
        this.taskGenerators.add(newTaskGenerator);
    }

}
