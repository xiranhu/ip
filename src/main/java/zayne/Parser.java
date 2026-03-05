package zayne;

import zayne.exceptions.InputException;
import zayne.tasks.Deadline;
import zayne.tasks.Event;
import zayne.tasks.Todo;

public class Parser {
    public static void parse(String fullCommand, TaskList tasks, Ui ui, Storage storage) throws InputException {
        if (fullCommand.equalsIgnoreCase("list")) {
            tasks.listTasks();
        } else if (fullCommand.startsWith("mark") || fullCommand.startsWith("unmark")) {
            handleMarkCommand(fullCommand, tasks);
        } else if (fullCommand.startsWith("todo")) {
            handleTodo(fullCommand, tasks);
        } else if (fullCommand.startsWith("deadline")) {
            handleDeadline(fullCommand, tasks);
        } else if (fullCommand.startsWith("event")) {
            handleEvent(fullCommand, tasks);
        } else if (fullCommand.startsWith("delete")) {
            handleDelete(fullCommand, tasks);
        } else if (fullCommand.startsWith("find")) {
            handleFind(fullCommand, tasks);
        } else {
            throw new InputException("Invalid Command Keyword.");
        }

        // Save to file after every successful command that modifies the list
        storage.save(tasks);
    }

    private static void handleMarkCommand(String command, TaskList tasks) throws InputException {
        String[] parts = command.split(" ");
        if (parts.length != 2) {
            throw new InputException("Usage: mark/unmark <task number>");
        }

        try {
            int index = Integer.parseInt(parts[1]) - 1;
            if (index < 0 || index >= tasks.getSize()) {
                throw new InputException("Invalid task number. I don't have that many tasks!");
            }

            if (command.startsWith("mark")) {
                tasks.getTask(index).markDone();
                System.out.println("Nice! I've marked this task as done:\n  " + tasks.getTask(index));
            } else {
                tasks.getTask(index).unmark();
                System.out.println("OK, I've marked this task as not done yet:\n  " + tasks.getTask(index));
            }
        } catch (NumberFormatException e) {
            throw new InputException("Please provide a valid number after mark/unmark.");
        }
    }

    private static void handleTodo(String command, TaskList tasks) throws InputException {
        if (command.length() <= 5) {
            throw new InputException("The task description of a todo cannot be empty.");
        }
        String description = command.substring(5).trim();
        if (description.isEmpty()) {
            throw new InputException("The task description of a todo cannot be empty.");
        }
        tasks.addTask(new Todo(description));
    }

    private static void handleDeadline(String command, TaskList tasks) throws InputException {
        int indexOfBy = command.indexOf("/by");
        if (indexOfBy == -1) {
            throw new InputException("Invalid deadline format. Use: deadline <desc> /by <date>");
        }

        String description = command.substring(9, indexOfBy).trim();
        String by = command.substring(indexOfBy + 3).trim();

        if (description.isEmpty() || by.isEmpty()) {
            throw new InputException("The description and date of a deadline cannot be empty.");
        }
        tasks.addTask(new Deadline(description, by));
    }

    private static void handleEvent(String command, TaskList tasks) throws InputException {
        int indexOfFrom = command.indexOf("/from");
        int indexOfTo = command.indexOf("/to");

        if (indexOfFrom == -1 || indexOfTo == -1 || indexOfTo <= indexOfFrom) {
            throw new InputException("Invalid event format. Use: event <desc> /from <start> /to <end>");
        }

        String description = command.substring(6, indexOfFrom).trim();
        String from = command.substring(indexOfFrom + 5, indexOfTo).trim();
        String to = command.substring(indexOfTo + 3).trim();

        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new InputException("Event details (desc, from, to) cannot be empty.");
        }
        tasks.addTask(new Event(description, from, to));
    }

    private static void handleDelete(String command, TaskList tasks) throws InputException {
        String[] parts = command.split(" ");
        if (parts.length != 2) {
            throw new InputException("Usage: delete <task number>");
        }
        try {
            int index = Integer.parseInt(parts[1]) - 1;
            if (index < 0 || index >= tasks.getSize()) {
                throw new InputException("Invalid task number.");
            }
            tasks.deleteTask(index);
        } catch (NumberFormatException e) {
            throw new InputException("Please provide a valid number to delete.");
        }
    }

    private static void handleFind(String command, TaskList tasks) throws InputException {
        if (command.length() <= 5) {
            throw new InputException("The keyword for find cannot be empty.");
        }
        String keyword = command.substring(5).trim();
        tasks.findTasks(keyword);
    }
}