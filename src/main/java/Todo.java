public class Todo extends Task {

    public Todo(String desc) {
        super(desc);
    }

    @Override
    public String getTaskType() {
        return "T";
    }
}
