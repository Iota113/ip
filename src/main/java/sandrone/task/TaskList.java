package sandrone.task;

import java.util.ArrayList;

import sandrone.util.Storage;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(Storage listData) {
        this.tasks = listData.loadTasks();
    }

    public void addTask(Task newTask) {
        this.tasks.add(newTask);
    }

    public void setTaskStatus(int id, boolean isMark) {
        if (id > tasks.size()) {
            System.out.println("You do not have that many tasks.");
        }

        if (isMark) {
            tasks.get(id).mark();
        } else {
            tasks.get(id).unmark();
        }
    }

    public String deleteTask(int id) {
        tasks.remove(id);
        return "Your task has been deleted.";
    }

    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }

    public int getTaskCount() {
        return this.tasks.size();
    }

    public Task getTask(int id) {
        return this.tasks.get(id);
    }

    public ArrayList<Task> getMatchingTasks(String userInput) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.getDescription().contains(userInput)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
