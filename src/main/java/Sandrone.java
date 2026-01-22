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
                if (input.equals("list")) {
                    list.printList();
                } else if (Pulonia.isMarkCommand(input)) {
                    printResponse(
                            list.setTaskStatus(Pulonia.extractIndex(input),
                                    Pulonia.getAction(input).equals("mark"))
                    );
                } else {
                    printResponse(list.addTask(input));
                }
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
