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

    public String addTask(String input) throws SandroneException {
        TaskType taskType = TaskType.fromCommand(input);
        Task newTask;
        switch (taskType) {
            case TODO:
                newTask = new Todo(input);
                break;
            case DEADLINE:
                newTask = new Deadline(input);
                break;
            case EVENT:
                newTask = new Event(input);
                break;
            default:
                String message =
                        "You Fool. What are you saying.\n"
                        + "Here are the valid commands:\n"
                        + "1. todo [insert desc] \n"
                        + "2. deadline [insert desc] /by [insert time]\n"
                        + "3. event [insert desc] /from [insert time] /to [insert time]\n"
                        + "4. mark/unmark [insert index]\n"
                        + "5. list";
                throw new SandroneException(message);
        }

        tasks[count++] = newTask;

        String message =
                "Very well. You have " + count + " task(s) now.\n" +
                        count + "."
                        + "[" + tasks[count - 1].getStatusIcon() + "]"
                        + "[" + tasks[count - 1].getTaskType() + "] "
                        + tasks[count - 1].getDescription();
        return message;
    }

    public String setTaskStatus(int id, boolean isMark) {
        if (id >= count) {
            return "You do not have that many tasks.";
        }

        if (isMark) {
            tasks[id].mark();
            return "Very well.";
        } else {
            tasks[id].unmark();
            return "Utterly risible.";
        }
    }

    public void printList() {
        System.out.println("____________________________________________________________");
        System.out.println("Your List:");
        for (int i = 0; i < count; i++) {
            // not my favourite solution to zero-index
            System.out.println(
                    (i+1) + "."
                    + "[" + tasks[i].getStatusIcon() + "]"
                    + "[" + tasks[i].getTaskType() + "] "
                    + tasks[i].getDescription());
        }
        System.out.println("____________________________________________________________");
    }
}