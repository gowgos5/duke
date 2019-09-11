package ui;

import java.util.Scanner;

/**
 * Class responsible for the user interface of {@link duke.Duke Duke}.
 */
public class Ui {
    public static final String MESSAGE_ADD = "Got it. I've added this task:";
    public static final String MESSAGE_DONE = "Nice! I've marked this task as done:";
    public static final String MESSAGE_DELETE = "Noted. I've removed this task:";
    public static final String MESSAGE_FIND_EMPTY = "There are no matching tasks in your list. "
            + "Please try another keyword.";
    public static final String MESSAGE_FIND_NON_EMPTY = "Here are the matching task(s) in your list:";
    public static final String MESSAGE_LIST_EMPTY = "You have currently no tasks in your list.";
    public static final String MESSAGE_LIST_NON_EMPTY = "Here are the task(s) in your list:";

    private static final String DIVIDER_LINE = "    ____________________________________________________________";
    private static final String INDENTATION_MESSAGE = "     ";
    private static final String MESSAGE_WELCOME_GREET = "Hello! I'm Duke";
    private static final String MESSAGE_WELCOME_QUESTION = "What can I do for you?";
    private static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";

    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println(DIVIDER_LINE);
    }

    public void showBlankLine() {
        System.out.println();
    }

    public void showMessage(String message) {
        System.out.println(INDENTATION_MESSAGE + message);
    }

    public void showError(String errorMessage) {
        System.out.println(INDENTATION_MESSAGE + errorMessage);
    }

    public void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        showLine();
        showMessage(MESSAGE_WELCOME_GREET);
        showMessage(MESSAGE_WELCOME_QUESTION);
        showLine();
        showBlankLine();
    }

    public void showExitMessage() {
        showMessage(MESSAGE_EXIT);
    }

    public void showLoadingError(String errorMessage) {
        showLine();
        showMessage(errorMessage);
        showLine();
        showBlankLine();
    }

    public String readUserMessage() {
        return scanner.nextLine().trim();
    }
}
