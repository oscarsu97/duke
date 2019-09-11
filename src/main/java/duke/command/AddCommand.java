package duke.command;

import duke.task.*;
import duke.ui.Ui;

import java.time.LocalDateTime;

/**
 * AddCommand class that add task into the task list
 */
public class AddCommand extends Command {

    /**
     * Initialises AddCommand class.
     */
    public AddCommand() {
        super();
    }

    /**
     * Execute task based on the command type and
     * then add them into task list
     * @param input user input
     * @param commandType type of command given
     * @param taskList list of tasks to be done
     * @param ui Ui class that takes care of interacting with user
     * @return string that represents the task added message
     */
    public String execute(String input, String commandType, TaskList taskList, Ui ui) {
        String response;
        if (commandType.equals("todo")) {
            response = executeTodoCommand(input, taskList, ui);
        } else if (commandType.equals("event")) {
            response = executeEventCommand(input, taskList, ui);
        } else {
            response = executeDeadlineCommand(input, taskList, ui);
        }
        return response;
    }

    /**
     * Add deadline task into task list
     * @param input user input
     * @param taskList list of tasks to be done
     * @param ui Ui class that takes care of interacting with user
     * @return string that represents the task added message
     */
    private String executeDeadlineCommand(String input, TaskList taskList, Ui ui) {
        String[] deadlineDetails = input.split("/by");
        String taskDescription = deadlineDetails[0].trim().substring(8).trim();
        String dateTime = deadlineDetails[1].trim();
        LocalDateTime localDateTime = readLocalDateTime(dateTime);

        taskList.addTask(new Deadline(taskDescription, localDateTime, dateTime));
        return ui.printTaskAdded(taskList);
    }

    /**
     * Add event task into task list
     * @param input user input
     * @param taskList list of tasks to be done
     * @param ui Ui class that takes care of interacting with user
     * @return string that represents the task added message
     */
    private String executeEventCommand(String input, TaskList taskList, Ui ui) {
        String[] eventDetails = input.split("/at");
        String taskDescription = eventDetails[0].trim().substring(5).trim();
        String dateTime = eventDetails[1].trim();
        LocalDateTime localDateTime = readLocalDateTime(dateTime);

        taskList.addTask(new Event(taskDescription, localDateTime, dateTime));
        return ui.printTaskAdded(taskList);
    }

    /**
     *  Add To Do task into task list
     * @param input user input
     * @param taskList list of tasks to be done
     * @param ui Ui class that takes care of interacting with user
     * @return string that represents the task added message
     */
    private String executeTodoCommand(String input, TaskList taskList, Ui ui) {
        String toDo = input.substring(5);
        taskList.addTask(new ToDo(toDo));
        return ui.printTaskAdded(taskList);
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
