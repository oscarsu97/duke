package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * ListCommand that handles the printing of tasks in tasklist to show to user.
 */
public class ListCommand extends Command {

    /**
     * Prints the list of tasks in task list.
     *
     * @param taskList list of tasks
     * @param ui       Ui that deals with interaction with the user
     * @return string representing the list of tasks
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.printTaskList(taskList);
    }
}
