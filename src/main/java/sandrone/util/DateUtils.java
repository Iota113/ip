package sandrone.util;

import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * Provides central configuration for date handling across the application.
 * This utility class acts as a single source of truth for date patterns and
 * formatting rules to ensure consistency between parsing and storage.
 */
public class DateUtils {
    /**
     * The standard ISO-based date pattern used by the application (yyyy-MM-dd).
     */
    public static final String DATE_PATTERN = "uuuu-MM-dd";

    /**
     * The strict formatter used for all date operations.
     * Uses {@link ResolverStyle#STRICT} to ensure invalid dates (e.g., February 30th)
     * are rejected during the parsing process.
     */
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter
            .ofPattern(DATE_PATTERN)
            .withResolverStyle(ResolverStyle.STRICT);
}
