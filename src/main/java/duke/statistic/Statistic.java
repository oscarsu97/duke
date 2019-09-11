package duke.statistic;

import duke.task.*;

import java.util.ArrayList;

/**
 * Statistics class that gives a report on the task list
 */
public class Statistic {

    /**
     * Initialises Statistic class.
     */
    public Statistic() {
    }

    /**
     * Get the statistic report on the task list
     * such as number of deadline tasks,
     * number of event tasks, number of To Do tasks
     * and percentage of work done.
     *
     * @param taskList list of tasks
     * @return string representing the report on the task list
     */
    public String getStats(TaskList taskList) {
        ArrayList<Task> listOfTasks = taskList.getTaskList();

        int numOfDeadlineTask = countDeadLineTasks(listOfTasks);
        int numOfEventTask = countEventTasks(listOfTasks);
        int numOfToDoTask = countToDoTasks(listOfTasks);
        int numOfDoneTask = countDoneTasks(listOfTasks);
        int totalTasks = listOfTasks.size();
        double percentageDone = ((double) numOfDoneTask / totalTasks) * 100;


        return String.format("Number of deadline tasks: %s\n" +
                        "Number of Event tasks: %s\n" +
                        "Number of ToDo tasks: %s\n" +
                        "Percentage of work done: %.2f%%",
                numOfDeadlineTask, numOfEventTask, numOfToDoTask, percentageDone);
    }

    /**
     * Count the number of task that is done.
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
     * Count the number of task that is event.
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
     * Count the number of task that is deadline.
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
     * Count the number of task that is To Do.
     *
     * @param tasks task in the task list
     * @return total number of To Do tasks
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
