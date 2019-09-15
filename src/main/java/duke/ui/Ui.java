package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

/**
 * Represents a User Interface that handles interaction with the user.
 */
public class Ui {

    /**
     * Prints the welcome message.
     */
    public void showWelcome() {
        String welcome = "Hello! I'm duke\nWhat can I do for you?\n";
        System.out.println(welcome);
    }

    /**
     * Prints the goodbye message.
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints the list of tasks in the task list.
     */
    public String printTaskList(TaskList taskList) {
        String str = "Here are the tasks in your list:\n";
        for (int i = 1; i <= taskList.getTaskListSize(); i++) {
            str += String.format("%s. %s\n", i, taskList.getTask(i - 1));
        }
        return str;
    }

    /**
     * Prints the task that is being marked as done.
     *
     * @param task the task that is being marked.
     */
    public String printMarkedTask(Task task) {
        String str = "Nice! I've marked this task as done:\n" + task;
        return str;
    }

    /**
     * Prints the task that is being removed from the task list.
     *
     * @param task     the task that is being removed
     * @param taskSize the size of the list after deletion
     */
    public String printDeletedTask(Task task, int taskSize) {
        String str = "Noted. I've removed this task:\n" + task
                + "\nNow you have " + taskSize + " tasks in the list.";
        return str;
    }

    /**
     * Prints the task added message.
     *
     * @param taskList the task list that store all the tasks
     * @return the string message
     */
    public String printTaskAdded(TaskList taskList) {
        int taskListSize = taskList.getTaskListSize();
        Task task = taskList.getTask(taskListSize - 1);
        String str = "Got it. I've added this task:\n" + task
                + "\nNow you have " + taskListSize + " tasks in the list.";
        return str;
    }

    /**
     * Prints all task that match with the keyword.
     *
     * @param matchingTaskList the list of tasks that is a match
     */
    public String printMatchingTasks(ArrayList<Task> matchingTaskList) {
        String str = "Here are the matching tasks in your list:\n";
        for (int i = 1; i <= matchingTaskList.size(); i++) {
            str += String.format("%s. %s\n", i, matchingTaskList.get(i - 1));
        }
        return str;
    }
}