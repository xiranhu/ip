import java.util.Scanner;

public class Zayne {
    private static void logo() { //method for printing logo
        String logo = "______   ______  __   __  ___    __ _______ \n"
        + "    //  ||    || ||   ||  || \\   || || \n"
        + "   //   ||____|| ||___||  ||  \\  || ||_____ \n"
        + "  //    ||    ||      ||  ||   \\ || || \n"
        + "_//___  ||    ||  ____||  ||    \\_| ||_____ \n";
        System.out.println("Hello from\n" + logo);
    }

    private static void greet() { //method for greeting user
        printDivider();
        System.out.println("Hello! I'm Zayne");
        System.out.println("What can I do for you?");
        printDivider();
    }

    private static void bye() { //method for saying bye to user
        printDivider();
        System.out.println("Bye. Hope to see you again soon!");
        printDivider();
    }

    private static final String DIVIDER = "____________________________________________________________";
    private static final int MAX_TASKS = 100; // maximum tasks by default
    private static Task[] tasks = new Task[MAX_TASKS]; // array to store all task inputs
    private static int taskCount = 0; // counter

    private static void addTask(Task task) { //add a task to the array
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = task; // store the n^th task input at tasks[n]
            taskCount++;
            printDivider();
            System.out.println(" Got it. I've added this task:");
            System.out.println("  " + task); //the task here is actually task.toString()
            System.out.println(" Now you have " + taskCount + " tasks in the list.");
            printDivider();
        } else {
            System.out.println("Task list is full! Cannot add more tasks.");
        }
    }

    private static void listTasks() { //list out all tasks
        printDivider();
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + "." + tasks[i]); //print index, status and task name
        }
        printDivider();
    }

    private static void handleMarkCommand(String command) {
        boolean isMark = command.startsWith("mark ");
        boolean isUnmark = command.startsWith("unmark ");

        String[] parts = command.split(" "); //split the command input whenever there is a space. split components are stored into an array called parts.

        if (parts.length != 2) {  //handle invalid command
            System.out.println("Usage: mark/unmark <task number>");
            return;
        }

        int index = Integer.parseInt(parts[1]) - 1; //when we list out, the index starts at 1; but in the array the index starts at 0; so we must -1 here to match the array index

        if (!isValidIndex(index)) {
            System.out.println("Invalid task number.");
            return;
        }

        if (isMark) {
            tasks[index].markDone();
            printDivider();
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println(" " + tasks[index]);
            printDivider();
            return;
        }

        if (isUnmark) {
            tasks[index].unmark();
            printDivider();
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println(" " + tasks[index]);
            printDivider();
        }
    }

    private static boolean isValidIndex(int index) {
        return index >= 0 && index < taskCount;
    }

    private static void printDivider() {
        System.out.println(DIVIDER);
    }

    private static void handleTodo(String command) {
        String taskDescription = command.substring(5).trim(); // extracts everything after "todo " (5 characters including space) as the task taskDescription

        if (taskDescription.isEmpty()) {
            System.out.println("The task description of a todo cannot be empty.");
            return;
        }

        addTask(new Todo(taskDescription));
    }

    private static void handleDeadline(String command) {
        int indexOfBy = command.indexOf("/by");

        if (indexOfBy== -1) {
            System.out.println("Invalid deadline format. Use: deadline <desc> /by <date>");
            return;
        }

        String taskDescription = command.substring(9, indexOfBy).trim(); // starts from index 9 to skip "deadline ", extracts the task description
        String by = command.substring(indexOfBy + 3).trim(); // +3 skips past "/by", extracts the deadline date/time

        if (taskDescription.isEmpty() || by.isEmpty()) {
            System.out.println("Invalid deadline format. Use: deadline <desc> /by <date>");
            return;
        }

        addTask(new Deadline(taskDescription, by));
    }

    private static void handleEvent(String command) {
        int indexOfFrom = command.indexOf("/from");
        int indexOfTo = command.indexOf("/to");

        if (indexOfFrom == -1 || indexOfTo == -1 || indexOfTo <= indexOfFrom) {
            System.out.println("Invalid event format. Use: event <desc> /from <start> /to <end>");
            return;
        }

        String taskDescription = command.substring(6, indexOfFrom).trim();  // starts from index 6 to skip "event ", extracts the event description
        String from = command.substring(indexOfFrom + 5, indexOfTo).trim(); // +5 skips past "/from", extracts the start time
        String to = command.substring(indexOfTo + 3).trim();  // +3 skips past "/to", extracts the end time

        if (taskDescription.isEmpty() || from.isEmpty() || to.isEmpty()) {
            System.out.println("Invalid event format. Use: event <desc> /from <start> /to <end>");
            return;
        }

        addTask(new Event(taskDescription, from, to));
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
            }

            if (command.equalsIgnoreCase("list")) {
                listTasks();
                continue;
            }

            if (command.startsWith("mark ") || command.startsWith("unmark ")) {
                handleMarkCommand(command);
                continue;
            }

            if (command.startsWith("todo ")) {
                handleTodo(command);
                continue;
            }

            if (command.startsWith("deadline ")) {
                handleDeadline(command);
                continue;
            }

            if (command.startsWith("event ")) {
                handleEvent(command);
                continue;
            }

            System.out.println("Please enter a valid task.");
        }
        sc.close();
    }
}




