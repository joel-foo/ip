package minion.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import minion.commands.DeadlineCommand;
import minion.commands.EventCommand;
import minion.commands.MarkCommand;
import minion.commands.UnmarkCommand;
import minion.commands.DeleteCommand;
import minion.commands.ToDoCommand;
import minion.common.Messages;
import minion.data.exception.MinionException;
import minion.data.task.Deadline;
import minion.data.task.Event;
import minion.data.task.ToDo;

public class CommandParserTest {
    @Test
    public void parse_ToDo_success() throws MinionException {
        assertEquals(new ToDoCommand(new ToDo("buy book")), CommandParser.parse("todo buy book"));
    }

    @Test
    public void parse_Deadline_success() throws MinionException {
        assertEquals(new DeadlineCommand(new Deadline("return book", "Dec 3 2023 2:00 PM")), CommandParser.parse("deadline return book /by 3/12/2023 1400"));
    }

    @Test
    public void parse_Deadline_fail() throws MinionException {
        // invalid date format
        try {
            assertEquals(null, CommandParser.parse("deadline return book /by 3 Dec 2023 1400"));
            fail();
        } catch (MinionException e) {
            assertEquals(Messages.MESSAGE_FAIL_PARSE_DATE, e.getMessage());
        }
    }

    @Test
    public void parse_Event_success() throws MinionException {
        assertEquals(new EventCommand(new Event("pool party", "Dec 3 2023 2:00 PM", "Dec 4 2023 2:30 AM")), CommandParser.parse("event pool party /from 3/12/2023 1400 /to 4/12/2023 0230"));
    }

    @Test
    public void parse_Mark_success() throws MinionException {
        // 0 indexed
        assertEquals(new MarkCommand(0), CommandParser.parse("mark 1"));
    }

    @Test
    public void parse_Unmark_success() throws MinionException {
        // 0 indexed
        assertEquals(new UnmarkCommand(0), CommandParser.parse("unmark 1"));
    }

    @Test
    public void parse_Delete_success() throws MinionException {
        // 0 indexed
        assertEquals(new DeleteCommand(0), CommandParser.parse("delete 1"));
    }

    @Test
    public void parse_emptyCommand_exceptionThrown() throws MinionException {
        // empty string as command
        try {
            assertEquals(null, CommandParser.parse(" "));
            fail();
        } catch (MinionException e) {
            assertEquals(Messages.MESSAGE_MISSING_COMMAND, e.getMessage());
        }
    }

    @Test
    public void parse_invalidCommand_exceptionThrown() throws MinionException {
        // invalid command word
        try {
            assertEquals(null, CommandParser.parse("hello"));
            fail();
        } catch (MinionException e) {
            assertEquals(Messages.MESSAGE_NO_UNDERSTAND, e.getMessage());
        }
    }
}
