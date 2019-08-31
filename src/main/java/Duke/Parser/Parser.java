package Duke.Parser;

import Duke.DukeException.IncompleteCommandException;
import Duke.DukeException.IndexOffBoundException;
import Duke.DukeException.InvalidCommandException;
import Duke.Task.Deadline;
import Duke.Task.Event;
import Duke.Task.TaskList;
import Duke.Task.ToDo;
import Duke.Ui.Ui;

import java.util.Scanner;

/**
 * Parser class that deals with making sense of the user command.
 */
public class Parser {
    private TaskList tasklist;
    private Scanner sc;
    private Ui ui;

    /**
     * Initialise Parser class with TaskList and Ui class
     *
     * @param taskList list of tasks
     * @param ui       object that deals with interaction with user
     */
    public Parser(TaskList taskList, Ui ui) {
        this.tasklist = taskList;
        sc = new Scanner(System.in);
        this.ui = ui;
    }

    /**
     * Check if the user command input is valid,
     * otherwise it will prompt user to give a
     * valid input again.
     *
     * @return a list of the updated tasks
     */
    public TaskList parse() {
        String input = sc.nextLine();
        boolean isTheEnd = false;

        while (!isTheEnd) {
            String[] taskDetails = input.split(" ");
            String taskType = taskDetails[0].trim();
            boolean isInValid = checkValidCommand(taskType);
            boolean isInComplete = false;

            if (isInValid) {
                while (isInValid) {
                    input = sc.nextLine();
                    String[] newTaskDetails = input.split(" ");
                    String newTaskType = newTaskDetails[0].trim();
                    isInValid = checkValidCommand(newTaskType);
                }
            }
            if (taskType.equals("list")) {
                ui.printTaskList();
                for (int i = 1; i <= tasklist.getTaskSize(); i++) {
                    System.out.println("" + i + ". " + tasklist.getTask(i - 1));
                }
                input = sc.nextLine();
            } else if (taskType.equals("done")) {
                isInComplete = checkIncompleteCommand(input, 2);
                if (isInComplete) {
                    input = sc.nextLine();
                    continue;
                }
                isInValid = checkValidIndex(Integer.parseInt(taskDetails[1]), tasklist.getTaskSize());
                if (isInValid) {
                    input = sc.nextLine();
                    continue;
                }
                int index = Integer.parseInt(taskDetails[1]) - 1;

                tasklist.getTask(index).markAsDone();
                ui.printMarkedTask(tasklist.getTask(index));
                input = sc.nextLine();

            } else if (taskType.equals("deadline")) {

                String[] msg = input.split("/by");
                isInComplete = checkIncompleteCommand(msg[0], 2);
                if (isInComplete) {
                    input = sc.nextLine();
                    continue;
                }
                isInComplete = checkIncompleteCommand(msg[1], 2);
                if (isInComplete) {
                    input = sc.nextLine();
                    continue;
                }
                tasklist.addTask(new Deadline(msg[0].trim().substring(8).trim(), msg[1].trim()));
                ui.printTaskAdded(tasklist.getTask(tasklist.getTaskSize() - 1), tasklist.getTaskSize());
                input = sc.nextLine();

            } else if (taskType.equals("event")) {

                String[] msg = input.split("/at");
                isInComplete = checkIncompleteCommand(msg[0], 2);
                if (isInComplete) {
                    input = sc.nextLine();
                    continue;
                }
                isInComplete = checkIncompleteCommand(msg[1], 2);
                if (isInComplete) {
                    input = sc.nextLine();
                    continue;
                }
                tasklist.addTask(new Event(msg[0].trim().substring(5).trim(), msg[1].trim()));
                ui.printTaskAdded(tasklist.getTask(tasklist.getTaskSize() - 1), tasklist.getTaskSize());
                input = sc.nextLine();

            } else if (taskType.equals("todo")) {
                checkIncompleteCommand(input, 2);
                String toDo = input.substring(5);
                tasklist.addTask(new ToDo(toDo));
                ui.printTaskAdded(tasklist.getTask(tasklist.getTaskSize() - 1), tasklist.getTaskSize());
                input = sc.nextLine();

            } else if (input.equals("bye")) {
                isTheEnd = true;
                ui.showGoodbye();

            } else if (taskType.equals("delete")) {
                isInComplete = checkIncompleteCommand(input, 2);
                if (isInComplete) {
                    input = sc.nextLine();
                    continue;
                }
                isInValid = checkValidIndex(Integer.parseInt(taskDetails[1]), tasklist.getTaskSize());
                if (isInValid) {
                    input = sc.nextLine();
                    continue;
                }
                int index = Integer.parseInt(taskDetails[1]) - 1;

                ui.printRemoveTask(tasklist.getTask(index), tasklist.getTaskSize() - 1);
                tasklist.deleteList(index);
                input = sc.nextLine();
            }
        }
        return tasklist;
    }

    /**
     * Check if the command given by the user is of the correct length
     *
     * @param command        command given by the user
     * @param expectedLength expected length of the command
     * @return false if it is a complete command, true if it is an incomplete command
     */
    private boolean checkIncompleteCommand(String command, int expectedLength) {
        try {
            if (command.split(" ").length < expectedLength) {
                throw new IncompleteCommandException("The description of the command is incomplete\n" +
                        "Please enter again.");
            }
            return false;
        } catch (IncompleteCommandException e) {
            System.out.println(e);
            return true;
        }
    }

    /**
     * Check if the index is within the size of the task list
     *
     * @param index    index of the task in the task list
     * @param taskSize size of the task list
     * @return false if it is within range, true if it is out of range
     */
    public boolean checkValidIndex(int index, int taskSize) {
        try {
            if (index < 1 || index > tasklist.getTaskSize()) {
                throw new IndexOffBoundException();
            }
            return false;
        } catch (IndexOffBoundException e) {
            System.out.println(e);
            return true;
        }
    }

    /**
     * Check if the command given by the user is a valid command.
     *
     * @param taskType type of the task to be executed
     * @return false if it is an invalid command, true if it is a valid command.
     */
    public boolean checkValidCommand(String taskType) {
        try {
            switch (taskType) {
            case "list":
                break;
            case "deadline":
                break;
            case "event":
                break;
            case "delete":
                break;
            case "bye":
                break;
            case "todo":
                break;
            case "done":
                break;
            default:
                throw new InvalidCommandException("I'm sorry, but I don't know what that means :-( \n" +
                        "Enter a command again:");
            }
            return false;
        } catch (InvalidCommandException e) {
            System.out.println(e);
            return true;
        }
    }
}
