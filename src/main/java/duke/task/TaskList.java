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
     * Initialises TaskList with an empty ArrayList.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Initialises TaskList with a given task list.
     *
     * @param taskList list of tasks
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a new task into the task list.
     *
     * @param task task to be added
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index the task index of the task to be deleted from the task list
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
     * @return integer value of the size of the list
     */
    public int getTaskListSize() {
        return taskList.size();
    }

    /**
     * Gets a specified task from the list.
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
     * Finds task that matches the keyword and adds them into
     * an ArrayList containing all tasks that matches the keyword.
     *
     * @param keyword string value to match to when finding task
     * @return ArrayList that contains all tasks that matches the keyword
     */
    public ArrayList<Task> findMatchingTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (checkKeyWord(task, keyword)) {
                addToMatchingTasks(task, matchingTasks);
            }
        }
        return matchingTasks;
    }

    private void addToMatchingTasks(Task task, ArrayList<Task> matchingTasks) {
        matchingTasks.add(task);
    }

    /**
     * Checks if the task details has detail that matches with the keyword.
     *
     * @param task    task to check for matching keyword
     * @param keyword keyword to match
     * @return boolean value that determine if a keyword is contained in the task details
     */
    private boolean checkKeyWord(Task task, String keyword) {
        boolean hasKeyWord = false;
        if (task instanceof Deadline) {
            hasKeyWord = ((Deadline) task).getDateTime().contains(keyword);
        }
        if (task instanceof Event) {
            hasKeyWord = ((Event) task).getDateTime().contains(keyword);
        }
        if (hasKeyWord) {
            return true;
        }
        //Check if Deadline, Event or ToDo task's description contains keyword
        hasKeyWord = task.getDescription().contains(keyword);
        return hasKeyWord;
    }
}
