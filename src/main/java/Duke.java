import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import java.util.Scanner;


/**
 * Represents a chat bot that helps user to manage their task list.
 * It executes according to the command given by user's input.
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;
    private Scanner sc;

    /**
     * Initialises Duke class and loads the task list from storage file
     * into a TaskList class.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt");
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        parser = new Parser(taskList);
        sc = new Scanner(System.in);
    }

    /**
     * Prints welcome message, reads input from user
     * and executes it, until the loop exits.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = sc.nextLine();
                Command c = parser.parse(input);
                System.out.println(c.execute(taskList, ui, storage));
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Parse the input given by user and execute the command
     * specific to command given.
     *
     * @param input input entered into the dialog by the user
     * @return string representing the response for the user
     */
    public String getResponse(String input) {
        try {
            Command c = parser.parse(input);
            return c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}

