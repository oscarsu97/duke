package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * DeleteCommand that deals with "delete" command.
 */
public class DeleteCommand extends Command {

    /**
     * Initialises DeleteCommand class.
     */
    public DeleteCommand() {
        super();
    }

    /**
     * Delete the specified task from the task list
     * @param taskDetails details of the specified task
     * @param taskList list of tasks
     * @param ui Ui task that deals with interaction with the user
     * @return string representing the deleted task message
     */
    public String execute(String[] taskDetails, TaskList taskList, Ui ui) {
        int index = Integer.parseInt(taskDetails[1]) - 1;
        Task taskToDelete = taskList.getTask(index);

        taskList.deleteTask(index);
        int taskListSize = taskList.getTaskListSize();
        return ui.printDeletedTask(taskToDelete, taskListSize);
    }
}

