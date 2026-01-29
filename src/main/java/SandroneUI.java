public class SandroneUI {
    private static final String greetings =
            "Look very closely, for standing before you is none other than Marionette.\n" +
                    "Seventh of the Fatui Harbingers.";
    private static final String farewell = "Ad astra abyssosque! Welcome to Nod-Krai, dominion of the Fatui.";

    public void showGreetings () {
        printResponse(greetings);
    }

    public void showFarewell() {
        printResponse(farewell);
    }

    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    public void printResponse(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }

}
