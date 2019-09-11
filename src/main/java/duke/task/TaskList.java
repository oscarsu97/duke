package duke.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks that needs to be do
 * and this class is able to add or delete tasks
 * in the list.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * initialise TaskList with an empty ArrayList.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * initialise TaskList with a given task list.
     *
     * @param taskList list of tasks
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Add a new task into the task list.
     *
     * @param task task to be added
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Delete a task from the task list.
     *
     * @param index the task index in the task list
     */
    public void deleteTask(int index) {
        taskList.remove(index);
    }

    /**
     * Returns the list of the task.
     *
     * @return list of task
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Returns the size of the list of task.
     *
     * @return int value of the size of the list
     */
    public int getTaskListSize() {
        return taskList.size();
    }

    /**
     * Get a specified task from the list.
     *
     * @param index the index of the task in the task list
     * @return the specified task
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Find task that matches the keyword.
     *
     * @param keyword the string to look for
     * @return ArrayList that contain all tasks that match the keyword
     */
    public ArrayList<Task> findMatchingTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task instanceof Deadline) {
                Deadline deadlineTask = (Deadline) task;
                if (deadlineTask.getDateTime().contains(keyword)) {
                    matchingTasks.add(deadlineTask);
                }
            } else if (task instanceof Event) {
                Event eventTask = (Event) task;
                if (eventTask.getDateTime().contains(keyword)) {
                    matchingTasks.add(eventTask);
                }
            }
            //Check if Deadline, Event or To do task's description contains keyword
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }


}
