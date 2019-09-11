package command;

import exception.DukeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Abstract base class for all of the Commands {@link duke.Duke Duke} may execute during its operation.
 */
public abstract class Command {
    protected boolean exit;

    Command() {
        exit = false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return exit;
    }
}
