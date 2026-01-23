public class Task {
    private String description;
    private boolean isDone;

    public Task () {  //task constructor without parameters
    }

    public Task(String description) {  //task constructor with parameters
        this.description = description;
        this.isDone = false;
    }
    //get/set for 'done'
    public void markDone() {  //setter
        this.isDone = true;
    }

    public void unmark() {  //setter
        this.isDone = false;
    }

    public boolean isDone() {  //getter
        return isDone;
    }

    //get function for 'description'
    public String getDescription() {  //getter
        return description;
    }
}
