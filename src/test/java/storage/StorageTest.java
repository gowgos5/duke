package storage;

import exception.DukeException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import parser.DateTimeParser;
import task.Deadline;
import task.Todo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StorageTest {
    private static final String PATH_FILE_STORAGE = System.getProperty("user.dir") + "/data/duke_test.txt";
    private static final String PATH_FILE_STORAGE_EMPTY = System.getProperty("user.dir") + "/data/duke_test_empty.txt";

    private Storage storage;
    private Storage storageEmpty;
    private LocalDateTime dateTime = LocalDateTime.now();

    private static void deleteFiles() throws IOException {
        Files.deleteIfExists(new File(PATH_FILE_STORAGE).toPath());
        Files.deleteIfExists(new File(PATH_FILE_STORAGE_EMPTY).toPath());
    }

    @BeforeAll
    static void setUp() throws IOException {
        deleteFiles();
    }

    @AfterAll
    static void tearDown() throws IOException {
        deleteFiles();
    }

    @Test
    @Order(1)
    void save_nonEmptyTaskList_success() throws DukeException {
        storage = new Storage(PATH_FILE_STORAGE);
        storage.save(Arrays.asList(new Todo("Eat rice"),
                new Deadline("Lab assignment 3", dateTime, true)));
    }

    @Test
    @Order(2)
    void load_nonEmptyTaskList_success() throws DukeException {
        storage = new Storage(PATH_FILE_STORAGE);
        assertEquals(storage.load().size(), 2);
        assertEquals(storage.load().get(0).toString(), "[T][\u2718] Eat rice");
        assertEquals(storage.load().get(1).toString(), "[D][\u2713] Lab assignment 3 (by: "
                + DateTimeParser.formatDateTimeToString(dateTime) + ")");
    }

    @Test
    @Order(3)
    void save_emptyTaskList_success() throws DukeException {
        storageEmpty = new Storage(PATH_FILE_STORAGE_EMPTY);
        storageEmpty.save(new ArrayList<>());
    }

    @Test
    @Order(4)
    void load_emptyTaskList_success() throws DukeException {
        storageEmpty = new Storage(PATH_FILE_STORAGE_EMPTY);
        assertTrue(storageEmpty.load().isEmpty());
    }
}