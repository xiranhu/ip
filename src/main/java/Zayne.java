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

    private static final int MAX_TASKS = 100; // maximum tasks by default
    private static Task[] tasks = new Task[MAX_TASKS]; // array to store all task inputs
    private static int taskCount = 0; // counter

    public static void addTask(Task task) { //add a task to the array
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = task; // store the n^th task input at tasks[n]
            taskCount++;
            System.out.println("____________________________________________________________");
            System.out.println(" added: " + task.getDescription());
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("Task list is full! Cannot add more tasks.");
        }
    }

    public static void listTasks() { //list out all tasks
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + ". " + tasks[i].getDescription()); //print index and task name
        }
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        logo();
        greet();

        Scanner sc = new Scanner(System.in); // for user input
        String command = " "; //stores user input String[] parts = command.split(" ");
        while (true) { //main loop for commands
            System.out.println("What would you like to do today?");
            command = sc.nextLine().trim();

            if (command.equalsIgnoreCase("bye")) {
                bye();
                break;
            } else if (command.equalsIgnoreCase("list")) {
                listTasks();
            } else if (!command.isEmpty()) { //any other input is treated as a new task
                addTask(new Task(command));
            } else {
                System.out.println("Please enter a valid task.");
            }
        }
        sc.close();
    }
}




