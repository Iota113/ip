import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;
    private Storage listData;

    public TaskList() {
        this.listData = new Storage("./data/sandrone_task_list.txt");
        this.tasks = listData.loadTasks();
    }

    private enum Command {
        LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, DEFAULT;

        public static Command getCommand(String input) {
            if (input == null) return DEFAULT;
            try {
                return Command.valueOf(input.split(" ")[0].toUpperCase());
            } catch (IllegalArgumentException e) {
                return DEFAULT;
            }
        }
    }

    public String performCommand(String input) throws SandroneException {
        Command command = Command.getCommand(input);
        Task newTask = null;
        switch (command) {
            case LIST:
                printList();
                return "";
            case MARK:
                return setTaskStatus(Pulonia.extractIndex(input), true);
            case UNMARK:
                return setTaskStatus(Pulonia.extractIndex(input), false);
            case DELETE:
                return deleteTask(Pulonia.extractIndex(input));
            case TODO:
            case DEADLINE:
            case EVENT:
                newTask = Pulonia.parseNewTaskCommand(input);
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

        // Adding Task, Deadline or Event
        tasks.add(newTask);
        listData.saveTasks(tasks);
        int count = tasks.size();
        String addTaskMessage =
                "Very well. You have " + count + " task(s) now.\n" +
                        count + "."
                        + "[" + tasks.get(count - 1).getStatusIcon() + "]"
                        + "[" + tasks.get(count - 1).getTaskType() + "] "
                        + tasks.get(count - 1).getDescription();
        return addTaskMessage;
    }

    public String setTaskStatus(int id, boolean isMark) {
        if (id > tasks.size()) {
            return "You do not have that many tasks.";
        }

        if (isMark) {
            tasks.get(id).mark();
            listData.saveTasks(tasks);
            return "Very well.";
        } else {
            tasks.get(id).unmark();
            listData.saveTasks(tasks);
            return "Utterly risible.";
        }
    }

    public String deleteTask(int id) {
        tasks.remove(id);
        listData.saveTasks(tasks);
        return "Your task has been deleted.";
    }

    public void printList() {
        System.out.println("Your List:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(
                    (i + 1) + "."
                            + "[" + tasks.get(i).getStatusIcon() + "]"
                            + "[" + tasks.get(i).getTaskType() + "] "
                            + tasks.get(i).getDescription());
        }
    }
}