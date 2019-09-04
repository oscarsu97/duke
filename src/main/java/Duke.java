import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a bot that helps user to keep track of their task list.
 * It execute task according to the user's input command.
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    /**
     * initialise duke and load data from duke.txt file
     * into a TaskList class.
     * @param filePath file path of duke.txt file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
        parser = new Parser(taskList, ui);
    }

    /**
     * starts off by greeting user and afterwards,
     * user command is being parsed to check for the validity
     * of the code. At the end of the whole process, it will
     * update the list of tasks back into duke.txt file.
     */
    public void run() {
        ui.showWelcome();
        TaskList tasks = parser.parse();
        storage.updateFile(tasks.getTaskList());
    }

    /**
     * main method where the code starts from.
     * @param args arguments
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}

