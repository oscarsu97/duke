package duke.task;

/**
 * Represents a Task class that needs to be completed.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initialises Task class with description of the task.
     *
     * @param description description of the task to be done
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon that represents whether the task is completed.
     *
     * @return status icon
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Sets the task to be completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }


    public String getDescription() {
        return description;
    }

    /**
     * Return a boolean value that shows whether the task is completed.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * String representation of task class.
     *
     * @return String representation
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}