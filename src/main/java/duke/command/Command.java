package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command class that is used to execute specific action based on the command type.
 */
public abstract class Command {

    private boolean isExit = false;

    public boolean isExit() {
        return isExit;
    }

    public void setExit(boolean exit) {
        isExit = exit;
    }

    public abstract String execute(TaskList taskList, Ui ui, Storage storage);
}
