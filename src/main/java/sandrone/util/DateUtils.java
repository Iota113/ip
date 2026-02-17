package sandrone.util;

import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * Provides central configuration for date (and time) handling across the application.
 * This utility class acts as a single source of truth for date patterns and
 * formatting rules to ensure consistency between parsing and storage.
 */
public class DateUtils {
    private static final String DATE_PATTERN = "uuuu-MM-dd";
    private static final String DATE_TIME_PATTERN = "uuuu-MM-dd HHmm";
    private static final String DISPLAY_DATE_PATTERN = "uuuu MMM dd";
    private static final String DISPLAY_DATE_TIME_PATTERN = "uuuu MMM dd, h:mm a";

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern(DATE_PATTERN)
            .withResolverStyle(ResolverStyle.STRICT);

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern(DATE_TIME_PATTERN)
            .withResolverStyle(ResolverStyle.STRICT);

    public static final DateTimeFormatter DISPLAY_DATE_FORMATTER = DateTimeFormatter
            .ofPattern(DISPLAY_DATE_PATTERN)
            .withResolverStyle(ResolverStyle.STRICT);

    public static final DateTimeFormatter DISPLAY_DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern(DISPLAY_DATE_TIME_PATTERN)
            .withResolverStyle(ResolverStyle.STRICT);
}
