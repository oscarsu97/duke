package duke.parser;

import duke.exception.IncompleteCommandException;
import duke.exception.IndexOffBoundException;
import duke.exception.InvalidCommandException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

import duke.ui.Ui;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * parser class that deals with making sense of the user command.
 */
public class Parser {
    private TaskList taskList;
    private Scanner sc;
    private Ui ui;
    private Storage storage;

    /**
     * Initialise parser class with TaskList and ui class.
     *
     * @param taskList list of tasks
     * @param ui       object that deals with interaction with user
     */
    public Parser(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        sc = new Scanner(System.in);
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Check if the user command input is valid,
     * otherwise it will prompt user to give a
     * valid input again.
     *
     * @return a list of the updated tasks
     */
    public String parse(String input) {

        String[] taskDetails = input.split(" ");
        String taskType = taskDetails[0].trim();

        String[] newTaskDetails = input.split(" ");
        String newTaskType = newTaskDetails[0].trim();
        String error = checkValidCommand(newTaskType);
        if (!error.equals("")) {
            return error;
        }

        if (taskType.equals("list")) {
            return ui.printTaskList(taskList);
        } else if (taskType.equals("done")) {
            error = checkIncompleteCommand(input, 2);
                /*if (isInComplete) {
                    continue;
                }*/
            if (!error.equals("")) {
                return error;
            }
            error = checkValidIndex(Integer.parseInt(taskDetails[1]));
                /*if (isInvalid) {
                    continue;
                }*/
            if (!error.equals("")) {
                return error;
            }
            int index = Integer.parseInt(taskDetails[1]) - 1;

            taskList.getTask(index).markAsDone();
            return ui.printMarkedTask(taskList.getTask(index));

        } else if (taskType.equals("deadline")) {

            String[] msg = input.split("/by");
            error = checkIncompleteCommand(msg[0], 2);
                /*if (isInComplete) {
                    continue;
                }*/
            if (!error.equals("")) {
                return error;
            }
            error = checkIncompleteCommand(msg[1], 2);
                /*if (isInComplete) {
                    continue;
                }*/
            if (!error.equals("")) {
                return error;
            }
            taskList.addTask(new Deadline(msg[0].trim().substring(8).trim(), msg[1].trim()));

            int taskListSize = taskList.getTaskListSize();

            return ui.printTaskAdded(taskList.getTask(taskListSize - 1), taskListSize);

        } else if (taskType.equals("event")) {
            String[] msg = input.split("/at");

            //Check whether the task description is complete
            error = checkIncompleteCommand(msg[0], 2);
                /*if (isInComplete) {
                    continue;
                }*/
            if (!error.equals("")) {
                return error;
            }
            // Check whether date and time format is complete
            error = checkIncompleteCommand(msg[1], 2);
                /*if (isInComplete) {
                    continue;
                }*/
            taskList.addTask(new Event(msg[0].trim().substring(5).trim(), msg[1].trim()));

            int taskListSize = taskList.getTaskListSize();

            return ui.printTaskAdded(taskList.getTask(taskListSize - 1), taskListSize);

        } else if (taskType.equals("todo")) {
            error = checkIncompleteCommand(input, 2);
            if (!error.equals("")) {
                return error;
            }
            String toDo = input.substring(5);

            taskList.addTask(new ToDo(toDo));

            int taskListSize = taskList.getTaskListSize();

            return ui.printTaskAdded(taskList.getTask(taskListSize - 1), taskListSize);

        } else if (input.equals("bye")) {
            storage.updateFile(taskList.getTaskList());
            return ui.showGoodbye();

        } else if (taskType.equals("delete")) {
            error = checkIncompleteCommand(input, 2);
                /*if (isInComplete) {
                    continue;
                }*/
            if (!error.equals("")) {
                return error;
            }
            error = checkValidIndex(Integer.parseInt(taskDetails[1]));
                /*if (isInvalid) {
                    continue;
                }*/
            if (!error.equals("")) {
                return error;
            }
            int index = Integer.parseInt(taskDetails[1]) - 1;
            int taskListSize = taskList.getTaskListSize();
            ui.printRemoveTask(taskList.getTask(index), taskList.getTaskListSize() - 1);
            taskList.deleteTask(index);
        } else if (taskType.equals("find")) {
            error = checkIncompleteCommand(input, 2);
                /*if (isInComplete) {
                    continue;
                }*/
            if (!error.equals("")) {
                return error;
            }
            String keyword = input.substring(5);
            //find all tasks that match keyword
            ArrayList<Task> matchingTasks = taskList.findMatchingTasks(keyword);
            //print all tasks that match keyword
            return ui.printMatchingTasks(matchingTasks);
        }
        return "I'm sorry, but I don't know what that means :-( \n"
                + "Enter a command again:";
    }


    /**
     * Check if the command given by the user is of the correct length.
     *
     * @param command        command given by the user
     * @param expectedLength expected length of the command
     * @return false if it is a complete command, true if it is an incomplete command
     */
    private String checkIncompleteCommand(String command, int expectedLength) {
        try {
            if (command.split(" ").length < expectedLength) {
                throw new IncompleteCommandException("The description of the command is incomplete\n"
                        + "Please enter again.");
            }
            return "";
        } catch (IncompleteCommandException e) {
            //    System.out.println(e);
            return e.toString();
        }
    }

    /**
     * Check if the index is within the size of the task list.
     *
     * @param index index of the task in the task list
     * @return false if it is within range, true if it is out of range
     */
    public String checkValidIndex(int index) {

        try {
            if (index < 1 || index > taskList.getTaskListSize()) {
                throw new IndexOffBoundException();
            }
            return "";
        } catch (IndexOffBoundException e) {
            return e.toString();
        }
    }

    /**
     * Check if the command given by the user is a valid command.
     *
     * @param taskType type of the task to be executed
     * @return false if it is an invalid command, true if it is a valid command.
     */
    public String checkValidCommand(String taskType) {
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
                throw new InvalidCommandException("I'm sorry, but I don't know what that means :-( \n"
                        + "Enter a command again:");
            }
            return "";
        } catch (InvalidCommandException e) {
            return e.toString();
        }
    }
}
