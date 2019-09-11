package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * FindCommand that deals with "find" command.
 */
public class FindCommand extends Command {

    /**
     * Initialises FindCommand class.
     */
    public FindCommand() {
        super();
    }

    /**
     * Find the task that has matching keyword and
     * add them under a new ArrayList that stores
     * all the matching tasks.
     * @param taskList list of tasks
     * @param input input given by the user
     * @param ui Ui class that deals with interaction with user
     * @return string representing the message that list the list of matching class
     */
    public String execute(TaskList taskList, String input, Ui ui) {
        String keyword = input.substring(5);

        //find all tasks that match keyword
        ArrayList<Task> matchingTasks = taskList.findMatchingTasks(keyword);
        taskList.setTaskList(matchingTasks);

        //print all tasks that match keyword
        return ui.printMatchingTasks(matchingTasks);
    }
}

