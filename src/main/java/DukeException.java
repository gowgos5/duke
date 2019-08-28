public class DukeException extends Exception {
    public static final String EXCEPTION_UNKNOWN_COMMAND = "Sorry, I do not understand this command. " +
            "Please enter a proper command.";
    public static final String EXCEPTION_EMPTY_DESCRIPTION = "The description of this command cannot be empty. " +
            "Please enter a description.";
    public static final String EXCEPTION_EMPTY_DATETIME = "The date/time argument of this command cannot be empty. " +
            "Please enter a date and/or time.";
    public static final String EXCEPTION_TASK_NOT_FOUND = "You have selected a task that does not exist in your current list. " +
            "Please enter the command \"list\" to view your current list.";
    public static final String EXCEPTION_UNKNOWN_TASK = "I do not know how to create such a task.";
    public static final String EXCEPTION_ERROR_CREATE_FILE = "I am unable to create file duke.txt. " +
            "Please exit the program and try again later.";
    public static final String EXCEPTION_ERROR_READ_FILE = "Failure reading from file duke.txt. " +
            "Please exit the program and try again later.";
    public static final String EXCEPTION_ERROR_WRITE_FILE = "Failure writing to file duke.txt." +
            "Please exit the program and try again later.";

    public DukeException(String message) {
        super(message);
    }
}
