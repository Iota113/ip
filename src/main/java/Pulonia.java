import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Pulonia {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDate parseDate(String input) throws SandroneException {
        try {
            return LocalDate.parse(input, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new SandroneException("Use the format yyyy-MM-dd (e.g., 2026-01-28) for dates.");
        }
    }

    public static String formatDate(LocalDate date) {
        return date.format(FORMATTER);
    }

    public static Task parseNewTaskCommand(String fullCommand) throws SandroneException{
        if (fullCommand.startsWith("todo")) {
            return new Todo(fullCommand.replace("todo", "").trim());
        } else if (fullCommand.startsWith("deadline")) {
            if (!fullCommand.contains(" /by ")) {
                throw new SandroneException("Incomplete command! A by needs a ' /by ' component.");
            }

            String[] parts = fullCommand.split(" /by ");
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new SandroneException("The by cannot be empty.");
            }

            String desc = parts[0].substring(8).trim();
            if (desc.isEmpty()) {
                throw new SandroneException("The task cannot be empty.");
            }

            LocalDate by = parseDate(parts[1]);
            return new Deadline(desc, by);
        } else if (fullCommand.startsWith("event")) {
            if (!(fullCommand.contains(" /from ") && fullCommand.contains(" /to"))) {
                throw new SandroneException("Incomplete command! An Event needs a ' /from ' and ' /to ' component.");
            }

            // Remove "event"
            String command = fullCommand.substring(5).trim();
            String[] desc_part = command.split("/from");
            String desc = desc_part[0].trim();
            if (desc.isEmpty()) {
                throw new SandroneException("The task description is empty!");
            }

            String[] time_parts = desc_part[1].split("/to");
            if (time_parts.length < 2) {
                if (time_parts[0].trim().isEmpty()) throw new SandroneException("Both from and to fields are empty!");
                throw new SandroneException("The to field is empty!");
            }

            String fromString = time_parts[0].trim();
            if (fromString.isEmpty()) throw new SandroneException("The from field is empty!");

            String toString = time_parts[1].trim();

            LocalDate from = parseDate(fromString);
            LocalDate to = parseDate(toString);
            return new Event(desc, from, to);
        } else {
            return null;
        }
    }

    public static int extractIndex(String input) {
        return Integer.parseInt(input.split(" ")[1]) - 1;
    }
}
