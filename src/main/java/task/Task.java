package task;

/**
 * Class representing a generic Task.
 */
public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks Task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Gets status icon of Task.
     *
     * @return Tick if Task has been completed. Otherwise, returns "X" symbol.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); // return tick or X symbols
    }

    /**
     * Gets description of Task.
     *
     * @return Task's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets current status of Task.
     *
     * @return True if Task is completed. Otherwise, returns false.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Gets full details of Task.
     *
     * @return Task's full details.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}