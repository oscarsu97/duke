package duke.storage;

import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
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
 * Storage class that handles the loading of tasks from storage file and saving tasks into storage file.
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
     * Loads the list of tasks from storage file,
     * if file could not be found, it will create a data folder
     * with an empty duke.txt inside that folder.
     *
     * @return an ArrayList that contains the list of tasks stored
     */
    public ArrayList<Task> load() throws DukeException {
        try {

            File directory = new File("data");
            directory.mkdir();              // creates folder if "data" folder cannot be found
            File f = new File(filePath);
            f.createNewFile();              // creates duke.txt if it does not exist
            sc = new Scanner(f);
            readFile();
        } catch (FileNotFoundException e) {
            throw new DukeException("file not found");
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
        return tasks;
    }

    /**
     * After loading from the file, this method will
     * determine the type of task and add them to the
     * task list.
     */
    public void readFile() throws InvalidCommandException {
        while (sc.hasNext()) {
            String data = sc.nextLine();
            String[] taskDetails = data.split("[|]");
            String taskType = taskDetails[0].trim();

            switch (taskType) {
            case "T":
                addToDoTask(taskDetails);
                break;
            case "D":
                addDeadlineTask(taskDetails);
                break;
            case "E":
                addEventTask(taskDetails);

                break;
            default:
                throw new InvalidCommandException("command of this file cannot be recognised");
            }
        }
    }

    /**
     * Adds ToDo task into task list.
     *
     * @param taskDetails contains task description
     */
    private void addToDoTask(String[] taskDetails) {
        Task toDoTask = new ToDo(taskDetails[2].trim());
        boolean isDone = taskDetails[1].trim().equals("1");
        if (isDone) {
            toDoTask.markAsDone();
        }
        tasks.add(toDoTask);
    }

    /**
     * Adds Deadline task into task list and
     * converts string date time into LocalDateTime.
     *
     * @param taskDetails contains task description, date and time
     */
    private void addDeadlineTask(String[] taskDetails) {
        String taskDescription = taskDetails[2].trim();
        LocalDateTime dateTime = readLocalDateTime(taskDetails[3].trim());

        Task deadlineTask = new Deadline(taskDescription, dateTime);
        boolean isDone = taskDetails[1].trim().equals("1");
        if (isDone) {
            deadlineTask.markAsDone();
        }
        tasks.add(deadlineTask);
    }

    /**
     * Adds Event task into task list and
     * converts string date time into LocalDateTime.
     *
     * @param taskDetails contains task description, date and time
     */
    private void addEventTask(String[] taskDetails) {
        String taskDescription = taskDetails[2].trim();
        LocalDateTime dateTime = readLocalDateTime(taskDetails[3].trim());

        Task eventTask = new Event(taskDescription, dateTime);
        boolean isDone = taskDetails[1].trim().equals("1");
        if (isDone) {
            eventTask.markAsDone();
        }
        tasks.add(eventTask);
    }

    /**
     * Updates file with a given format based on the type of task
     * in the task list.
     *
     * @param taskList list of tasks to update into storage file
     */
    public void updateFile(ArrayList<Task> taskList) {
        try {
            FileWriter fileWriter = new FileWriter(filePath, false);
            for (Task task : taskList) {
                String taskFormat = getTaskFormat(task);
                fileWriter.write(taskFormat + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("file not found");
        }
    }

    /**
     * Converts task into proper string format before storing it.
     *
     * @param task the task to convert into string format
     * @return string format of task
     */
    private String getTaskFormat(Task task) {
        String taskType;
        String isDone = task.isDone() ? "1" : "0";
        if (task instanceof ToDo) {
            taskType = "T";
            return String.format("%s | %s | %s", taskType, isDone, task.getDescription());
        } else if (task instanceof Deadline) {
            taskType = "D";
            return String.format("%s | %s | %s | %s",
                    taskType, isDone, task.getDescription(), ((Deadline) task).getDateTime());
        } else {
            taskType = "E"; // Event Task
            return String.format("%s | %s | %s | %s",
                    taskType, isDone, task.getDescription(), ((Event) task).getDateTime());
        }
    }

    /**
     * Converts string value of date and time
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