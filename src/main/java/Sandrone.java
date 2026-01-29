import java.util.Scanner;


public class Sandrone {
    private SandroneUI ui;
    private Storage listData;

    public Sandrone() {
        this.ui = new SandroneUI();
        this.listData = new Storage("./data/sandrone_task_list.txt");
    }

    public static void main(String[] args) {
        new Sandrone().run();
    }

    public void run() {
        ui.showGreetings();

        Scanner scn = new Scanner(System.in);
        String input = scn.nextLine();

        TaskList list = new TaskList(this.listData);

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
