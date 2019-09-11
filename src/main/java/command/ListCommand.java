package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents a specialised {@link command.Command} to display all {@link task.Task Tasks} in the user's Task list.
 */
public class ListCommand extends Command {
    /**
     * Executes Command to display all Tasks in the user's Task list.
     *
     * @param tasks   User's current {@link task.TaskList}.
     * @param ui      {@link ui.Ui} object.
     * @param storage {@link storage.Storage} object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String message = (tasks.getSize() == 0) ? Ui.MESSAGE_LIST_EMPTY : Ui.MESSAGE_LIST_NON_EMPTY;
        ui.showMessage(message);

        int i = 1;
        for (Task task : tasks.getTasks()) {
            ui.showMessage(i++ + "." + task.toString());
        }
    }
}
