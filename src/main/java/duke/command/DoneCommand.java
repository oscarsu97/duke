package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * DoneCommand that take care of checking task that is done.
 */
public class DoneCommand extends Command {

    /**
     * Initialise DoneCommand class.
     */
    public DoneCommand() {
        super();
    }

    /**
     * Mark the specific task as done in the task list
     * @param taskDetails details of the task
     * @param taskList list of the task to be done
     * @param ui Ui class that takes care of interaction with user
     * @return string representing the marked task message
     */
    public String execute(String[] taskDetails, TaskList taskList, Ui ui){
        int index = Integer.parseInt(taskDetails[1]) - 1;
        taskList.getTask(index).markAsDone();
        return ui.printMarkedTask(taskList.getTask(index));
    }
}
