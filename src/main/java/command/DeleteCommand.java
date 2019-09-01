package command;

import exception.DukeException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int taskIndex) {
        this.index = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index < 0 || index > (tasks.getSize() - 1)) {
            throw new DukeException(DukeException.EXCEPTION_TASK_NOT_FOUND);
        }

        Task task = tasks.deleteTask(index);
        storage.save(tasks.getTasks());

        ui.showMessage(Ui.MESSAGE_DELETE);
        ui.showMessage("  " + task.toString());
        ui.showMessage("Now you have " + tasks.getSize() + " task(s) in the list.");
    }
}