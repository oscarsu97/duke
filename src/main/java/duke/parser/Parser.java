package duke.parser;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.StatisticCommand;
import duke.exception.DukeException;
import duke.exception.IncompleteCommandException;
import duke.exception.IndexOffBoundException;
import duke.exception.InvalidCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.ToDo;
import java.time.LocalDateTime;

/**
 * Deals with making sense of the user command, and
 * checks for any wrong input given by user.
 */
public class Parser {
    private TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Checks if the user input is valid and
     * return specific command based on the command given by user
     * if it is valid.
     *
     * @param input given by the user
     * @return specific command class based on user's command
     */
    public Command parse(String input) throws DukeException {

        handleWrongUserInput(input);
        return getCommand(input);
    }

    /**
     * Gets the specified command based on command user.
     *
     * @return specific command according to command given
     */
    private Command getCommand(String input) throws InvalidCommandException {
        String[] taskDetails = input.split(" ");
        String command = taskDetails[0].trim();

        //Executing command based on command type
        switch (command) {
        case "todo":
            return prepareToDoCommand(input);
        case "deadline":
            return prepareDeadlineCommand(input);
        case "event":
            return prepareEventCommand(input);
        case "delete":
            return prepareDeleteCommand(input);
        case "done":
            return prepareDoneCommand(input);
        case "find":
            return prepareFindCommand(input);
        case "list":
            return prepareListCommand();
        case "bye":
            return prepareByeCommand();
        case "statistics":
            return prepareStatisticCommand();
        default:
            assert false;
            // code will not reach here given that we have checked that it is a valid command
            throw new InvalidCommandException("Command is invalid, please enter a valid command.");
        }
    }

    /**
     * Prepares AddCommand with ToDo task added
     * so that it can be executed on later on.
     *
     * @return AddCommand
     */
    private Command prepareToDoCommand(String input) {
        String taskDescription = input.substring(4).trim();
        return new AddCommand(new ToDo(taskDescription));
    }

    /**
     * Prepare AddCommand with Deadline task added
     * so that it can be executed later on.
     *
     * @return AddCommand
     */
    private Command prepareDeadlineCommand(String input) {
        String[] deadlineDetails = input.split("/by");
        String taskDescription = deadlineDetails[0].trim().substring(8).trim();
        LocalDateTime localDateTime = readLocalDateTime(deadlineDetails[1].trim());
        return new AddCommand(new Deadline(taskDescription, localDateTime));
    }

    /**
     * Prepares AddCommand with Event task added
     * so that it can be executed on later on.
     *
     * @return AddCommand
     */
    private Command prepareEventCommand(String input) {
        String[] eventDetails = input.split("/at");
        String taskDescription = eventDetails[0].trim().substring(5).trim();
        LocalDateTime localDateTime = readLocalDateTime(eventDetails[1].trim());
        return new AddCommand(new Event(taskDescription, localDateTime));
    }

    /**
     * Prepares DeleteCommand with specified index added
     * so that DeleteCommand knows which task to delete from
     * task list later on.
     *
     * @return DeleteCommand
     */
    private Command prepareDeleteCommand(String input) {
        String[] taskDetails = input.split(" ");
        int index = Integer.parseInt(taskDetails[1]) - 1;
        return new DeleteCommand(index);
    }

    /**
     * Prepares DoneCommand with specified index added
     * so that DoneCommand knows which task to mark as done
     * in task list later on.
     *
     * @return DoneCommand
     */
    private Command prepareDoneCommand(String input) {
        String[] taskDetails = input.split(" ");
        int index = Integer.parseInt(taskDetails[1]) - 1;
        return new DoneCommand(index);
    }

    /**
     * Prepares FindCommand with string keyword added
     * so that FindCommand can find task with the
     * matching keywords.
     *
     * @return FindCommand
     */
    private Command prepareFindCommand(String input) {
        String keyword = input.substring(5);
        return new FindCommand(keyword);
    }

    private Command prepareListCommand() {
        return new ListCommand();
    }

    private Command prepareByeCommand() {
        return new ByeCommand();
    }

    private Command prepareStatisticCommand() {
        return new StatisticCommand();
    }

    /**
     * Handle possible error in the input given by the user.
     * It checks whether command is valid and then checks
     * whether the command is complete.
     * <p>
     * If command is 'delete' or 'done', it checks whether
     * task index is within the size of task list.
     * </p>
     */
    private void handleWrongUserInput(String input) throws DukeException {

        checkValidCommand(input);
        checkCompleteCommand(input);
        if (input.startsWith("delete") || input.startsWith("done")) {
            checkCorrectTaskIndex(input);
        }
    }

