import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String DIVIDER_LINE = "    ____________________________________________________________";
    private static final String INDENTATION_MESSAGE = "     ";
    private static final String MESSAGE_GREET = INDENTATION_MESSAGE + "Hello! I'm Duke\n"
            + INDENTATION_MESSAGE + "What can I do for you?";
    private static final String MESSAGE_ADD = INDENTATION_MESSAGE + "Got it. I've added this task:";
    private static final String MESSAGE_DONE = INDENTATION_MESSAGE + "Nice! I've marked this task as done:";
    private static final String MESSAGE_REMOVE = INDENTATION_MESSAGE + "Noted. I've removed this task:";
    private static final String MESSAGE_FIND_EMPTY = INDENTATION_MESSAGE + "There are no matching tasks in your list. "
            + "Please try another keyword.";
    private static final String MESSAGE_FIND_NON_EMPTY = INDENTATION_MESSAGE + "Here are the matching tasks in your list:";
    private static final String MESSAGE_LIST_EMPTY = INDENTATION_MESSAGE + "You have currently no tasks in your list.";
    private static final String MESSAGE_LIST_NON_EMPTY = INDENTATION_MESSAGE + "Here are the task(s) in your list:";
    private static final String MESSAGE_EXIT = INDENTATION_MESSAGE + "Bye. Hope to see you again soon!";

    private static final String[] TYPE_COMMANDS = {"todo", "deadline", "event", "find", "delete", "done", "list", "bye"};

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Greet user
        System.out.println(DIVIDER_LINE);
        System.out.println(MESSAGE_GREET);
        System.out.println(DIVIDER_LINE + "\n");

        Storage storage = null;
        List<Task> tasks = new ArrayList<>();
        try {
            storage = new Storage();
            tasks = storage.getTasks();
        } catch (DukeException e) {
            System.out.println(DIVIDER_LINE);
            System.out.println(INDENTATION_MESSAGE + e.getMessage());
            System.out.println(DIVIDER_LINE + "\n");
        }

        Scanner scanner = new Scanner(System.in);
        boolean exitFlag = false;

        while (!exitFlag) {
            String userMessage = scanner.nextLine().trim();
            System.out.println(DIVIDER_LINE);

            try {
                String userCommand = userMessage.split("\\s+")[0];
                if (!Arrays.asList(TYPE_COMMANDS).contains(userCommand)) {
                    throw new DukeException(DukeException.EXCEPTION_UNKNOWN_COMMAND);
                }

                switch (userCommand) {
                case "todo":
                    // Fallthrough
                case "deadline":
                    // Fallthrough
                case "event": {
                    String taskProperties = userMessage.split(userCommand, 2)[1].trim();
                    if (taskProperties.isEmpty()) {
                        throw new DukeException(DukeException.EXCEPTION_EMPTY_DESCRIPTION);
                    }

                    Task task = setupTask(userCommand, taskProperties);
                    if (storage == null) {
                        throw new DukeException(DukeException.EXCEPTION_ERROR_WRITE_FILE);
                    }
                    tasks.add(task);
                    storage.updateFile(tasks);

                    System.out.println(MESSAGE_ADD);
                    System.out.println(INDENTATION_MESSAGE + "  " + task.toString());
                    System.out.println(INDENTATION_MESSAGE + "Now you have " + tasks.size() + " task(s) in the list.");
                    break;
                }
                case "delete":
                    // Fallthrough
                case "done": {
                    int index;
                    try {
                        index = Integer.parseInt(userMessage.split("\\s+")[1]) - 1;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException(DukeException.EXCEPTION_EMPTY_DESCRIPTION);
                    } catch (NumberFormatException e) {
                        throw new DukeException(DukeException.EXCEPTION_NON_INTEGER_DESCRIPTION);
                    }

                    if (index < 0 || index > tasks.size() - 1) {
                        throw new DukeException(DukeException.EXCEPTION_TASK_NOT_FOUND);
                    }

                    Task task = tasks.get(index);
                    if (userCommand.equals("done")) {
                        task.markAsDone();
                        storage.updateFile(tasks);

                        System.out.println(MESSAGE_DONE);
                        System.out.println(INDENTATION_MESSAGE + "  " + task.toString());
                    } else {
                        tasks.remove(index);
                        storage.updateFile(tasks);

                        System.out.println(MESSAGE_REMOVE);
                        System.out.println(INDENTATION_MESSAGE + "  " + task.toString());
                        System.out.println(INDENTATION_MESSAGE + "Now you have " + tasks.size() + " task(s) in the list.");
                    }
                    break;
                }
                case "find": {
                    String searchTerm = userMessage.split(userCommand, 2)[1].trim();
                    if (searchTerm.isEmpty()) {
                        throw new DukeException(DukeException.EXCEPTION_EMPTY_DESCRIPTION);
                    }

                    if (storage == null) {
                        throw new DukeException(DukeException.EXCEPTION_ERROR_READ_FILE);
                    }

                    List<Task> foundTasks = new ArrayList<>();
                    for (Task task : tasks) {
                        if (task.toString().contains(searchTerm)) {
                            foundTasks.add(task);
                        }
                    }

                    System.out.println(foundTasks.isEmpty() ? MESSAGE_FIND_EMPTY : MESSAGE_FIND_NON_EMPTY);

                    int i = 1;
                    for (Task task : foundTasks) {
                        System.out.println(INDENTATION_MESSAGE + i++ + "." + task.toString());
                    }
                    break;
                }
                case "list": {
                    if (storage == null) {
                        throw new DukeException(DukeException.EXCEPTION_ERROR_READ_FILE);
                    }

                    System.out.println(tasks.isEmpty() ? MESSAGE_LIST_EMPTY : MESSAGE_LIST_NON_EMPTY);

                    int i = 1;
                    for (Task task : tasks) {
                        System.out.println(INDENTATION_MESSAGE + i++ + "." + task.toString());
                    }
                    break;
                }
                case "bye":
                    exitFlag = true;
                    System.out.println(MESSAGE_EXIT);
                    break;
                }

                System.out.println(DIVIDER_LINE + "\n");
            } catch (DukeException e) {
                System.out.println(INDENTATION_MESSAGE + e.getMessage());
                System.out.println(DIVIDER_LINE + "\n");
            }
        }
    }

    private static Task setupTask(String taskType, String taskProperties) throws DukeException {
        switch (taskType) {
        case "todo":
            return new Todo(taskProperties);
        case "deadline":
            // Fallthrough
        case "event":
            return setupTaskWithDateTime(taskType, taskProperties);
        default:
            throw new DukeException(DukeException.EXCEPTION_UNKNOWN_TASK);
        }
    }

    private static Task setupTaskWithDateTime(String taskType, String taskProperties) throws DukeException {
        String term;
        if (taskType.equals("deadline")) {
            term = "/by";
        } else {
            term = "/at";
        }

        String description = taskProperties.split(term, 2)[0].trim();
        if (description.isEmpty()) {
            throw new DukeException(DukeException.EXCEPTION_EMPTY_DESCRIPTION);
        }

        String taskDateTime;
        try {
            taskDateTime = taskProperties.split(term, 2)[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(DukeException.EXCEPTION_EMPTY_DATETIME);
        }

        if (taskDateTime.isEmpty()) {
            throw new DukeException(DukeException.EXCEPTION_EMPTY_DATETIME);
        }

        LocalDateTime dateTime = DateTimeParser.formatStringToDateTime(taskDateTime);
        if (taskType.equals("deadline")) {
            return new Deadline(description, dateTime);
        } else {
            return new Event(description, dateTime);
        }
    }
}