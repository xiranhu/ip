package zayne;

import zayne.exceptions.InputException;
import java.io.FileNotFoundException;

public class Zayne {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Zayne(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

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