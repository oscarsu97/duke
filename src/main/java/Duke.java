import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        List<String> tasks = new ArrayList<>();
        String welcome = "Hello! I'm Duke\nWhat can I do for you?\n";
        System.out.println(welcome);

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                for (int i = 1; i <= tasks.size(); i++) {
                    System.out.println("" + i + ". " + tasks.get(i - 1));
                }
            } else {
                tasks.add(input);
                System.out.println("added: " + input);
            }
            input = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again!");
    }
}
