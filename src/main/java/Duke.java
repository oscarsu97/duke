import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static List<Task> tasks;
    private static Scanner sc;

    public static void main(String[] args) throws IOException {

        String welcome = "Hello! I'm Duke\nWhat can I do for you?\n";
        System.out.println(welcome);
        tasks = new ArrayList<>();
        readUserInput();
    }

    private static void readUserInput() throws IOException {
        sc = new Scanner(System.in);
        String input = sc.nextLine();
        String error = "The description of a todo cannot be empty.";
        boolean isTheEnd = false;

        while (!isTheEnd) {
            try {
                String[] taskDetails = input.split(" ");
                String taskType = taskDetails[0];
                if (taskType.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 1; i <= tasks.size(); i++) {
                        System.out.println("" + i + ". " + tasks.get(i - 1));
                    }
                    input = sc.nextLine();
                } else if (taskType.equals("done")) {
                    if (taskDetails.length < 2) {
                        throw new IncompleteCommandException(error);
                    } else if (Integer.parseInt(taskDetails[1]) > tasks.size()) {
                        throw new IndexOffBoundException();
                    }
                    int taskIndex = Integer.parseInt(taskDetails[1]) - 1;

                    tasks.get(taskIndex).markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n" + tasks.get(taskIndex));
                    input = sc.nextLine();

                } else if (taskType.equals("deadline")) {
                    if (taskDetails.length < 2) {
                        throw new IncompleteCommandException(error);
                    }
                    String[] msg = input.split("/by");

                    tasks.add(new Deadline(msg[0].trim(), msg[1].trim()));
                    printTaskAdded();
                    input = sc.nextLine();

                } else if (taskType.equals("event")) {
                    if (taskDetails.length < 2) {
                        throw new IncompleteCommandException(error);
                    }
                    String[] msg = input.split("/at");

                    tasks.add(new Event(msg[0].trim(), msg[1].trim()));
                    printTaskAdded();
                    input = sc.nextLine();

                } else if (taskType.equals("todo")) {
                    if (taskDetails.length < 2) {
                        throw new IncompleteCommandException(error);
                    }
                    String toDo = input.substring(5);
                    tasks.add(new ToDo(toDo));
                    printTaskAdded();
                    input = sc.nextLine();

                } else if (input.equals("bye")) {
                    isTheEnd = true;
                    System.out.println("Bye. Hope to see you again soon!");

                } else if (taskType.equals("delete")) {
                    if (taskDetails.length < 2) {
                        throw new IncompleteCommandException(error);
                    }
                    if (Integer.parseInt(taskDetails[1]) > tasks.size()) {
                        throw new IndexOffBoundException();
                    }
                    int second = Integer.parseInt(taskDetails[1]) - 1;

                    System.out.println("Noted. I've removed this task:\n" + tasks.get(second)
                            + "\nNow you have " + (tasks.size() - 1) + " tasks in the list.");
                    tasks.remove(second);
                    input = sc.nextLine();

                } else {
                    throw new InvalidCommandException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (Exception err) {
                System.out.println(err);
                input = sc.nextLine();
            }
        }
    }
    private static void printTaskAdded() {
        System.out.println("Got it. I've added this task:\n" + tasks.get(tasks.size() - 1)
                + "\nNow you have " + tasks.size() + " tasks in the list");
    }
}
