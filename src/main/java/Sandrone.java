import java.util.Scanner;

public class Sandrone {
    public static void main(String[] args) {
        String greetings =
                " Hello! I'm Sandrone\n" +
                " What can I do for you?\n";
        String farewell = " Bye. Hope to see you again soon!\n";

        System.out.println(greetings);

        Scanner scn = new Scanner(System.in);
        String sentence = scn.nextLine();
        while (!sentence.equals("bye")) {
            System.out.println(sentence);
            sentence = scn.nextLine();
        }

        System.out.println(farewell);
    }
}
