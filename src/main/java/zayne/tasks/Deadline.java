package zayne.tasks;

public class Deadline extends Task{
    private String by; //declare a new variable only accessed by this class

    public Deadline(String description, String by) {  //constructor
        super(description);
        this.by = by;
    }

    public String getBy() { //getter
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toFileString() {
        String done = isDone() ? "1" : "0";
        return "D | " + done + " | " + getDescription() + " | " + by;
    }
}
