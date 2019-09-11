package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * storage class that deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String filePath;
    private Scanner sc;
    private ArrayList<Task> tasks;

    public Storage(String filePath) {
        this.filePath = filePath;
        tasks = new ArrayList<>();
    }

    /**
     * Load the list of tasks from duke.txt file,
     * if file could not be file, it will tell user
     * that file could not be found and an empty ArrayList of tasks
     *
     * @return an ArrayList that contains the tasks to do
     */
    public ArrayList<Task> load() {
        try {
            sc = new Scanner(new File(filePath));
            readFile();
        } catch (FileNotFoundException e) {
            System.out.println("could not find file");
        }
        return tasks;
    }

    /**
     * After loading from the file, this method will
     * determine the type of task and add them to the
     * task list.
     */
    public void readFile() {
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] taskDetails = line.split("[|]");
            String taskType = taskDetails[0].trim();
            String taskDescription;
            String dateTime;
            LocalDateTime localDateTime;
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
                taskDescription = taskDetails[2].trim();
                dateTime = taskDetails[3].trim();
                localDateTime = readLocalDateTime(dateTime);
                Task deadlineTask = new Deadline(taskDescription, localDateTime, dateTime);
                if (isDone) {
                    deadlineTask.markAsDone();
                }
                tasks.add(deadlineTask);
                break;
            case "E":
                taskDescription = taskDetails[2].trim();
                dateTime = taskDetails[3].trim();
                localDateTime = readLocalDateTime(dateTime);
                Task eventTask = new Event(taskDescription, localDateTime, dateTime);
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

    /**
     * Update file with a given format based on the type of task.
     * in the task list
     *
     * @param taskList list of tasks
     */
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
                    taskFormat = String.format("D | %s | %s | %s", done, task.getDescription(),
                            ((Deadline) task).getDateTime());
                    fileWriter.write(taskFormat + "\n");
                }
                if (task instanceof Event) {
                    taskFormat = String.format("E | %s | %s | %s", done, task.getDescription(),
                            ((Event) task).getDateTime());
                    fileWriter.write(taskFormat + "\n");
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("file not found");
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