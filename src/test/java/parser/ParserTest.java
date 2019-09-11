package parser;

import command.AddCommand;
import command.DeleteCommand;
import command.DoneCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {
    @Test
    void parse_validCommands_success() throws DukeException {
        /* VALID COMMANDS */
        assertTrue(Parser.parse("todo borrow books") instanceof AddCommand);
        assertTrue(Parser.parse("deadline assignment 1 /by 01/09/2019 23:59") instanceof AddCommand);
        assertTrue(Parser.parse("event Evan's birthday party /at 05/09/2019 15:00") instanceof AddCommand);
        assertTrue(Parser.parse("done 1") instanceof DoneCommand);
        assertTrue(Parser.parse("delete 3") instanceof DeleteCommand);
        assertTrue(Parser.parse("list") instanceof ListCommand);
        assertTrue(Parser.parse("find assignment") instanceof FindCommand);
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
    }

    @Test
    void parse_invalidCommands_exceptionThrown() {
        /* INVALID COMMANDS */
        assertThrows(DukeException.class, () -> Parser.parse("blah"));
        assertThrows(DukeException.class, () -> Parser.parse("todo"));
        assertThrows(DukeException.class, () -> Parser.parse("deadline assignment 2"));
        assertThrows(DukeException.class, () -> Parser.parse("deadline assignment 2 /by today"));
        assertThrows(DukeException.class, () -> Parser.parse("event shower /by"));
        assertThrows(DukeException.class, () -> Parser.parse("event shower /by 05/09/2019 23:59"));
        assertThrows(DukeException.class, () -> Parser.parse("deadline homework 1 /at 05/09/2019 23:59"));
        assertThrows(DukeException.class, () -> Parser.parse("find"));
        assertThrows(DukeException.class, () -> Parser.parse("done x"));
        assertThrows(DukeException.class, () -> Parser.parse("done"));
        assertThrows(DukeException.class, () -> Parser.parse("delete this"));
        assertThrows(DukeException.class, () -> Parser.parse("delete"));
    }
}