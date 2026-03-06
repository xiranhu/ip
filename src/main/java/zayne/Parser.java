package zayne;

import zayne.exceptions.InputException;
import zayne.tasks.Deadline;
import zayne.tasks.Event;
import zayne.tasks.Todo;

/**
 * Deals with making sense of the user command.
 */
public class Parser {
    /**
     * Identifies the command type and calls the appropriate handler method.
     */
    public static void parse(String fullCommand, TaskList tasks, Ui ui, Storage storage) throws InputException {
        // 1. Split the input into "command" and "everything else"
        String[] parts = fullCommand.trim().split("\\s+", 2);
        String commandWord = parts[0].toLowerCase(); // e.g., "todo", "findsw"
        String arguments = parts.length > 1 ? parts[1] : ""; // The rest of the string

        // 2. Exact match check using switch
        switch (commandWord) {
        case "list":
            if (!arguments.isEmpty()) {
                throw new InputException("The 'list' command does not take arguments.");
            }
            tasks.listTasks();
            break;

        case "mark":
        case "unmark":
            handleMarkCommand(commandWord, arguments, tasks);
            break;

        case "todo":
            handleTodo(arguments, tasks);
            break;

        case "deadline":
            handleDeadline(arguments, tasks);
            break;

        case "event":
            handleEvent(arguments, tasks);
            break;

        case "delete":
            handleDelete(arguments, tasks);
            break;

        case "find":
            handleFind(arguments, tasks);
            break;

        default:
            // This now correctly catches "todo1", "findsw", etc.
            throw new InputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        // Save to file after every successful command
        storage.save(tasks);
    }

    private static void handleMarkCommand(String command, String args, TaskList tasks) throws InputException {
        if (args.isEmpty()) {
            throw new InputException("Usage: " + command + " <task number>");
        }

        try {
            int index = Integer.parseInt(args) - 1;
            if (index < 0 || index >= tasks.getSize()) {
                throw new InputException("Invalid task number. I don't have that many tasks!");
            }

            if (command.equals("mark")) {
                tasks.getTask(index).markDone();
                System.out.println("Nice! I've marked this task as done:\n  " + tasks.getTask(index));
            } else {
                tasks.getTask(index).unmark();
                System.out.println("OK, I've marked this task as not done yet:\n  " + tasks.getTask(index));
            }
        } catch (NumberFormatException e) {
            throw new InputException("Please provide a valid number after " + command + ".");
        }
    }

    private static void handleTodo(String args, TaskList tasks) throws InputException {
        if (args.isEmpty()) {
            throw new InputException("The task description of a todo cannot be empty.");
        }
        tasks.addTask(new Todo(args));
    }

    private static void handleDeadline(String args, TaskList tasks) throws InputException {
        int indexOfBy = args.indexOf("/by");
        if (indexOfBy == -1) {
            throw new InputException("Invalid deadline format. Use: deadline <desc> /by <date>");
        }

        String description = args.substring(0, indexOfBy).trim();
        String by = args.substring(indexOfBy + 3).trim();

        if (description.isEmpty() || by.isEmpty()) {
            throw new InputException("The description and date of a deadline cannot be empty.");
        }
        tasks.addTask(new Deadline(description, by));
    }

    private static void handleEvent(String args, TaskList tasks) throws InputException {
        int indexOfFrom = args.indexOf("/from");
        int indexOfTo = args.indexOf("/to");

        if (indexOfFrom == -1 || indexOfTo == -1 || indexOfTo <= indexOfFrom) {
            throw new InputException("Invalid event format. Use: event <desc> /from <start> /to <end>");
        }

        String description = args.substring(0, indexOfFrom).trim();
        String from = args.substring(indexOfFrom + 5, indexOfTo).trim();
        String to = args.substring(indexOfTo + 3).trim();

        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new InputException("Event details (desc, from, to) cannot be empty.");
        }
        tasks.addTask(new Event(description, from, to));
    }

    private static void handleDelete(String args, TaskList tasks) throws InputException {
        if (args.isEmpty()) {
            throw new InputException("Usage: delete <task number>");
        }
        try {
            int index = Integer.parseInt(args) - 1;
            if (index < 0 || index >= tasks.getSize()) {
                throw new InputException("Invalid task number.");
            }
            tasks.deleteTask(index);
        } catch (NumberFormatException e) {
            throw new InputException("Please provide a valid number to delete.");
        }
    }

    private static void handleFind(String args, TaskList tasks) throws InputException {
        if (args.isEmpty()) {
            throw new InputException("The keyword for find cannot be empty.");
        }
        tasks.findTasks(args);
    }
}