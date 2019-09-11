package command;

import exception.DukeException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddCommandTest {
    private static final String PATH_FILE_STORAGE = System.getProperty("user.dir") + "/data/duke_test.txt";

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeAll
    static void setUp() throws IOException {
        Files.deleteIfExists(new File(PATH_FILE_STORAGE).toPath());
    }

    @AfterAll
    static void tearDown() throws IOException {
        Files.deleteIfExists(new File(PATH_FILE_STORAGE).toPath());
    }

    @BeforeEach
    void initialise() throws DukeException {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage(PATH_FILE_STORAGE);
    }

    @Test
    void execute_todo_success() throws DukeException {
        Command addCommand = Parser.parse("todo eat dinner");
        addCommand.execute(taskList, ui, storage);
        assertEquals(taskList.getTasks().get(0).toString(), "[T][\u2718] eat dinner");
        assertEquals(storage.load().get(0).toString(), "[T][\u2718] eat dinner");
    }
}