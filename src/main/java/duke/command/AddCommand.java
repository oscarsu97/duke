package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * AddCommand class that adds task into the task list.
 */
public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes task based on the command type and
     * then add them into task list.
     *
     * @param taskList list of tasks to be done
     * @param ui       Ui class that takes care of interaction with user
     * @param storage  storage file the task list are stored
     * @return string that represents the task added message
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {

        taskList.addTask(task);
        return ui.printTaskAdded(taskList);
    }
}
