package Duke.Ui;

import Duke.Task.Task;
import Duke.Task.TaskList;

import java.util.ArrayList;

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
    public void printTaskList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= taskList.getTaskListSize(); i++) {
            System.out.println("" + i + ". " + taskList.getTask(i - 1));
        }
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

    public void printMatchingTasks(ArrayList<Task> matchingTaskList) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 1; i <= matchingTaskList.size(); i++) {
            System.out.println("" + i + ". " + matchingTaskList.get(i - 1));
        }
    }
}