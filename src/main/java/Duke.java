import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        List<Task> tasks = new ArrayList<>();
        String welcome = "Hello! I'm Duke\nWhat can I do for you?\n";
        System.out.println(welcome);

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            String[] command = input.split(" ");
            String first = command[0];

            if (first.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= tasks.size(); i++) {
                    System.out.println("" + i + ". " + tasks.get(i - 1));
                }
            }
            else if (first.equals("done")) {
                int second = Integer.parseInt(command[1]) - 1;

                tasks.get(second).markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" + tasks.get(second));
            }
            else if (first.equals("deadline")) {
                String[] msg = input.split("/by");
                String newMessage = msg[0] + "(by:" + msg[1] + ")";
                tasks.add(new Deadline(newMessage));
                System.out.println("Got it. I've added this task:\n" + tasks.get(tasks.size() - 1)
                        + "\nNow you have " + tasks.size() + " tasks in the list");
            }
            else if (first.equals("event")) {
                String[] msg = input.split("/at");
                String newMessage = msg[0] + "(at:" + msg[1] + ")";
                tasks.add(new Deadline(newMessage));
                System.out.println("Got it. I've added this task:\n" + tasks.get(tasks.size() - 1)
                        + "\nNow you have " + tasks.size() + " tasks in the list");
            }
            //ToDo
            else {
                tasks.add(new ToDo(command[2]));
                System.out.println("Got it. I've added this task:\n" + tasks.get(tasks.size() - 1)
                        + "\nNow you have " + tasks.size() + " tasks in the list");
            }
            input = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again!");
    }
}
