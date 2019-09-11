package duke.command;

import duke.task.TaskList;

/**
 * Command class that is used to execute task based on the command type
 */
public abstract class Command {
    private TaskList taskList;

    /**
     * Initialise the Command clas
     */
    public Command() {
    }
}
