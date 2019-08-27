import java.util.Scanner;

public class Duke {
    private static final String DIVIDER_LINE = "    ____________________________________________________________";
    private static final String INDENTATION_MESSAGE = "     ";
    private static final String MESSAGE_GREET = "Hello! I'm Duke\n" + INDENTATION_MESSAGE + "What can I do for you?";
    private static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Greet user
        System.out.println(DIVIDER_LINE);
        System.out.println(INDENTATION_MESSAGE + MESSAGE_GREET);
        System.out.println(DIVIDER_LINE + "\n");

        Scanner scanner = new Scanner(System.in);
        boolean exitFlag = false;

        while (!exitFlag) {
            String userCommand = scanner.nextLine();

            System.out.println(DIVIDER_LINE);
            if (userCommand.equals("bye")) {
                System.out.println(INDENTATION_MESSAGE + MESSAGE_EXIT);
                exitFlag = true;
            } else {
                System.out.println(INDENTATION_MESSAGE + userCommand);
            }
            System.out.println(DIVIDER_LINE + "\n");
        }
    }
}