package command;

import storage.Storage;
import task.Task;
import task.TaskList;
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

        String message = foundTaskList.isEmpty() ? Ui.MESSAGE_FIND_EMPTY : Ui.MESSAGE_FIND_NON_EMPTY;
        ui.showMessage(message);

        int i = 1;
        for (Task task : foundTaskList) {
            ui.showMessage(i++ + "." + task.toString());
        }
    }
}
