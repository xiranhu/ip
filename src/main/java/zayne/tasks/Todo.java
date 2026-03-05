package zayne.tasks;

/**
 * Represents a basic task without any specific date or time attached to it.
 * A Todo object only contains a description and a completion status.
 */
public class Todo extends Task{
    /**
     * Constructs a Todo task with a specified description.
     * * @param description The textual description of the task.
     */
    public Todo(String description) { //Constructor
        super(description);  //inherit the properties of "Task"
    }

    /**
     * Returns a string representation of the todo task, including its type [T],
     * its status, and its description.
     * * @return A formatted string suitable for console display.
     */
    @Override
    public String toString() {  //override the parent toString() method because we want to print a [T] at the start
        return "[T]" + super.toString();
    }

    /**
     * Returns a string formatted for saving the todo task to a data file.
     * The format used is "T | status | description".
     * * @return A formatted string for file storage.
     */
    @Override
    public String toFileString() {
        String done = isDone() ? "1" : "0";
        return "T | " + done + " | " + getDescription();
    }
}

