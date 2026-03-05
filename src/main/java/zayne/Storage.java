package zayne;

import zayne.tasks.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(TaskList tasks) {
        try {
            File f = new File(filePath);
            f.getParentFile().mkdirs();
            FileWriter fw = new FileWriter(f);
            for (int i = 0; i < tasks.getSize(); i++) {
                fw.write(tasks.getTask(i).toFileString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        File f = new File(filePath);
        ArrayList<Task> loadedTasks = new ArrayList<>();
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            String[] parts = s.nextLine().split(" \\| ");
            Task task = null;
            if (parts[0].equals("T")) task = new Todo(parts[2]);
            else if (parts[0].equals("D")) task = new Deadline(parts[2], parts[3]);
            else if (parts[0].equals("E")) task = new Event(parts[2], parts[3], parts[4]);

            if (task != null) {
                if (parts[1].equals("1")) task.markDone();
                loadedTasks.add(task);
            }
        }
        return loadedTasks;
    }
}