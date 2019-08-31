package Duke.Parser;

import Duke.DukeException.IncompleteCommandException;
import Duke.DukeException.IndexOffBoundException;
import Duke.DukeException.InvalidCommandException;
import Duke.Task.*;
import Duke.Ui.Ui;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Parser class that deals with making sense of the user command.
 */
public class Parser {
    private TaskList taskList;
    private Scanner sc;
    private Ui ui;

    /**
     * Initialise Parser class with TaskList and Ui class
     *
     * @param taskList list of tasks
     * @param ui       object that deals with interaction with user
     */
    public Parser(TaskList taskList, Ui ui) {
        this.taskList = taskList;
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
            boolean isInvalid = checkValidCommand(taskType);
            boolean isInComplete;

            if (isInvalid) {
                while (isInvalid) {
                    input = sc.nextLine();
                    String[] newTaskDetails = input.split(" ");
                    String newTaskType = newTaskDetails[0].trim();
                    isInvalid = checkValidCommand(newTaskType);
                }
            }
            if (taskType.equals("list")) {
                ui.printTaskList(taskList);
                input = sc.nextLine();
            } else if (taskType.equals("done")) {
                isInComplete = checkIncompleteCommand(input, 2);
                if (isInComplete) {
                    input = sc.nextLine();
                    continue;
                }
                isInvalid = checkValidIndex(Integer.parseInt(taskDetails[1]));
                if (isInvalid) {
                    input = sc.nextLine();
                    continue;
                }
                int index = Integer.parseInt(taskDetails[1]) - 1;

                taskList.getTask(index).markAsDone();
                ui.printMarkedTask(taskList.getTask(index));
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
                taskList.addTask(new Deadline(msg[0].trim().substring(8).trim(), msg[1].trim()));

                int taskListSize = taskList.getTaskListSize();

                ui.printTaskAdded(taskList.getTask(taskListSize - 1), taskListSize);
                input = sc.nextLine();

            } else if (taskType.equals("event")) {
                String[] msg = input.split("/at");

                //Check whether the task description is complete
                isInComplete = checkIncompleteCommand(msg[0], 2);
                if (isInComplete) {
                    input = sc.nextLine();
                    continue;
                }
                // Check whether date and time format is complete
                isInComplete = checkIncompleteCommand(msg[1], 2);
                if (isInComplete) {
                    input = sc.nextLine();
                    continue;
                }
                taskList.addTask(new Event(msg[0].trim().substring(5).trim(), msg[1].trim()));

                int taskListSize = taskList.getTaskListSize();

                ui.printTaskAdded(taskList.getTask(taskListSize - 1), taskListSize);
                input = sc.nextLine();

            } else if (taskType.equals("todo")) {
                checkIncompleteCommand(input, 2);
                String toDo = input.substring(5);

                taskList.addTask(new ToDo(toDo));

                int taskListSize = taskList.getTaskListSize();

                ui.printTaskAdded(taskList.getTask(taskListSize - 1), taskListSize);
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
                isInvalid = checkValidIndex(Integer.parseInt(taskDetails[1]));
                if (isInvalid) {
                    input = sc.nextLine();
                    continue;
                }
                int index = Integer.parseInt(taskDetails[1]) - 1;
                int taskListSize = taskList.getTaskListSize();
                ui.printRemoveTask(taskList.getTask(index), taskList.getTaskListSize() - 1);
                taskList.deleteTask(index);
                input = sc.nextLine();
            } else if (taskType.equals("find")) {
                isInComplete = checkIncompleteCommand(input, 2);
                if (isInComplete) {
                    input = sc.nextLine();
                    continue;
                }
                String keyword = input.substring(5);
                //find all tasks that match keyword
                ArrayList<Task> matchingTasks = taskList.findMatchingTasks(keyword);
                //print all tasks that match keyword
                ui.printMatchingTasks(matchingTasks);
                input = sc.nextLine();
            }
        }
        return taskList;
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
     * @param index index of the task in the task list
     * @return false if it is within range, true if it is out of range
     */
    public boolean checkValidIndex(int index) {

        try {
            if (index < 1 || index > taskList.getTaskListSize()) {

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
            case "find":
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
