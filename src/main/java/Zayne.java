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

    public static void addTask(Task task) { //add a string to the array
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = task; //stores the n^th task input at the tasks[n-1]
            taskCount++;
            System.out.println("____________________________________________________________");
            System.out.println(" Got it. I've added this task:");
            System.out.println("  " + task); //the task here is actually task.toString()
            System.out.println(" Now you have " + taskCount + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("Task list is full! Cannot add more tasks.");
        }
    }

    public static void listTasks() { //list out the strings stored in the array
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + "." + tasks[i]); //print index, status and task name
        }
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
            } else if (command.equalsIgnoreCase("list")) {
                listTasks();
            } else if (command.startsWith("mark ")) {
                String[] parts = command.split(" "); //split the command input whenever there is a space. split components are stored into an array called parts.
                if (parts.length == 2) { //if the command is "mark 2", "mark" is stored at parts[0] and "2" is stored at parts[1]
                    int index = Integer.parseInt(parts[1]) - 1; //when we list out, the index starts at 1; but in the array the index starts at 0; so we must -1 here to match the array index

                    if (index >= 0 && index < taskCount) {
                        tasks[index].markDone(); //set the state to "done" when mark command is the input
                        System.out.println("____________________________________________________________");
                        System.out.println(" Nice! I've marked this task as done:");
                        System.out.println(" " + tasks[index]);
                        System.out.println("____________________________________________________________");
                    } else {
                        System.out.println("Invalid task number.");
                    }
                } else {
                    System.out.println("Usage: mark <task number>");
                }
            } else if (command.startsWith("unmark ")) {
                String[] parts = command.split(" ");
                if (parts.length == 2) {
                    int index = Integer.parseInt(parts[1]) - 1;

                    if (index >= 0 && index < taskCount) {
                        tasks[index].unmark();  //set the state back to not done when command is to unmark
                        System.out.println("____________________________________________________________");
                        System.out.println(" OK, I've marked this task as not done yet:");
                        System.out.println(" " + tasks[index]);
                        System.out.println("____________________________________________________________");
                    } else {
                        System.out.println("Invalid task number.");
                    }
                } else {
                        System.out.println("Usage: unmark <task number>");
                }
            } else if (command.startsWith("todo ")) {
                String description = command.substring(5).trim(); // extracts everything after "todo " (5 characters including space) as the task description
                if (!description.isEmpty()) {
                    addTask(new Todo(description));
                } else {
                    System.out.println("The description of a todo cannot be empty.");
                }
            } else if (command.startsWith("deadline ")) {
                int byIndex = command.indexOf("/by");
                if (byIndex != -1) {  // ensures that "/by" actually exists in the command
                    String description = command.substring(9, byIndex).trim(); // starts from index 9 to skip "deadline ", extracts the task description
                    String by = command.substring(byIndex + 3).trim(); // +3 skips past "/by", extracts the deadline date/time
                    if (!description.isEmpty() && !by.isEmpty()) {
                        addTask(new Deadline(description, by));
                    } else {
                        System.out.println("Invalid deadline format. Use: deadline <desc> /by <date>");
                    }
                } else {
                    System.out.println("Invalid deadline format. Use: deadline <desc> /by <date>");
                }
            } else if (command.startsWith("event ")) { //new Event input
                // expected format: event <desc> /from <start> /to <end>
                int fromIndex = command.indexOf("/from");
                int toIndex = command.indexOf("/to");
                if (fromIndex != -1 && toIndex != -1 && toIndex > fromIndex) {  // ensures both "/from" and "/to" exist, also ensures "/to" comes after "/from" in the command
                    String description = command.substring(6, fromIndex).trim();  // starts from index 6 to skip "event ", extracts the event description
                    String from = command.substring(fromIndex + 5, toIndex).trim(); // +5 skips past "/from", extracts the start time
                    String to = command.substring(toIndex + 3).trim(); // +3 skips past "/to", extracts the end time
                    if (!description.isEmpty() && !from.isEmpty() && !to.isEmpty()) {
                        addTask(new Event(description, from, to));
                    } else {
                        System.out.println("Invalid event format. Use: event <desc> /from <start> /to <end>");
                    }
                } else {
                    System.out.println("Invalid event format. Use: event <desc> /from <start> /to <end>");
                }
            } else if (!command.isEmpty()) { // addTask is put at the end of the while loop, because all other inputs before are special cases and should be evaluated first. if they are put after addTask, they will be treated as a new task and be added to the list.
                addTask(new Task(command));
            } else {
                System.out.println("Please enter a valid task.");
            }
        }
        sc.close();
    }
}




