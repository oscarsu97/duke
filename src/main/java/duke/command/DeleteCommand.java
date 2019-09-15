package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * DeleteCommand that handles the deletion of task from task list.
 */
public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the specified task from the task list.
     *
     * @param taskList list of tasks
     * @param ui       Ui task that deals with interaction with the user
     * @return string representing the deleted task message
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task taskToDelete = taskList.getTask(index);
        taskList.deleteTask(index);
        int taskListSize = taskList.getTaskListSize();
        return ui.printDeletedTask(taskToDelete, taskListSize);
    }
}

