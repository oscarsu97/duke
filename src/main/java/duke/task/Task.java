package duke.task;

/**
 * Represents a task class that needs to be done.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initialise task class with description of the task.
     *
     * @param description task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get the status icon  that represents whether the task is completed.
     *
     * @return status icon
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Set the task to be completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Get the description of the task.
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Return a boolean value that shows whether the task is completed.
     *
     * @return true if task is completed, false if task is incomplete.
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