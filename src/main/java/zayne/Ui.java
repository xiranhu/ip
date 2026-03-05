package zayne;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;
    private static final String DIVIDER = "____________________________________________________________";

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showLogo() {
        String logo = "______   ______  __   __  ___    __ _______ \n"
                + "    //  ||    || ||   ||  || \\   || || \n"
                + "   //   ||____|| ||___||  ||  \\  || ||_____ \n"
                + "  //    ||    ||      ||  ||   \\ || || \n"
                + "_//___  ||    ||  ____||  ||    \\_| ||_____ \n";
        System.out.println("Hello from\n" + logo);
    }

    public void showWelcome() {
        printDivider();
        System.out.println("Hello! I'm Zayne\nWhat can I do for you?");
        printDivider();
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void printDivider() {
        System.out.println(DIVIDER);
    }

    public void showError(String message) {
        System.out.println(" OOPS!!! " + message);
    }
}