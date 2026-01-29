import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(Storage listData) {
        this.tasks = listData.loadTasks();
    }

    public void addTask(Task newTask) {
        this.tasks.add(newTask);
        int count = tasks.size();
        String addTaskMessage =
                "Very well. You have " + count + " task(s) now.\n" +
                        count + "."
                        + "[" + tasks.get(count - 1).getStatusIcon() + "]"
                        + "[" + tasks.get(count - 1).getTaskType() + "] "
                        + tasks.get(count - 1).getDescription();
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

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

}