import java.util.Scanner;


public class Sandrone {
    private SandroneUI ui;

    public Sandrone() {
        this.ui = new SandroneUI();
    }

    public static void main(String[] args) {
        new Sandrone().run();
    }

    public void run() {
        ui.showGreetings();

        Scanner scn = new Scanner(System.in);
        String input = scn.nextLine();

        TaskList list = new TaskList();

        while (!input.equals("bye")) {
            try {
                ui.printLine();
                String message = list.performCommand(input);
                if (!message.isEmpty()) System.out.println(message);
                ui.printLine();
            } catch (SandroneException e) {
                ui.printResponse(e.getMessage());
            }

            input = scn.nextLine();
        }
        ui.showFarewell();

    }

}
