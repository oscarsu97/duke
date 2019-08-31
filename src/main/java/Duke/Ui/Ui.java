package Duke.Ui;

import Duke.Task.Task;

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

    public void printTaskList() {
        System.out.println("Here are the tasks in your list:");
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
}