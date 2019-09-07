import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Scanner;


/**
 * Represents a bot that helps user to keep track of their task list.
 * It execute task according to the user's input command.
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;
    private Scanner sc;

    /**
     * initialise duke and load data from duke.txt file
     * into a TaskList class.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt");
        taskList = new TaskList(storage.load());
        parser = new Parser(taskList, ui, storage);
        sc = new Scanner(System.in);
    }

    /**
     * starts off by greeting user and afterwards,
     * user command is being parsed to check for the validity
     * of the code. At the end of the whole process, it will
     * update the list of tasks back into duke.txt file.
     */
    public void run() {
        ui.showWelcome();
        boolean isBye = false;
        String input = sc.nextLine();
        while (!isBye) {
            if (input.startsWith("bye")) {
                System.out.println(parser.parse(input));
                isBye = true;
            } else {
                System.out.println(parser.parse(input));
                input = sc.nextLine();
            }
        }
    }


    /**
     * main method where the code starts from.
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    public String getResponse(String input) {
        return parser.parse(input);
    }
}

