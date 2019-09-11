package parser;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.DoneCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Class responsible for parsing the user messages received by {@link duke.Duke Duke}.
 */
public class Parser {
    /**
     * Obtains an instance of {@link command.Command} from the user message received.
     *
     * @param userMessage String message inputted by the user.
     * @return Command to be executed by Duke.
     * @throws DukeException If the format of the user message received is incorrect.
     */
    public static Command parse(String userMessage) throws DukeException {
        String command = userMessage.split("\\s+")[0];

        switch (command) {
        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "event":
            String taskAttributes = userMessage.split(command, 2)[1].trim();
            if (taskAttributes.isEmpty()) {
                throw new DukeException(DukeException.EXCEPTION_EMPTY_DESCRIPTION);
            }

            if (command.equals("todo")) {
                // description = taskAttributes
                return new AddCommand(command, taskAttributes);
            } else {
                String key;
                if (command.equals("deadline")) {
                    key = "/by";
                } else {
                    // command.equals("event")
                    key = "/at";
                }

                String taskDescription = taskAttributes.split(key, 2)[0].trim();
                if (taskDescription.isEmpty()) {
                    throw new DukeException(DukeException.EXCEPTION_EMPTY_DESCRIPTION);
                }

                LocalDateTime taskDateTime;
                try {
                    String dateTime = taskAttributes.split(key, 2)[1].trim();
                    taskDateTime = DateTimeParser.formatStringToDateTime(dateTime);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException(DukeException.EXCEPTION_EMPTY_DATETIME);
                } catch (DateTimeParseException e) {
                    throw new DukeException(DukeException.EXCEPTION_UNKNOWN_DATETIME_FORMAT);
                }

                return new AddCommand(command, taskDescription, taskDateTime);
            }
        case "find":
            String searchTerm = userMessage.split(command, 2)[1].trim();
            if (searchTerm.isEmpty()) {
                throw new DukeException(DukeException.EXCEPTION_EMPTY_DESCRIPTION);
            }

            return new FindCommand(searchTerm);
        case "delete":
            // Fallthrough
        case "done":
            String description = userMessage.split(command, 2)[1].trim();
            if (description.isEmpty()) {
                throw new DukeException(DukeException.EXCEPTION_EMPTY_DESCRIPTION);
            }

            int index;
            try {
                index = Integer.parseInt(description) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException(DukeException.EXCEPTION_NON_INTEGER_DESCRIPTION);
            }

            if (command.equals("done")) {
                return new DoneCommand(index);
            } else {
                // command.equals("done")
                return new DeleteCommand(index);
            }
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        default:
            // Empty string or unknown command.
            throw new DukeException(DukeException.EXCEPTION_UNKNOWN_COMMAND);
        }
    }
}