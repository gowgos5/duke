import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String DIVIDER_LINE = "    ____________________________________________________________";
    private static final String INDENTATION_MESSAGE = "     ";
    private static final String MESSAGE_GREET = INDENTATION_MESSAGE + "Hello! I'm Duke\n" +
            INDENTATION_MESSAGE + "What can I do for you?";
    private static final String MESSAGE_ADD = INDENTATION_MESSAGE + "added: ";
    private static final String MESSAGE_DONE = INDENTATION_MESSAGE + "Nice! I've marked this task as done:";
    private static final String MESSAGE_LIST = INDENTATION_MESSAGE + "Here are the tasks in your list:";
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
                case "done": {
                    int index = Integer.parseInt(userMessage.split("\\s+")[1]);
                    Task task = tasks.get(index - 1);
                    task.markAsDone();

                    System.out.println(MESSAGE_DONE);
                    System.out.println(INDENTATION_MESSAGE + "  " + task.getStatusIcon() + " " + task.getDescription());
                    break;
                }
                case "list": {
                    System.out.println(MESSAGE_LIST);

                    int i = 1;
                    for (Task task : tasks) {
                        System.out.println(INDENTATION_MESSAGE + i++ + "." + task.getStatusIcon() + " " +
                                task.getDescription());
                    }
                    break;
                }
                case "bye": {
                    exitFlag = true;
                    System.out.println(MESSAGE_EXIT);
                    break;
                }
                default: {
                    tasks.add(new Task(userMessage));
                    System.out.println(MESSAGE_ADD + userMessage);
                    break;
                }
            }
            System.out.println(DIVIDER_LINE + "\n");
        }
    }
}