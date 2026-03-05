package zayne;

import java.util.Scanner;

/**
 * Deals with interactions with the user, such as reading commands and displaying messages.
 */
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

    /**
     * Prints a stylized welcome message and the Zayne logo.
     */
    public void showWelcome() {
        printDivider();
        System.out.println("Hello! I'm Zayne\nWhat can I do for you?");
        printDivider();
    }

    /**
     * Shows a bye message when user exits the programme.
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Reads the next line of input from the user.
     * @return The trimmed string entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void printDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * Displays an error message to the user in a standardized format.
     * @param message The error description to be displayed.
     */
    public void showError(String message) {
        System.out.println(" OOPS!!! " + message);
    }
}