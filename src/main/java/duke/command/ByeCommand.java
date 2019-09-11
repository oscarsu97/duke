package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * ByeCommand that deals with "bye" command.
 */
public class ByeCommand extends Command{

    /**
     * Initialise ByeCommand class.
     */
    public ByeCommand(){
        super();
    }

    /**
     * Update text file with the updated task list.
     * @param ui Ui class that deals with interaction with user
     * @param storage Storage class that deals with storage of data
     * @param taskList list of tasks stored
     * @return string representing good bye meessage
     */
    public String execute(Ui ui, Storage storage, TaskList taskList){
        storage.updateFile(taskList.getTaskList());
        return ui.showGoodbye();
    }
}
