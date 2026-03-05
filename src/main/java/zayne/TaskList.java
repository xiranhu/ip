package zayne;

import zayne.tasks.Task;
import java.util.ArrayList;

/**
 * Represents the list of tasks and handles operations like adding, deleting, and finding tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a specified task to the task list and displays a confirmation message to the user.
     * The message includes the task details and the updated total number of tasks.
     *
     * @param task The Task object (Todo, Deadline, or Event) to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println(" Got it. I've added this task:\n  " + task);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Lists all the tasks currently stored in the task list to the console.
     * If the list is empty, it notifies the user.
     */
    public void listTasks() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Retrieves a specific task from the list based on its index.
     * @param index The zero-based index of the task in the list.
     * @return The Task object at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the current number of tasks in the list.
     * @return The size of the internal ArrayList.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Removes a task from the list and displays a confirmation message.
     * @param index The zero-based index of the task to be removed.
     */
    public void deleteTask(int index) {
        Task removed = tasks.remove(index);
        System.out.println(" Noted. I've removed this task:\n  " + removed);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Searches for tasks that contain the specified keyword in their description.
     * The search is case-insensitive. If matches are found, they are printed to the console.
     * * @param keyword The search term used to filter the tasks.
     */
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