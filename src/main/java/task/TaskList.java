package task;

import java.util.ArrayList;
import java.util.List;

/**
 * This class consists of methods that operate and modify the user's {@link task.Task} list.
 */
public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds Task to user's Task list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Removes Task from user's Task list.
     *
     * @param taskIndex Index of the Task to be removed.
     * @return Removed Task.
     */
    public Task deleteTask(int taskIndex) {
        Task task = taskList.get(taskIndex);
        taskList.remove(task);
        return task;
    }


    /**
     * Finds relevant Tasks in user's Task list.
     *
     * @param searchTerm String text to search for.
     * @return List of relevant Tasks found. Returns an empty list if no relevant Tasks are found.
     */
    public List<Task> findTasks(String searchTerm) {
        List<Task> foundTaskList = new ArrayList<>();

        for (Task task : taskList) {
            if (task.toString().contains(searchTerm)) {
                foundTaskList.add(task);
            }
        }

        return foundTaskList;
    }


    /**
     * Marks Task in user's Task list as completed.
     *
     * @param taskIndex Index of the Task to be updated.
     * @return Updated Task.
     */
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
