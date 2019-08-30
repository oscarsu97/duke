import Duke.Parser.Parser;
import Duke.Storage.Storage;
import Duke.Task.TaskList;
import Duke.Ui.Ui;

import java.io.File;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
        parser = new Parser(taskList, ui);
    }


    public void run() {
        ui.showWelcome();
        TaskList tasks = parser.parse();
        storage.updateFile(tasks.getTaskList());
    }

    public static void main(String[] args) {
        File file = new File("data/duke.txt");
        String path = file.getAbsolutePath();
        new Duke(path).run();
    }
}

