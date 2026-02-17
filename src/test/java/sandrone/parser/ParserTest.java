package sandrone.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import sandrone.exception.SandroneException;

public class ParserTest {
    @Test
    public void extractIndex_inputTwo_returnsIndexOne() throws SandroneException {
        // Input "mark 2" should return index 1 (0-index used)
        assertEquals(1, Parser.parseIndex("2"));
    }
}
