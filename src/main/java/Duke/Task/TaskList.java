package Duke.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteList(int index) {
        taskList.remove(index);
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

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

    public int getTaskSize() {
        return taskList.size();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }
}
