public class TaskList {
    private Task[] tasks = new Task[100];
    private int count = 0;

    private enum TaskType {
        TODO, DEADLINE, EVENT, DEFAULT;

        public static TaskType fromCommand(String input) {
            if (input == null) return DEFAULT;
            try {
                return TaskType.valueOf(input.split(" ")[0].toUpperCase());
            } catch (IllegalArgumentException e) {
                return DEFAULT;
            }
        }
    }

    public void addTask(String input) {
        TaskType taskType = TaskType.fromCommand(input);
        switch (taskType) {
            case TODO:
                tasks[count++] = new Todo(input);
                System.out.println();
                break;
            case DEADLINE:
                tasks[count++] = new Deadline(input);
                break;
            case EVENT:
                tasks[count++] = new Event(input);
                break;
            default:
                tasks[count++] = new Task(input);
                System.out.println("added task: " + input);
        }

        System.out.println("Very well. You have " + count + " task(s) now");
        System.out.println(
                count + "."
                        + "[" + tasks[count - 1].getStatusIcon() + "]"
                        + "[" + tasks[count - 1].getTaskType() + "] "
                        + tasks[count - 1].getDescription());
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
            System.out.println(
                    (i+1) + "."
                    + "[" + tasks[i].getStatusIcon() + "]"
                    + "[" + tasks[i].getTaskType() + "] "
                    + tasks[i].getDescription());
        }
    }
}