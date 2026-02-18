package zayne.tasks;

public class Event extends Task {
    private String from;
    private String to;  ////declare two new variables only accessed by this class

    public Event(String description, String from, String to) {  //constructor
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toFileString() {
        String done = isDone() ? "1" : "0";
        return "E | " + done + " | " + getDescription() + " | " + from + " | " + to;
    }
}
