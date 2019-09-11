package command;

import exception.DukeException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents a specialised {@link command.Command} to update the status (mark as completed) of a specified
 * {@link task.Task} in the user's Task list.
 */
public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int taskIndex) {
        this.index = taskIndex;
    }

    /**
     * Executes Command to update a specified Task.
     *
     * @param tasks   User's current {@link task.TaskList}.
     * @param ui      {@link ui.Ui} object.
     * @param storage {@link storage.Storage} object.
     * @throws DukeException If the requested Task to be updated does not exist,
     *                       or the storage object faces an error writing to the text file (saved in the hard drive).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index < 0 || index > (tasks.getSize() - 1)) {
            throw new DukeException(DukeException.EXCEPTION_TASK_NOT_FOUND);
        }

        Task task = tasks.markTaskAsDone(index);
        storage.save(tasks.getTasks());

        ui.showMessage(Ui.MESSAGE_DONE);
        ui.showMessage("  " + task.toString());
    }
}