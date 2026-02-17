package sandrone.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import sandrone.command.AddCommand;
import sandrone.command.Command;
import sandrone.command.PrintCommand;
import sandrone.exception.SandroneException;

public class PuloniaTest {
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
}
