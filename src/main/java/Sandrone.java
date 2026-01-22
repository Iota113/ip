import java.util.Scanner;


public class Sandrone {
    public static void main(String[] args) {
        String greetings =
                "Look very closely, for standing before you is none other than Marionette.\n" +
                "Seventh of the Fatui Harbingers.";
        String farewell = "Ad astra abyssosque! Welcome to Nod-Krai, dominion of the Fatui.";

        printResponse(greetings);

        Scanner scn = new Scanner(System.in);
        TaskList list = new TaskList();
        String input = scn.nextLine();

        while (!input.equals("bye")) {
            try {
                System.out.println("____________________________________________________________");
                String message = list.act(input);
                if (!message.isEmpty()) System.out.println(message);
                System.out.println("____________________________________________________________");
            } catch (SandroneException e) {
                printResponse(e.getMessage());
            }

            input = scn.nextLine();
        }

        printResponse(farewell);
    }

    public static void printResponse(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }
}
