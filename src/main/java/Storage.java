import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final String FOLDER_LIST_TASKS = System.getProperty("user.dir") + "/data";
    private static final String FILE_LIST_TASKS = FOLDER_LIST_TASKS + "/duke.txt";

    private List<Task> tasks = new ArrayList<>();

    public Storage() throws DukeException {
        try {
            File file = new File(FILE_LIST_TASKS);

            if (file.exists()) {
                // File exists; retrieve tasks list from it.
                readFile(file);
            } else {
                // File doesn't exist; create it.
                File folder = new File(FOLDER_LIST_TASKS);
                if (!folder.exists()) {
                    folder.mkdir();
                }
                file.createNewFile();
            }
        } catch (IOException e) {
            // Exception: Unable to create file duke.txt (from file.createNewFile())
            throw new DukeException(DukeException.EXCEPTION_ERROR_CREATE_FILE);
        } catch (DukeException e) {
            // Exception generated from readFile(file);
            throw new DukeException(e.getMessage());
        }
    }

    private void readFile(File file) throws DukeException {
        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] taskAttributes = line.split("\\|");
                String taskType = taskAttributes[0].trim();
                boolean isTaskDone = taskAttributes[1].trim().equals("1");
                String taskDescription = taskAttributes[2].trim();

                Task task = null;
                switch (taskType) {
                case "T":
                    task = new Todo(taskDescription);
                    break;
                case "D":
                    task = new Deadline(taskDescription, DateTimeParser.formatStringToDateTime(taskAttributes[3].trim()));
                    break;
                case "E":
                    task = new Event(taskDescription, DateTimeParser.formatStringToDateTime(taskAttributes[3].trim()));
                    break;
                }

                if (task != null) {
                    if (isTaskDone) task.markAsDone();
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            throw new DukeException(DukeException.EXCEPTION_ERROR_READ_FILE);
        }
    }

    public void updateFile(List<Task> tasks) throws DukeException {
        this.tasks = tasks;

        try (FileWriter fw = new FileWriter(FILE_LIST_TASKS, false);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            for (Task task : tasks) {
                String taskType = null;
                if (task instanceof Todo) {
                    taskType = "T";
                } else if (task instanceof Deadline) {
                    taskType = "D";
                } else if (task instanceof Event) {
                    taskType = "E";
                }

                if (taskType != null) {
                    String taskAttributes = taskType + " | " + (task.isDone ? "1" : "0") + " | " + task.getDescription();

                    if (task instanceof ITaskWithDateTime) {
                        taskAttributes = taskAttributes.concat(" | " +
                                DateTimeParser.formatDateTimeToString(((ITaskWithDateTime) task).getDateTime()));
                    }

                    // Write line to file
                    out.println(taskAttributes);
                }
            }
        } catch (IOException e) {
            throw new DukeException(DukeException.EXCEPTION_ERROR_WRITE_FILE);
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
