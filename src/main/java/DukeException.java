public class DukeException extends Exception {
    public static final String EXCEPTION_UNKNOWN_COMMAND = "I do not understand this command.";
    public static final String EXCEPTION_EMPTY_DESCRIPTION = "The description of this command cannot be empty.";
    public static final String EXCEPTION_EMPTY_DATETIME = "The date/time argument of this command cannot be empty";
    public static final String EXCEPTION_TASKS_OUTOFBOUNDS = "There is no such task in your current list.";
    public static final String EXCEPTION_UNKNOWN_TASK = "I do not yet possess the capability to create such tasks.";

    public DukeException(String message) {
        super(message);
    }
}
