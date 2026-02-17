package sandrone.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import sandrone.exception.SandroneException;
import sandrone.util.DateUtils;
import sandrone.util.Messages;

/**
 * Handles the interpretation and validation of date-related user input.
 * This class bridges raw string input from the user to the {@link LocalDate}
 * domain objects used by the application logic.
 */
public class DateParser {
    /**
     * Parses a raw string into a {@link LocalDate} object with strict validation.
     *
     * <p>The method first performs a structural check via regex before attempting
     * logical parsing. This ensures that the user receives specific error messages
     * for format errors versus non-existent dates.</p>
     *
     * @param input The raw date string entered by the user.
     * @return A valid {@code LocalDate} representation of the input.
     * @throws SandroneException If the input format is incorrect or the date is invalid.
     * @see DateUtils#FORMATTER
     */
    public static LocalDate parse(String input) throws SandroneException {
        if (!input.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new SandroneException(Messages.ERROR_INVALID_DATE_FORMAT);
        }

        try {
            return LocalDate.parse(input, DateUtils.FORMATTER);
        } catch (DateTimeParseException e) {
            throw new SandroneException(Messages.ERROR_DATE_DOES_NOT_EXIST);
        }
    }
}
