package duke.parser;

import duke.command.*;
import duke.exception.IncompleteCommandException;
import duke.exception.IndexOffBoundException;
import duke.exception.InvalidCommandException;
import duke.statistic.Statistic;
import duke.storage.Storage;
import duke.task.TaskList;

import duke.ui.Ui;

/**
 * Parser class that deals with making sense of the user command.
 */
public class Parser {
    private TaskList taskList;
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

        String message = handleWrongUserInput(input);

        if (hasError(message)) {
            return message;
        } else {
            String[] taskDetails = input.split(" ");
            String command = taskDetails[0].trim();
            String response;

            //Executing command based on command type
            switch (command) {
            case "list":

                Command list = new ListCommand();
                response = ((ListCommand) list).execute(taskList, ui);
                return response;

            case "todo":

                Command todo = new AddCommand();
                response = ((AddCommand) todo).execute(input, command, taskList, ui);
                return response;

            case "deadline":

                Command deadline = new AddCommand();
                response = ((AddCommand) deadline).execute(input, command, taskList, ui);
                return response;

            case "event":

                Command event = new AddCommand();
                response = ((AddCommand) event).execute(input, command, taskList, ui);
                return response;

            case "delete":

                Command delete = new DeleteCommand();
                response = ((DeleteCommand) delete).execute(taskDetails, taskList, ui);
                return response;

            case "done":

                Command done = new DoneCommand();
                response = ((DoneCommand) done).execute(taskDetails, taskList, ui);
                return response;

            case "find":

                Command find = new FindCommand();
                response = ((FindCommand) find).execute(taskList, input, ui);
                return response;

            case "bye":

                Command bye = new ByeCommand();
                response = ((ByeCommand) bye).execute(ui, storage, taskList);
                return response;

            case "statistics":
                Statistic statistic = new Statistic();
                response = statistic.getStats(taskList);
                return response;

            default:
                assert false;
                // code will not come here given that we have checked that it is a valid command
                return "I'm sorry, but I don't know what that means :-( \n"
                        + "Enter a command again:";
            }
        }
    }

    /**
     * Handle possible error in the input given by the user.
     *
     * @param input input given by the user
     * @return error string if there exists
     */
    private String handleWrongUserInput(String input) {
        String[] taskDetails = input.split(" ");
        String command = taskDetails[0].trim();
        String message = "";
        String taskDescription;

        try {
            boolean isValidCommand = checkValidCommand(command);
            if (!isValidCommand) {
                throw new InvalidCommandException("I'm sorry, but I don't know what that means :-( \n"
                        + "Enter a command again:");
            }
        } catch (InvalidCommandException error) {
            return error.toString();
        }

        switch (command) {
        case "deadline":
            String[] deadlineDetails = input.split("/by");
            taskDescription = deadlineDetails[0];
            // check whether deadline command is complete
            message = checkIncompleteCommand(deadlineDetails.length, 2);
            if (hasError(message)) {
                return message;
            }
            // Checks whether deadline task description is complete
            message = checkIncompleteCommand(taskDescription, 2);
            if (hasError(message)) {
                return message;
            }
            //Checks whether there is date and time input
            message = checkIncompleteCommand(deadlineDetails[1], 2);
            if (hasError(message)) {
                return message;
            } else {
                return message;
            }
        case "event":
            String[] eventDetails = input.split("/at");
            // check whether event command is complete
            message = checkIncompleteCommand(eventDetails.length, 2);
            if (hasError(message)) {
                return message;
            }
            //Check whether the task description is complete
            message = checkIncompleteCommand(eventDetails[0], 2);
            if (hasError(message)) {
                return message;
            }
            // Check whether date and time format is complete
            message = checkIncompleteCommand(eventDetails[1], 2);
            if (hasError(message)) {
                return message;
            }
            break;
        case "delete":
            message = checkIncompleteCommand(input, 2);
            if (hasError(message)) {
                return message;
            }
            //check whether the task to be deleted is within the task list
            message = checkValidIndex(Integer.parseInt(taskDetails[1]));
            if (hasError(message)) {
                return message;
            }
            break;
        case "todo":
            message = checkIncompleteCommand(input, 2);
            if (hasError(message)) {
                return message;
            }
            break;
        case "done":
            message = checkIncompleteCommand(input, 2);
            if (hasError(message)) {
                return message;
            }
            //check whether the task to be marked done is within task list
            message = checkValidIndex(Integer.parseInt(taskDetails[1]));
            if (hasError(message)) {
                return message;
            }
            break;
        case "find":
            message = checkIncompleteCommand(input, 2);
            if (hasError(message)) {
                return message;
            }
            break;
        default:
            // will not reach here given that we have check that input has a valid command
        }
        return message;
    }

    /**
     * Check if the message contains error message.
     *
     * @param message the message describing the error if any
     * @return a boolean variable depending on the existence of error message
     */
    private boolean hasError(String message) {
        return !message.equals("");
    }


    /**
     * Check if the command given by the user is of the correct length.
     *
     * @param command        command given by the user
     * @param expectedLength expected length of the command
     * @return false if it is a complete command, true if it is an incomplete command
     */
    private String checkIncompleteCommand(String command, int expectedLength) {
        assert expectedLength > 0 : "Expected length should not be negative";
        try {
            if (command.split(" ").length < expectedLength) {
                throw new IncompleteCommandException("The description of the command is incomplete\n"
                        + "Please enter again.");
            }
            return "";
        } catch (IncompleteCommandException e) {
            return e.toString();
        }
    }

    /**
     * Check if the command given by the user is of the correct length.
     *
     * @param commandLength  length of given by the user
     * @param expectedLength expected length of the command
     * @return false if it is a complete command, true if it is an incomplete command
     */
    private String checkIncompleteCommand(int commandLength, int expectedLength) {
        assert expectedLength > 0 : "Expected length should not be negative";
        try {
            if (commandLength < expectedLength) {
                throw new IncompleteCommandException("The description of the command is incomplete\n"
                        + "Please enter again.");
            }
            return "";
        } catch (IncompleteCommandException e) {
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
     * @param command type of the task to be executed
     * @return false if it is an invalid command, true if it is a valid command.
     */
    public boolean checkValidCommand(String command) {

        switch (command) {
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
        case "statistics":
            break;
        default:
            return false;
        }
        return true;
    }

}
