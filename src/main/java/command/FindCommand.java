package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.util.List;

/**
 * Represents a specialised {@link command.Command} to search for relevant {@link task.Task Tasks}
 * in the user's Task list.
 */
public class FindCommand extends Command {
    private String searchTerm;

    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    /**
     * Executes Command to search for relevant Tasks in the user's Task list.
     *
     * @param tasks   User's current {@link task.TaskList}.
     * @param ui      {@link ui.Ui} object.
     * @param storage {@link storage.Storage} object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> foundTaskList = tasks.findTasks(searchTerm);

        String message = foundTaskList.isEmpty() ? Ui.MESSAGE_FIND_EMPTY : Ui.MESSAGE_FIND_NON_EMPTY;
        ui.showMessage(message);

        int i = 1;
        for (Task task : foundTaskList) {
            ui.showMessage(i++ + "." + task.toString());
        }
    }
}
