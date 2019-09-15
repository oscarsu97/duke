package duke.command;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import duke.task.TaskList;
import duke.task.Task;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * StatisticCommand that handles the printing of the report
 * of the tasks in task list.
 */
public class StatisticCommand extends Command {

    /**
     * Gets the statistic report on the task list
     * such as number of deadline tasks,
     * number of event tasks, number of To Do tasks
     * and percentage of work done.
     *
     * @param taskList list of tasks
     * @param ui       Ui class that handles the interaction with user
     * @param storage  storage file that stores the task list
     * @return string representing the report on the task list
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> listOfTasks = taskList.getTaskList();

        int numOfDeadlineTask = countDeadLineTasks(listOfTasks);
        int numOfEventTask = countEventTasks(listOfTasks);
        int numOfToDoTask = countToDoTasks(listOfTasks);
        int numOfDoneTask = countDoneTasks(listOfTasks);
        int totalTasks = listOfTasks.size();
        double percentageDone = ((double) numOfDoneTask / totalTasks) * 100;


        return String.format("Number of deadline tasks: %s\n"
                        + "Number of Event tasks: %s\n"
                        + "Number of ToDo tasks: %s\n"
                        + "Percentage of work done: %.2f%%",
                numOfDeadlineTask, numOfEventTask, numOfToDoTask, percentageDone);
    }


    /**
     * Counts the number of task that is done type.
     *
     * @param tasks tasks in the task list
     * @return total number of task done
     */
    private int countDoneTasks(ArrayList<Task> tasks) {
        int count = 0;
        for (Task task : tasks) {
            if (task.isDone()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Counts the number of task that is event type.
     *
     * @param tasks tasks in the task list
     * @return total number of event tasks
     */
    private int countEventTasks(ArrayList<Task> tasks) {
        int count = 0;

        for (Task task : tasks) {
            if (task instanceof Event) {
                count++;
            }
        }
        return count;
    }

    /**
     * Counts the number of task that is deadline type.
     *
     * @param tasks tasks in the task list
     * @return total number of deadline tasks
     */
    private int countDeadLineTasks(ArrayList<Task> tasks) {
        int count = 0;

        for (Task task : tasks) {
            if (task instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    /**
     * Counts the number of task that is ToDo type.
     *
     * @param tasks task in the task list
     * @return total number of ToDo tasks
     */
    private int countToDoTasks(ArrayList<Task> tasks) {
        int count = 0;

        for (Task task : tasks) {
            if (task instanceof ToDo) {
                count++;
            }
        }
        return count;
    }
}
