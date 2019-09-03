package command;

import exception.DukeException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Message;
import ui.Ui;

public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int taskIndex) {
        this.index = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index < 0 || index > (tasks.getSize() - 1)) {
            throw new DukeException(DukeException.EXCEPTION_TASK_NOT_FOUND);
        }

        Task task = tasks.markTaskAsDone(index);
        storage.save(tasks.getTasks());

        String message = Message.MESSAGE_DONE + "\n" + "  " + task.toString();
        ui.showMessage(message);
    }
}