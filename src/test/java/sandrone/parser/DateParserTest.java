package sandrone.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import sandrone.exception.SandroneException;

public class DateParserTest {

    @Test
    public void parseDate_validDate_returnsCorrectLocalDate() throws SandroneException {
        LocalDate expected = LocalDate.of(2026, 1, 28);
        assertEquals(expected, DateParser.parse("2026-01-28"));
    }

    @Test
    public void parseDate_invalidFormat_throwsSandroneException() {
        assertThrows(SandroneException.class, () -> {
            DateParser.parse("28-01-2026");
        });
    }
}
