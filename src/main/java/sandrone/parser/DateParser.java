package sandrone.parser;

import static sandrone.util.DateUtils.DATE_TIME_FORMATTER;
import static sandrone.util.DateUtils.DISPLAY_DATE_FORMATTER;
import static sandrone.util.DateUtils.DISPLAY_DATE_TIME_FORMATTER;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
     *
     */
    public static LocalDate parseDate(String input) throws SandroneException {
        if (!input.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new SandroneException(Messages.ERROR_INVALID_DATE_FORMAT);
        }

        try {
            return LocalDate.parse(input, DateUtils.DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new SandroneException(Messages.ERROR_DATE_DOES_NOT_EXIST);
        }
    }

    /**
     * Converts a {@code LocalDate} object into its standard string representation for display.
     *
     * @param date The date to format.
     * @return A string formatted as yyyy-MM-dd.
     */
    public static String formatDisplayDate(LocalDate date) {
        return date.format(DISPLAY_DATE_FORMATTER);
    }

    /**
     * Parses a raw string into a {@link LocalDateTime} object with strict validation.
     *
     * @param input The raw date string entered by the user.
     * @return A valid {@code LocalDateTime} representation of the input.
     * @throws SandroneException If the input format is incorrect or the date is invalid.
     *
     */
    public static LocalDateTime parseDateTime(String input) throws SandroneException {
        if (!input.matches("\\d{4}-\\d{2}-\\d{2} \\d{4}")) {
            throw new SandroneException(Messages.ERROR_INVALID_DATE_TIME_FORMAT);
        }

        try {
            return LocalDateTime.parse(input, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new SandroneException(Messages.ERROR_DATE_DOES_NOT_EXIST);
        }
    }

    /**
     * Converts a {@code LocalDateTime} object into its standard string representation for display.
     *
     * @param dateTime The date to format.
     * @return A string formatted as yyyy-MM-dd.
     */
    public static String formatDisplayDateTime(LocalDateTime dateTime) {
        return dateTime.format(DISPLAY_DATE_TIME_FORMATTER);
    }
}
