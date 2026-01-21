public class Pulonia {
    private static final String COMMAND_REGEX = "^(mark|unmark) ([1-9][0-9]?|100)$";

    public static boolean isValidCommand(String input) {
        return input.matches(COMMAND_REGEX);
    }

    public static String getAction(String input) {
        return input.split(" ")[0];
    }

    public static int extractIndex(String input) {
        return Integer.parseInt(input.split(" ")[1]) - 1;
    }
}
