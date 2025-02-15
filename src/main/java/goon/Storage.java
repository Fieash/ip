package goon;

import goon.tasks.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Handles storage of tasks
 */
public class Storage {
    private final String filePath;

    /**
     * Creates the Storage Object.
     * @param filepath File Path to load from and save to
     */
    public Storage(String filepath) {
        assert filepath != null : "file path cannot be null!";
        this.filePath = filepath;
    }

    /**
     * Creates the Storage Object. Sets the file path to load from and save to
     * @param newTask object to be added to the file
     */
    public void addTaskToFile(Task newTask) throws GoonException {
        assert newTask != null : "task cannot be null!";
        try {
            FileWriter fw = new FileWriter(this.filePath, true);
            fw.append(newTask.toFileFormat());
            fw.close();
        } catch (IOException e) {
            System.out.println("G00n3r, an error occured while writing to the file.");
            throw new GoonException("at addTaskToFile");
        }
    }

    /**
     * parse the user input into a LocalDate
     * @param input string representation of the date
     * @return LocalDate representation of the user's input
     */
    public static LocalDate parseDate(String input) {
        assert input != null : "date input string cannot be null!";
        try {
            String date = input.replaceAll("\\s+","");
            return LocalDate.parse(date);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("date not in correct format");
            e.printStackTrace();
        }
        return LocalDate.parse("1111-11-11");
    }

    /**
     * Loads the list of tasks from the file storage into the ArrayList
     * @param taskList to populate with the tasks found in storage
     * @throws GoonException if there are issues reading from the file
     */
    public void load(TaskList taskList) throws GoonException {
        File savedTasks = new File(filePath);
        Scanner readTasks = null;
        try {
            readTasks = new Scanner(savedTasks);
            while(readTasks.hasNextLine()) {
                String line = readTasks.nextLine();
                String[] tasks = line.split("\\|");
                //System.out.println(goon.tasks[0] + "." + goon.tasks[1] + "." + goon.tasks[2]);

                if(tasks[0].contains("T")) { //todo case
                    ToDo newTodo = new ToDo(tasks[2].substring(1));
                    taskList.addTask(newTodo);

                } else if (tasks[0].contains("E")) { //event case
                    String desc = tasks[2].split("/from")[0].substring(1);
                    String from = tasks[2].split("/from")[1].split("/to")[0];
                    String to = tasks[2].split("/to")[1];
                    Event newEvent = new Event(desc, from, to);
                    taskList.addTask(newEvent);

                } else if (tasks[0].contains("D")) { //deadline case
                    String desc = tasks[2].split("/by")[0].substring(1);
                    String by = tasks[2].split("/by")[1];
                    LocalDate parsedDate = parseDate(by);
                    Deadline newDeadline = new Deadline(desc, parsedDate);
                    taskList.addTask(newDeadline);
                } else if (tasks[0].contains("C")) {
                    String desc = tasks[2].split("/name")[0].substring(1);
                    String name = tasks[2].split("/name")[1].split("/phone")[0].substring(1);
                    String phone = tasks[2].split("/phone")[1].substring(1);
                    Contact newContact = new Contact(desc, name, phone);
                    taskList.addTask(newContact);
                } else {
                    System.out.println("valid task format please");
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println("File not found, trying to create now");
            try {
                File newFile = new File("data/goon.tasks.txt");
                newFile.createNewFile();
                System.out.println("new file created at " + newFile.getAbsolutePath());
            } catch (IOException e1) {
                throw new GoonException("Error: Could not create tasks.txt file");
            }
        }
    }




}
