import java.util.Scanner;

public class Zayne {
    public static void logo() { //method for printing logo
        String logo = "______   ______  __   __  ___    __ _______ \n"
        + "    //  ||    || ||   ||  || \\   || || \n"
        + "   //   ||____|| ||___||  ||  \\  || ||_____ \n"
        + "  //    ||    ||      ||  ||   \\ || || \n"
        + "_//___  ||    ||  ____||  ||    \\_| ||_____ \n";
        System.out.println("Hello from\n" + logo);
    }

    public static void greet() { //method for greeting user
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Zayne");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static void bye() { //method for saying bye to user
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        logo();
        greet();

        Scanner sc = new Scanner(System.in); // for user input
        String command = " "; //stores user input String[] parts = command.split(" ");
        while (true) {
            command = sc.nextLine().trim();

            if (command.equalsIgnoreCase("bye")) {
                bye();
                break;
            } else {
                System.out.println("____________________________________________________________");
                System.out.println(" " + command);
                System.out.println("____________________________________________________________");
            }
        }
        sc.close();
    }
}




