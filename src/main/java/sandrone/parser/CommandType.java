package sandrone.parser;

/**
 * Represents the various command types supported by the Sandrone chatbot.
 * Each type is associated with a specific keyword used for command identification
 * during the parsing process.
 *
 * @author Henry Tse
 * @version 0.1
 */
public enum CommandType {
    LIST("list"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    FIND("find"),
    BYE("bye"),
    RECUR("recur"),
    DRECUR("drecur"),
    SYNC("sync"),
    HELP("help"),
    DEFAULT("");

    private final String keyword;

    CommandType(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public static CommandType getCommandType(String commandWord) {
        if (commandWord == null || commandWord.isBlank()) {
            return DEFAULT;
        }

        for (CommandType type : values()) {
            // Match the user's first word against the stored keyword
            if (type.keyword.equalsIgnoreCase(commandWord.trim())) {
                return type;
            }
        }
        return DEFAULT;
    }
}
