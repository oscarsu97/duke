import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private Scanner sc;
    private ArrayList<Task> tasks;

    public Storage(String filePath) {
        this.filePath = filePath;
        tasks = new ArrayList<>();
    }

    public ArrayList<Task> load() {
        try {
            sc = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            System.out.println("could not find file");
        }
        readFile();
        return tasks;
    }

    public void readFile() {
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] taskDetails = line.split("[|]");
            String taskType = taskDetails[0].trim();
            boolean isDone = false;
            if (taskDetails[1].trim().equals("1")) {
                isDone = true;
            }

            switch (taskType) {
            case "T":
                Task toDoTask = new ToDo(taskDetails[2].trim());
                if (isDone) {
                    toDoTask.markAsDone();
                }
                tasks.add(toDoTask);
                break;
            case "D":
                Task deadlineTask = new Deadline(taskDetails[2].trim(), taskDetails[3].trim());
                if (isDone) {
                    deadlineTask.markAsDone();
                }
                tasks.add(deadlineTask);
                break;
            case "E":
                Task eventTask = new Event(taskDetails[2].trim(), taskDetails[3].trim());
                if (isDone) {
                    eventTask.markAsDone();
                }
                tasks.add(eventTask);
                break;
            default:
                System.out.println("invalid format of data input");
                break;
            }
        }
    }

    public void updateFile(ArrayList<Task> taskList) {
        try {
            FileWriter fileWriter = new FileWriter(filePath, false);
            String taskFormat;
            String done;

            for (Task task : taskList) {
                done = task.isDone() ? "1" : "0";
                if (task instanceof ToDo) {
                    taskFormat = String.format("T | %s | %s", done, task.getDescription());
                    fileWriter.write(taskFormat + "\n");
                }
                if (task instanceof Deadline) {
                    taskFormat = String.format("D | %s | %s | %s", done, task.getDescription(), ((Deadline) task).getDateTime());
                    fileWriter.write(taskFormat + "\n");
                }
                if (task instanceof Event) {
                    taskFormat = String.format("E | %s | %s | %s", done, task.getDescription(), ((Event) task).getDateTime());
                    fileWriter.write(taskFormat + "\n");
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("file not found");
        }
    }
}