package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * DoneCommand that handles the marking of task as done.
 */
public class DoneCommand extends Command {

    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the specific task as done in the task list.
     *
     * @param taskList list of the task
     * @param ui       Ui class that handles interaction with user
     * @return string representing the marked task message
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.getTask(index).markAsDone();
        return ui.printMarkedTask(taskList.getTask(index));
    }
}
