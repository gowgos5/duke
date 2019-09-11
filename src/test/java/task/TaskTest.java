package task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskTest {
    private Task taskOne;
    private Task taskTwo;
    private Task taskThree;

    @BeforeEach
    void initialise() {
        taskOne = new Task("Watch movie");
        taskTwo = new Task("Review movie", false);
        taskThree = new Task("Download movie", true);
    }

    @Test
    void testMarkAsDone() {
        taskOne.markAsDone();
        assertTrue(taskOne.isDone());
        taskTwo.markAsDone();
        assertTrue(taskTwo.isDone());
        taskThree.markAsDone();
        assertTrue(taskThree.isDone());
    }

    @Test
    void testGetStatusIcon() {
        assertEquals(taskOne.getStatusIcon(), "\u2718");    // X
        assertEquals(taskTwo.getStatusIcon(), "\u2718");    // X
        assertEquals(taskThree.getStatusIcon(), "\u2713");  // tick
    }

    @Test
    void testGetDescription() {
        assertEquals(taskOne.getDescription(), "Watch movie");
        assertEquals(taskTwo.getDescription(), "Review movie");
        assertEquals(taskThree.getDescription(), "Download movie");
    }

    @Test
    void testToString() {
        assertEquals(taskOne.toString(), "[\u2718] Watch movie");
        assertEquals(taskTwo.toString(), "[\u2718] Review movie");
        assertEquals(taskThree.toString(), "[\u2713] Download movie");
    }
}
