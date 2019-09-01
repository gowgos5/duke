package storage;

import exception.DukeException;
import parser.DateTimeParser;
import task.Deadline;
import task.Event;
import task.ITaskWithDateTime;
import task.Task;
import task.Todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String filePath;

    public Storage(final String filePath) throws DukeException {
        this.filePath = filePath;

        try {
            File file = new File(filePath);

            if (!file.exists()) {
                // File doesn't exist; create it.
                Files.createDirectories(Paths.get(filePath).getParent());
                file.createNewFile();
            }
        } catch (IOException e) {
            // Error creating file duke.txt
            throw new DukeException(DukeException.EXCEPTION_ERROR_CREATE_FILE);
        }
    }

    public List<Task> load() throws DukeException {
        try (FileReader fr = new FileReader(filePath);
             BufferedReader br = new BufferedReader(fr)) {
            List<Task> taskList = new ArrayList<>();
            String line;

            while ((line = br.readLine()) != null) {
                String[] taskAttributes = line.split("\\|");

                String taskType = taskAttributes[0].trim();
                boolean isTaskDone = taskAttributes[1].trim().equals("1");
                String taskDescription = taskAttributes[2].trim();
                LocalDateTime taskDateTime;
                try {
                    taskDateTime = DateTimeParser.formatStringToDateTime(taskAttributes[3].trim());
                } catch (ArrayIndexOutOfBoundsException e) {
                    // taskType.equals("T")
                    taskDateTime = null;
                } catch (DateTimeParseException e) {
                    throw new DukeException(DukeException.EXCEPTION_UNKNOWN_DATETIME_FORMAT);
                }

                switch (taskType) {
                case "T":
                    taskList.add(new Todo(taskDescription, isTaskDone));
                    break;
                case "D":
                    taskList.add(new Deadline(taskDescription, taskDateTime, isTaskDone));
                    break;
                case "E":
                    taskList.add(new Event(taskDescription, taskDateTime, isTaskDone));
                    break;
                }
            }

            return taskList;
        } catch (IOException e) {
            // Unable to read file duke.txt
            throw new DukeException(DukeException.EXCEPTION_ERROR_READ_FILE);
        }
    }

    public void save(List<Task> taskList) throws DukeException {
        try (FileWriter fw = new FileWriter(filePath, false);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            for (Task task : taskList) {
                String taskType = (task instanceof Todo) ? "T" : (task instanceof Deadline) ? "D" : "T";
                String taskStatus = task.isDone() ? "1" : "0";
                String taskDescription = task.getDescription();

                String taskAttributes = taskType + " | " + taskStatus + " | " + taskDescription;
                if (task instanceof ITaskWithDateTime) {
                    taskAttributes = taskAttributes.concat(" | " +
                            DateTimeParser.formatDateTimeToString(((ITaskWithDateTime) task).getDateTime()));
                }

                // Write line to file
                out.println(taskAttributes);
            }
        } catch (IOException e) {
            throw new DukeException(DukeException.EXCEPTION_ERROR_WRITE_FILE);
        }
    }
}