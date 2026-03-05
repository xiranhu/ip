package zayne.tasks;

/**
 * Represents a task with a specific deadline.
 * A Deadline object contains a description and a date/time string representing the deadline.
 */
public class Deadline extends Task{
    /** The deadline date or time associated with this task. */
    private String by; //declare a new variable only accessed by this class

    /**
     * Constructs a Deadline task with a description and a deadline date/time.
     * * @param description The textual description of the task.
     * @param by The deadline date or time (e.g., "Sunday" or "2026-03-06").
     */
    public Deadline(String description, String by) {  //constructor
        super(description);
        this.by = by;
    }

    /**
     * Returns the deadline date or time of the task.
     * * @return A string representing when the task is due.
     */
    public String getBy() { //getter
        return by;
    }

    /**
     * Returns a string representation of the deadline task, including its type [D],
     * its status, description, and the deadline time.
     * * @return A formatted string suitable for console display.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns a string formatted for saving the deadline task to a data file.
     * The format used is "D | status | description | deadline".
     * * @return A formatted string for file storage.
     */
    @Override
    public String toFileString() {
        String done = isDone() ? "1" : "0";
        return "D | " + done + " | " + getDescription() + " | " + by;
    }
}
