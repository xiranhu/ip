package zayne;

import zayne.tasks.Task;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println(" Got it. I've added this task:\n  " + task);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
    }

    public void listTasks() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public void deleteTask(int index) {
        Task removed = tasks.remove(index);
        System.out.println(" Noted. I've removed this task:\n  " + removed);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
    }

    public void findTasks(String keyword) {
        System.out.println(" Here are the matching tasks in your list:");
        int matchCount = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchCount++;
                System.out.println(" " + matchCount + "." + tasks.get(i));
            }
        }
        if (matchCount == 0) {
            System.out.println(" No tasks match your keyword: " + keyword);
        }
    }
}