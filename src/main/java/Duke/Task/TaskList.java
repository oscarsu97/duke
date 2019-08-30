package Duke.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(){
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList){
        this.taskList = taskList;
    }

    public void addTask(Task task ){
        taskList.add(task);
    }

    public void deleteList(int index){
        taskList.remove(index);
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public int getTaskSize(){
        return taskList.size();
    }

    public Task getTask(int index){
        return taskList.get(index);
    }
}
