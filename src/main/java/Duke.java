import java.lang.annotation.IncompleteAnnotationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        List<Task> tasks = new ArrayList<>();
        boolean theEnd = true;
        String welcome = "Hello! I'm Duke\nWhat can I do for you?\n";
        System.out.println(welcome);

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (theEnd) {
            try {
                String[] command = input.split(" ");
                String first = command[0];
                String error = "The description of a todo cannot be empty.";

                if (first.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 1; i <= tasks.size(); i++) {
                        System.out.println("" + i + ". " + tasks.get(i - 1));
                    }
                }
                else if (first.equals("done")) {
                    if (command.length < 2 || command.length > 2) {
                        throw new IncompleteCommandException(error);
                    }
                    int second = Integer.parseInt(command[1]) - 1;

                    tasks.get(second).markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n" + tasks.get(second));
                }
                else if (first.equals("deadline")) {
                    if (command.length < 2) {
                        throw new IncompleteCommandException(error);
                    }
                    String[] msg = input.split("/by");
                    String newMessage = msg[0] + "(by:" + msg[1] + ")";
                    tasks.add(new Deadline(newMessage));
                    System.out.println("Got it. I've added this task:\n" + tasks.get(tasks.size() - 1)
                            + "\nNow you have " + tasks.size() + " tasks in the list");
                }
                else if (first.equals("event")) {
                    if (command.length < 2) {
                        throw new IncompleteCommandException(error);
                    }
                    String[] msg = input.split("/at");
                    String newMessage = msg[0] + "(at:" + msg[1] + ")";
                    tasks.add(new Deadline(newMessage));
                    System.out.println("Got it. I've added this task:\n" + tasks.get(tasks.size() - 1)
                            + "\nNow you have " + tasks.size() + " tasks in the list");
                }
                //ToDo
                else if (first.equals("todo")) {
                    if (command.length < 2) {
                        throw new IncompleteCommandException(error);
                    }
                    String todo = input.substring(5);
                    tasks.add(new ToDo(todo));
                    System.out.println("Got it. I've added this task:\n" + tasks.get(tasks.size() - 1)
                            + "\nNow you have " + tasks.size() + " tasks in the list");
                } else if (input.equals("bye")) {
                    theEnd = false;
                    System.out.println("Bye. Hope to see you again soon!");
                }
                else {
                    throw new InvalidCommandException("I'm sorry, but I don't know what that means :-(");
                }
                input = sc.nextLine();
            } catch (Exception err) {
                System.out.println(err);
                input = sc.nextLine();
            }
        }
    }
}
