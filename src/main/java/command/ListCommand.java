package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Message;
import ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder message = new StringBuilder((tasks.getSize() == 0) ? Message.MESSAGE_LIST_EMPTY
                : Message.MESSAGE_LIST_NON_EMPTY);
        message.append("\n");

        int i = 1;
        for (Task task : tasks.getTasks()) {
            String tmp = i++ + ". " + task.toString() + "\n";
            message.append(tmp);
        }

        ui.showMessage(message.toString());
    }
}
