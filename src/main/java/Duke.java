import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String welcome = "Hello! I'm Duke\nWhat can I do for you?\n";
        System.out.println(welcome);

    Scanner sc = new Scanner(System.in);
    String input = sc.nextLine();
    while(!input.equals("bye")){
        System.out.println(input);
        input = sc.next();
    }
        System.out.println("Bye. Hope to see you again!");
    }
}
