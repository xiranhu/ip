public class Zayne {
    public static void logo() {
        String logo = "______   ______  __   __  ___    __ _______ \n"
        + "    //  ||    || ||   ||  || \\   || || \n"
        + "   //   ||____|| ||___||  ||  \\  || ||_____ \n"
        + "  //    ||    ||      ||  ||   \\ || || \n"
        + "_//___  ||    ||  ____||  ||    \\_| ||_____ \n";
        System.out.println("Hello from\n" + logo);
    }
    public static void greet() {
        System.out.println("-----------------------");
        System.out.println("Hello! I'm Zayne");
        System.out.println("What can I do for you?");
        System.out.println("-----------------------");
    }
    public static void bye() {
        System.out.println("-----------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("-----------------------");
    }
    public static void main(String[] args) {
        logo();
        greet();
        bye();
    }
}




