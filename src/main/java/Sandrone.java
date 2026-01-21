import java.util.Scanner;


public class Sandrone {
    public static void main(String[] args) {
        String greetings =
                "Look very closely, for standing before you is none other than Marionette" +
                " Seventh of the Fatui Harbingers.\n";
        String farewell = "Ad astra abyssosque! Welcome to Nod-Krai, dominion of the Fatui. \n";

        System.out.println(greetings);

        Scanner scn = new Scanner(System.in);
        TaskList list = new TaskList();
        String input = scn.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                list.printList();
            } else if (Pulonia.isValidCommand(input)) {
                list.setTaskStatus(Pulonia.extractIndex(input), Pulonia.getAction(input).equals("mark"));
            } else {
                list.addTask(input);
            }

            input = scn.nextLine();
        }

        System.out.println(farewell);
    }
}
