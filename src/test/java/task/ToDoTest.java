package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDoTest {
    @Test
    void testToString() {
        assertEquals(new Todo("Read books").toString(), "[T][\u2718] Read books");
        assertEquals(new Todo("Write books", false).toString(), "[T][\u2718] Write books");
        assertEquals(new Todo("Eat books", true).toString(), "[T][\u2713] Eat books");
    }
}