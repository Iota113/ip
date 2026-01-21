import java.util.Scanner;

public class Sandrone {
    public static void main(String[] args) {
        String greetings =
                "Look very closely, for standing before you is none other than Marionette" +
                " Seventh of the Fatui Harbingers.\n";
        String farewell = "Ad astra abyssosque! Welcome to Nod-Krai, dominion of the Fatui. \n";

        System.out.println(greetings);

        Scanner scn = new Scanner(System.in);
        String[] list = new String[100];
        int index = 0;
        String task = scn.nextLine();
        while (!task.equals("bye")) {
            if (task.equals("list")) {
                for (int i = 0; i < index; i++) {
                    System.out.println(i + ". " + list[i]);
                }
            } else {
                list[index++] = task;
                System.out.println("added: " + task);
            }

            task = scn.nextLine();
        }

        System.out.println(farewell);
    }
}
