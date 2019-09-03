package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Message;
import ui.Ui;

import java.util.List;

public class FindCommand extends Command {
    private String searchTerm;

    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> foundTaskList = tasks.findTasks(searchTerm);

        StringBuilder message = new StringBuilder(foundTaskList.isEmpty() ? Message.MESSAGE_FIND_EMPTY
                : Message.MESSAGE_FIND_NON_EMPTY);
        message.append("\n");

        int i = 1;
        for (Task task : foundTaskList) {
            String tmp = i++ + ". " + task.toString() + "\n";
            message.append(tmp);
        }

        ui.showMessage(message.toString());
    }
}