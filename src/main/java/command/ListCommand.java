package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class ListCommand extends Command {
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
