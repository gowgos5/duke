package command;

import exception.DukeException;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;
import ui.Message;
import ui.Ui;

import java.time.LocalDateTime;

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

        String message = Message.MESSAGE_ADD + "\n" + "  " + task.toString() + "\n"
                + "Now you have " + tasks.getSize() + " task(s) in the list.";
        ui.showMessage(message);
    }
}