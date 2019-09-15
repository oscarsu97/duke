package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * ByeCommand that handles the storage of updated task list
 * into storage file and exiting.
 */
public class ByeCommand extends Command {

    /**
     * Initialise ByeCommand class.
     */
    public ByeCommand() {
        super();
    }

    /**
     * Updates text file with the updated task list
     * and prints the goodbye message.
     *
     * @param ui       Ui class that deals with interaction with user
     * @param storage  Storage class that deals with storage of data
     * @param taskList list of tasks stored
     * @return string representing good bye message
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        storage.updateFile(taskList.getTaskList());
        setExit(true);
        return ui.showGoodbye();
    }
}
