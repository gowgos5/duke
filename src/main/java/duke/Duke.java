package duke;

import command.Command;
import exception.DukeException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private boolean exitFlag;

    public Duke(Ui ui, String filePath) {
        this.ui = ui;
        ui.showWelcomeMessage();

        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }

        exitFlag = false;
    }

    public void respond(String userMessage) {
        if (exitFlag) return;

        try {
            Command command = Parser.parse(userMessage);
            command.execute(tasks, ui, storage);
            exitFlag = command.isExit();
            if (exitFlag) ui.exit();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }
}