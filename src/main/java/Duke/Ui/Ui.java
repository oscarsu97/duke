package Duke.Ui;

import Duke.Task.Task;

/**
 * Represents a User Interface that deals with interactions with the user.
 */
public class Ui {

    /**
     * initialise Ui class with an empty parameter
     */
    public Ui() {

    }

    /**
     * Prints the welcome message
     */
    public void showWelcome() {
        String welcome = "Hello! I'm Duke\nWhat can I do for you?\n";
        System.out.println(welcome);
    }

    /**
     * Prints the goodbye message
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the sentence for the task list
     */
    public void printTaskList() {
        System.out.println("Here are the tasks in your list:");
    }

    /**
     * Prints the task that is being marked as done
     *
     * @param task the task that is being marked
     */
    public void printMarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Prints the task that is being removed from the task list
     *
     * @param task     the task that is being removed
     * @param taskSize the size of the list after deletion
     */
    public void printRemoveTask(Task task, int taskSize) {
        System.out.println("Noted. I've removed this task:\n" + task
                + "\nNow you have " + taskSize + " tasks in the list.");
    }

    /**
     * Prints the task being added message
     *
     * @param task     task that is being added
     * @param taskSize size of the list after addition
     */
    public void printTaskAdded(Task task, int taskSize) {
        System.out.println("Got it. I've added this task:\n" + task
                + "\nNow you have " + taskSize + " tasks in the list");
    }
}