package task;

/**
 * Class representing a specialised {@link task.Task}.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Gets full details of Todo.
     *
     * @return Todo's full details.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
