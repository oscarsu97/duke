package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * FindCommand that handles the finding of task that matches
 * a specified search.
 */
public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds the task that matches the keyword and
     * adds them under a new ArrayList that stores
     * all the matching tasks and prints the matching
     * task message.
     *
     * @param taskList list of tasks
     * @param ui       Ui class that deals with interaction with user
     * @return string representing the message that has the list of matching class
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        //find all tasks that match keyword
        ArrayList<Task> matchingTasks = taskList.findMatchingTasks(keyword);

        //print all tasks that match keyword
        return ui.printMatchingTasks(matchingTasks);
    }
}

