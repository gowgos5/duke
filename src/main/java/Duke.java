import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String DIVIDER_LINE = "    ____________________________________________________________";
    private static final String INDENTATION_MESSAGE = "     ";
    private static final String MESSAGE_GREET = INDENTATION_MESSAGE + "Hello! I'm Duke\n" +
            INDENTATION_MESSAGE + "What can I do for you?";
    private static final String MESSAGE_ADD = INDENTATION_MESSAGE + "Got it. I've added this task:";
    private static final String MESSAGE_DONE = INDENTATION_MESSAGE + "Nice! I've marked this task as done:";
    private static final String MESSAGE_LIST = INDENTATION_MESSAGE + "Here are the task(s) in your list:";
    private static final String MESSAGE_EXIT = INDENTATION_MESSAGE + "Bye. Hope to see you again soon!";

    private static final String[] TYPE_COMMANDS = {"todo", "deadline", "event", "done", "list", "bye"};

    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();

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
                    case "deadline":
                    case "event": {
                        String taskProperties = userMessage.split(userCommand, 2)[1].trim();
                        if (taskProperties.isEmpty()) {
                            throw new DukeException(DukeException.EXCEPTION_EMPTY_DESCRIPTION);
                        }

                        Task task = setupTask(userCommand, taskProperties);
                        tasks.add(task);

                        System.out.println(MESSAGE_ADD);
                        System.out.println(INDENTATION_MESSAGE + "  " + task.toString());
                        System.out.println(INDENTATION_MESSAGE + "Now you have " + tasks.size() + " task(s) in the list.");
                        break;
                    }
                    case "done": {
                        int index;
                        try {
                            index = Integer.parseInt(userMessage.split("\\s+")[1]) - 1;
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new DukeException(DukeException.EXCEPTION_EMPTY_DESCRIPTION);
                        }

                        if (index < 0 || index > tasks.size() - 1) {
                            throw new DukeException(DukeException.EXCEPTION_TASK_NOT_FOUND);
                        }

                        Task task = tasks.get(index);
                        task.markAsDone();

                        System.out.println(MESSAGE_DONE);
                        System.out.println(INDENTATION_MESSAGE + "  " + task.toString());
                        break;
                    }
                    case "list": {
                        System.out.println(MESSAGE_LIST);

                        int i = 1;
                        for (Task task : tasks) {
                            System.out.println(INDENTATION_MESSAGE + i++ + "." + task.toString());
                        }
                        break;
                    }
                    case "bye": {
                        exitFlag = true;
                        System.out.println(MESSAGE_EXIT);
                        break;
                    }
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
            case "todo": {
                return new Todo(taskProperties);
            }
            case "deadline":
            case "event": {
                return setupTaskWithDateTime(taskType, taskProperties);
            }
            default: {
                throw new DukeException(DukeException.EXCEPTION_UNKNOWN_TASK);
            }
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

        String dateTime;
        try {
            dateTime = taskProperties.split(term, 2)[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(DukeException.EXCEPTION_EMPTY_DATETIME);
        }

        if (dateTime.isEmpty()) {
            throw new DukeException(DukeException.EXCEPTION_EMPTY_DATETIME);
        }

        if (taskType.equals("deadline")) {
            return new Deadline(description, dateTime);
        } else {
            return new Event(description, dateTime);
        }
    }
}