package task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task deleteTask(int taskIndex) {
        Task task = taskList.get(taskIndex);
        taskList.remove(task);
        return task;
    }

    public List<Task> findTasks(String searchTerm) {
        List<Task> foundTaskList = new ArrayList<>();

        for (Task task : taskList) {
            if (task.toString().contains(searchTerm)) {
                foundTaskList.add(task);
            }
        }

        return foundTaskList;
    }

    public Task markTaskAsDone(int taskIndex) {
        Task task = taskList.get(taskIndex);
        task.markAsDone();
        return task;
    }

    public List<Task> getTasks() {
        return taskList;
    }

    public int getSize() {
        return taskList.size();
    }
}
