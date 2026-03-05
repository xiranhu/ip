package zayne.tasks;

/**
 * Represents a generic task with a description and completion status.
 */
public class Task {
    /** The textual description of the task. */
    private String description;
    /** The completion status of the task. */
    private boolean isDone;

    /**
     * Default constructor for an empty task.
     */
    public Task () {
    }

    /**
     * Constructs a Task with a specified description.
     * Initial status is set to not done.
     * * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as completed.
     */
    public void markDone() {  //setter
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void unmark() {  //setter
        this.isDone = false;
    }

    /**
     * Checks if the task is completed.
     * * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {  //getter
        return isDone;
    }

    /**
     * Returns the description of the task.
     * * @return The task description string.
     */
    public String getDescription() {  //getter
        return description;
    }

    /**
     * Returns a string representation of the task, including its completion status icon.
     * * @return A formatted string like "[X] read book".
     */
    public String toString() {
        String status = isDone ? "[X]" : "[ ]";
        return status + " " + description;
    }

    /**
     * Returns a string formatted for saving the task to a data file.
     * * @return A formatted string for file storage (e.g., "T | 1 | read book").
     */
    public String toFileString() {
        String done = isDone ? "1" : "0";
        return "T | " + done + " | " + description;
    }
}
