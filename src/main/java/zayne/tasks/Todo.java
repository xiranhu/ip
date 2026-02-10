package zayne.tasks;

public class Todo extends Task{
    public Todo(String description) { //Constructor
        super(description);  //inherit the properties of "Task"
    }

    @Override
    public String toString() {  //override the parent toString() method because we want to print a [T] at the start
        return "[T]" + super.toString();
    }
}

