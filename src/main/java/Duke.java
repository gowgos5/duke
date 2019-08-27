import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String DIVIDER_LINE = "    ____________________________________________________________";
    private static final String INDENTATION_MESSAGE = "     ";
    private static final String MESSAGE_GREET = INDENTATION_MESSAGE + "Hello! I'm Duke\n" +
            INDENTATION_MESSAGE + "What can I do for you?";
    private static final String MESSAGE_EXIT = INDENTATION_MESSAGE + "Bye. Hope to see you again soon!";
    private static final String MESSAGE_ADD = INDENTATION_MESSAGE + "added: ";

    public static void main(String[] args) {
        List<String> userMessages = new ArrayList<>();

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

            System.out.println(DIVIDER_LINE);
            switch (userMessage) {
                case "list":
                    int i = 1;
                    for (String message : userMessages) {
                        System.out.println(INDENTATION_MESSAGE + i++ + ". " + message);
                    }
                    break;
                case "bye":
                    System.out.println(MESSAGE_EXIT);
                    exitFlag = true;
                    break;
                default:
                    userMessages.add(userMessage);
                    System.out.println(MESSAGE_ADD + userMessage);
                    break;
            }
            System.out.println(DIVIDER_LINE + "\n");
        }
    }
}