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

            if (command[0].equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= tasks.size(); i++) {
                    System.out.println("" + i + ". " + tasks.get(i - 1));
                }
            } else if (command[0].equals("done")) {
                int second = Integer.parseInt(command[1]) - 1;

                tasks.get(second).markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" + tasks.get(second));
            } else {
                tasks.add(new Task(input));
                System.out.println("added: " + input);
            }
            input = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again!");
    }
}