    /**
     * Checks whether the input has fulfilled the minimum length.
     * <p>For deadline and event command,
     * it checks whether it has the correct format and
     * correct separating word eg. '/at'.
     * </p>
     */
    private void checkCompleteCommand(String input) throws IncompleteCommandException {
        if (input.startsWith("deadline")) {

            checkMinimumSize(input, 5);
            checkSeparatingWord(input, "/by");

            String[] taskDetails = input.split("/by");
            checkCompleteDateTimeFormat(taskDetails[1].trim());

        } else if (input.startsWith("event")) {

            checkMinimumSize(input, 5);
            checkSeparatingWord(input, "/at");

            String[] taskDetails = input.split("/at");
            checkCompleteDateTimeFormat(taskDetails[1].trim());

        } else if (input.startsWith("done") || input.startsWith("delete")) {
            checkMinimumSize(input, 2);
        }
    }

    /**
     * Checks whether the input has fulfilled the minimum length
     * for specific command.
     *
     * @param expectedLength length expected for specified command type
     */
    private void checkMinimumSize(String input, int expectedLength) throws IncompleteCommandException {

        String[] taskDetails = input.split(" ");
        if (taskDetails.length < expectedLength) {
            throw new IncompleteCommandException("Size of input is below expected length,"
                    + " please have a input size of " + expectedLength);
        }
    }

    /**
     * Checks if it has separator such as '/by' or '/at'
     * for Deadline or Event class.
     */
    private void checkSeparatingWord(String input, String separator) throws IncompleteCommandException {
        if (!input.contains(separator)) {
            throw new IncompleteCommandException(String.format("Input does not contains '%s',"
                    + " please have a '%s' in your input", separator, separator));
        }
    }

    /**
     * Checks if it has date and time
     * and checks if they are in correct format.
     */
    private void checkCompleteDateTimeFormat(String dateTime) throws IncompleteCommandException {

        checkMinimumSize(dateTime, 2);
        checkCorrectFormat(dateTime);
    }

    /**
     * Checks if the task chosen is within the range of
     * the task list size.
     */
    private void checkCorrectTaskIndex(String input) throws IndexOffBoundException {
        String[] taskDetails = input.split(" ");
        int index = Integer.parseInt(taskDetails[1]);
        if (index > taskList.getTaskListSize()) {
            throw new IndexOffBoundException("The task you are getting is out of range,"
                    + " please enter a range from 1 to " + taskList.getTaskListSize());
        }
    }

    /**
     * Check if the command given by the input is one of the recognised command.
     */
    public void checkValidCommand(String input) throws InvalidCommandException {
        String command = input.split(" ")[0];

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
            throw new InvalidCommandException("I'm sorry, but I don't know what that means :-(\n"
                    + "please enter a valid command:");
        }
    }

    /**
     * Checks if the date is of the format dd/MM/YYYY HHmm.
     * Checks if day entered is within range 1 to 31.
     * Checks if month entered is within range 1 to 12.
     * Checks  if timing entered is within 0000 to 2359.
     */
    private void checkCorrectFormat(String dateTime) throws IncompleteCommandException {
        System.out.println(dateTime);
        String[] dateAndTime = dateTime.split(" ");
        System.out.println(dateAndTime[0]);
        String[] date = dateAndTime[0].split("/");
        if (!(date.length == 3)) {
            throw new IncompleteCommandException("Date format is wrong, please enter in this format dd/MM/YYYY HHmm.");
        }
        int time = Integer.parseInt(dateAndTime[1]);
        int dayOfMonth = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int hour = time / 100;
        int min = time % 100;
        if (dayOfMonth > 31) {
            throw new IncompleteCommandException("Invalid day entered, please enter a range from 1 to 31.");
        }
        if (month > 12) {
            throw new IncompleteCommandException("Invalid month entered, please enter a range from 1 to 12.");
        }
        if (hour >= 24 || min >= 60) {
            throw new IncompleteCommandException("Invalid timing entered, please enter a range from 0000 to 2359.");
        }
    }

    /**
     * Convert string value of date and time
     * into LocalDateTime format.
     *
     * @param dateTime string representing the date and time
     * @return LocalDateTime format of the date and time
     */
    private LocalDateTime readLocalDateTime(String dateTime) {
        String[] dateAndTime = dateTime.split(" ");
        String[] date = dateAndTime[0].split("/");
        int time = Integer.parseInt(dateAndTime[1]);
        int dayOfMonth = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int year = Integer.parseInt(date[2]);
        int hour = time / 100;
        int min = time % 100;

        return LocalDateTime.of(year, month, dayOfMonth, hour, min);
    }
}
