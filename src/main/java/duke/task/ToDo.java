package duke.task;

/**
 * Represents a ToDo task that needs to be done.
 */
public class ToDo extends Task {

    /**
     * Initialises ToDo class with description of the task.
     *
     * @param description task description
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * String representation of the To Do class.
     *
     * @return String representation
     */
    @Override
    public String toString() {
        return "[T]" + "[" + getStatusIcon() + "] " + description;
    }
}
