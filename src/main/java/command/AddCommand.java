package command;

import exception.DukeException;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;
import ui.Ui;

import java.time.LocalDateTime;

/**
 * Represents a specialised {@link command.Command} to add {@link task.Task} to the user's Task list.
 */
public class AddCommand extends Command {
    private String taskType;
    private String taskDescription;
    private LocalDateTime taskDateTime;

    public AddCommand(String taskType, String taskDescription) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.taskDateTime = null;
    }

    public AddCommand(String taskType, String taskDescription, LocalDateTime taskDateTime) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.taskDateTime = taskDateTime;
    }

    /**
     * Executes Command to add Task.
     *
     * @param tasks   User's current {@link task.TaskList}.
     * @param ui      {@link ui.Ui} object.
     * @param storage {@link storage.Storage} object.
     * @throws DukeException If the storage object faces an error writing to the text file (in the hard drive).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task;
        switch (taskType) {
        case "todo":
            task = new Todo(taskDescription);
            break;
        case "deadline":
            task = new Deadline(taskDescription, taskDateTime);
            break;
        default:
            // case "event"
            task = new Event(taskDescription, taskDateTime);
            break;
        }

        tasks.addTask(task);
        storage.save(tasks.getTasks());

        ui.showMessage(Ui.MESSAGE_ADD);
        ui.showMessage("  " + task.toString());
        ui.showMessage("Now you have " + tasks.getSize() + " task(s) in the list.");
    }
}