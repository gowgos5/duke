package duke;

import command.Command;
import exception.DukeException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * A Personal Assistant Chatbot that helps a person keep track of his/her {@link task.Task Tasks}.
 */
public class Duke {
    private static final String PATH_FILE_TASK_LIST = System.getProperty("user.dir") + "/data/duke.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Duke(String filePath) {
        ui = new Ui();

        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke(PATH_FILE_TASK_LIST).run();
    }

    private void run() {
        ui.showWelcomeMessage();

        boolean isExit = false;
        while (!isExit) {
            try {
                String userMessage = ui.readUserMessage();
                ui.showLine();
                Command command = Parser.parse(userMessage);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
                ui.showBlankLine();
            }
        }
    }
}