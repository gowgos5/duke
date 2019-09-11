package task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskListTest {
    private TaskList taskList;
    private TaskList taskListEmpty;

    @BeforeEach
    void initialise() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Dance"));
        tasks.add(new Event("Coding competition", LocalDateTime.now()));

        taskList = new TaskList(tasks);
        taskListEmpty = new TaskList();
    }

    @Test
    void addTask_nonEmptyList_success() {
        Task deadline = new Deadline("Coding assignment", LocalDateTime.now());

        taskList.addTask(deadline);
        assertEquals(taskList.getSize(), 3);
        assertEquals(deadline, taskList.getTasks().get(taskList.getSize() - 1));
    }

    @Test
    void addTask_emptyList_success() {
        Task deadline = new Deadline("Coding assignment", LocalDateTime.now());

        taskListEmpty.addTask(deadline);
        assertEquals(taskListEmpty.getSize(), 1);
        assertEquals(deadline, taskListEmpty.getTasks().get(0));
    }

    @Test
    void deleteTask_validIndexOnNonEmptyList_success() {
        taskList.deleteTask(0);
        assertEquals(taskList.getSize(), 1);
    }

    @Test
    void deleteTask_invalidIndexOnNonEmptyList_exceptionThrown() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.deleteTask(2));
    }

    @Test
    void deleteTask_emptyList_exceptionThrown() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskListEmpty.deleteTask(0));
    }

    @Test
    void findTask_nonEmptyList_success() {
        assertEquals(taskList.findTasks("Coding").size(), 1);
        assertEquals(taskList.findTasks("Eat").size(), 0);
    }

    @Test
    void findTask_emptyList_success() {
        assertEquals(taskListEmpty.findTasks("Dance").size(), 0);
    }

    @Test
    void markTaskAsDone_validIndexOnNonEmptyList_success() {
        assertFalse(taskList.getTasks().get(0).isDone());
        taskList.markTaskAsDone(0);
        assertTrue(taskList.getTasks().get(0).isDone());
    }

    @Test
    void markTaskAsDone_invalidIndexOnNonEmptyList_exceptionThrown() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.markTaskAsDone(4));
    }

    @Test
    void markTaskAsDone_emptyList_exceptionThrown() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskListEmpty.markTaskAsDone(0));
    }
}
