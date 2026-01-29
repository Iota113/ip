package sandrone.util;

import org.junit.jupiter.api.Test;
import sandrone.command.AddCommand;
import sandrone.command.Command;
import sandrone.command.PrintCommand;
import sandrone.exception.SandroneException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PuloniaTest {
    @Test
    public void parseDate_validDate_returnsCorrectLocalDate() throws SandroneException {
        LocalDate expected = LocalDate.of(2026, 1, 28);
        assertEquals(expected, Pulonia.parseDate("2026-01-28"));
    }

    @Test
    public void parseDate_invalidFormat_throwsSandroneException() {
        assertThrows(SandroneException.class, () -> {
            Pulonia.parseDate("28-01-2026");
        });
    }

    @Test
    public void parseCommand_todoInput_returnsAddCommand() throws SandroneException {
        Command result = Pulonia.parseCommand("todo read book");
        assertTrue(result instanceof AddCommand);
    }

    @Test
    public void parseCommand_listInput_returnsPrintCommand() throws SandroneException {
        Command result = Pulonia.parseCommand("list");
        assertTrue(result instanceof PrintCommand);
    }

    @Test
    public void parseCommand_deadlineInputMissingBy_throwsSandroneException() {
        assertThrows(SandroneException.class, () -> {
            Pulonia.parseCommand("deadline MA2108 by 2026-01-30");
        });
    }

    @Test
    public void extractIndex_inputTwo_returnsIndexOne() {
        // Input "mark 2" should return index 1 (0-index used)
        assertEquals(1, Pulonia.extractIndex("mark 2"));
    }
}
