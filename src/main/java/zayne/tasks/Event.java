package zayne.tasks;

/**
 * Represents a task that occurs within a specific time frame.
 * An Event object contains a description, a start time, and an end time.
 */
public class Event extends Task {
    /** The starting time or date of the event. */
    private String from;
    /** The ending time or date of the event. */
    private String to;

    /**
     * Constructs an Event task with a description and a duration defined by start and end times.
     * * @param description The textual description of the event.
     * @param from The starting time or date (e.g., "Mon 2pm").
     * @param to The ending time or date (e.g., "4pm").
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start time of the event.
     * @return A string representing when the event begins.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Returns the end time of the event.
     * @return A string representing when the event ends.
     */
    public String getTo() {
        return to;
    }

    /**
     * Returns a string representation of the event task, including its type [E],
     * its status, description, and the time range.
     * @return A formatted string suitable for console display.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns a string formatted for saving the event task to a data file.
     * The format used is "E | status | description | from | to".
     * @return A formatted string for file storage.
     */
    @Override
    public String toFileString() {
        String done = isDone() ? "1" : "0";
        return "E | " + done + " | " + getDescription() + " | " + from + " | " + to;
    }
}
