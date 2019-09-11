package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents a specialised {@link command.Command} to terminate Duke.
 */
public class ExitCommand extends Command {
    public ExitCommand() {
    }

    /**
     * Executes Command to terminate Duke.
     *
     * @param tasks   User's current {@link task.TaskList}.
     * @param ui      {@link ui.Ui} object.
     * @param storage {@link storage.Storage} object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        exit = true;
        ui.showExitMessage();
    }
}