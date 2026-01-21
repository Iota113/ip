public class TaskList {
    private Task[] tasks = new Task[100];
    private int count = 0;

    public void addTask(String desc) {
        tasks[count++] = new Task(desc);
        System.out.println("added: " + desc);
    }

    public Task getTask(int id) {
        return tasks[id];
    }

    public void setTaskStatus(int id, boolean isMark) {
        if (id >= count) {
            System.out.println("You do not have that many tasks.");
            return;
        }

        if (isMark) {
            tasks[id].mark();
            System.out.println("Very well.");
        } else {
            tasks[id].unmark();
            System.out.println("Utterly risible.");
        }
    }

    public void printList() {
        for (int i = 0; i < count; i++) {
            // not my favourite solution to zero-index
            System.out.println((i+1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
        }
    }
}