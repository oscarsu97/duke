package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command{

    /**
     * Initialises ListCommand class.
     */
    public ListCommand(){
        super();
    }

    /**
     * Get the list of tasks for printing.
     * @param taskList list of tasks
     * @param ui Ui that deals with interaction with the user
     * @return string representing the list of tasks
     */
    public String execute(TaskList taskList, Ui ui){
        return ui.printTaskList(taskList);
    }
}
