package Duke.Ui;

import Duke.Task.Task;
import Duke.Task.TaskList;

import java.util.ArrayList;

public class Ui {

    public Ui() {

    }

    public void showWelcome() {
        String welcome = "Hello! I'm Duke\nWhat can I do for you?\n";
        System.out.println(welcome);
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printTaskList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= taskList.getTaskSize(); i++) {
            System.out.println("" + i + ". " + taskList.getTask(i - 1));
        }
    }

    public void printMarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    public void printRemoveTask(Task task, int taskSize) {
        System.out.println("Noted. I've removed this task:\n" + task
                + "\nNow you have " + taskSize + " tasks in the list.");
    }

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