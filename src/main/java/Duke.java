import java.util.ArrayList;
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
            String userMessage = scanner.nextLine();
            String userCommand = userMessage.split("\\s+")[0];

            System.out.println(DIVIDER_LINE);
            switch (userCommand) {
                case "todo":
                case "deadline":
                case "event": {
                    String taskProperties = userMessage.split(userCommand, 2)[1].trim();
                    Task task = setupTask(userCommand, taskProperties);
                    tasks.add(task);

                    System.out.println(MESSAGE_ADD);
                    System.out.println(INDENTATION_MESSAGE + "  " + task.toString());
                    System.out.println(INDENTATION_MESSAGE + "Now you have " + tasks.size() + " task(s) in the list.");
                    break;
                }
                case "done": {
                    int index = Integer.parseInt(userMessage.split("\\s+")[1]);
                    Task task = tasks.get(index - 1);
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
        }
    }

    private static Task setupTask(String userCommand, String taskProperties) {
        switch (userCommand) {
            case "todo": {
                return new Todo(taskProperties);
            }
            case "deadline": {
                String description = taskProperties.split("/by")[0].trim();
                String dateTime = taskProperties.split("/by")[1].trim();
                return new Deadline(description, dateTime);
            }
            case "event": {
                String description = taskProperties.split("/at")[0].trim();
                String dateTime = taskProperties.split("/at")[1].trim();
                return new Event(description, dateTime);
            }
            default: {
                return null;
            }
        }
    }
}