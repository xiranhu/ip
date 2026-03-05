package zayne;

import zayne.exceptions.InputException;
import java.io.FileNotFoundException;

/**
 * The main entry point for the Zayne task management application.
 * Coordinates the Ui, Storage, TaskList, and Parser to run the program loop.
 */
public class Zayne {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes the Zayne application with a specific file path for storage.
     * @param filePath The path to the file where tasks are saved (e.g., "data/zayne.txt").
     */
    public Zayne(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Starts the main program loop, reading commands and executing them until the user exits.
     */
    public void run() {
        ui.showLogo();
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                if (fullCommand.equalsIgnoreCase("bye")) {
                    ui.showBye();
                    isExit = true;
                } else {
                    ui.printDivider();
                    Parser.parse(fullCommand, tasks, ui, storage);
                    ui.printDivider();
                }
            } catch (InputException e) {
                ui.showError(e.getMessage());
                ui.printDivider();
            }
        }
    }

    public static void main(String[] args) {
        new Zayne("data/zayne.txt").run();
    }
}