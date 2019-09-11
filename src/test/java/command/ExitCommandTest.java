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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExitCommandTest {
    private static final String PATH_FILE_STORAGE = System.getProperty("user.dir") + "/data/duke_test.txt";
    private static final PrintStream outStandard = System.out;
    private static final ByteArrayOutputStream outNew = new ByteArrayOutputStream();

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeAll
    static void setUp() throws IOException {
        Files.deleteIfExists(new File(PATH_FILE_STORAGE).toPath());
        System.setOut(new PrintStream(outNew));
    }

    @AfterAll
    static void tearDown() throws IOException {
        Files.deleteIfExists(new File(PATH_FILE_STORAGE).toPath());
        System.setOut(outStandard);
    }

    @BeforeEach
    void initialise() throws DukeException {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage(PATH_FILE_STORAGE);
    }

    @Test
    void execute_exit_success() throws DukeException {
        Command exitCommand = Parser.parse("bye");
        exitCommand.execute(taskList, ui, storage);
        assertEquals(outNew.toString().trim(), "Bye. Hope to see you again soon!");
    }
}