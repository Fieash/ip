package goon;

import goon.tasks.Deadline;
import goon.tasks.Event;
import goon.tasks.Task;
import goon.tasks.TaskList;
import goon.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Storage {
    public String filePath;

    public Storage(String filepath) {
        this.filePath = filepath;
    }

    //add the task to goon.tasks.txt file
    public void addTaskToFile(Task newTask) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.append(newTask.toFileFormat());
            fw.close();
        } catch (IOException e) {
            System.out.println("G00n3r, an error occured while writing to the file.");
        }
    }

    public static LocalDate parseDate(String input) {
        try {
            String date = input.replaceAll("\\s+","");
            return LocalDate.parse(date);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("date not in correct format");
            e.printStackTrace();
        }
        return LocalDate.parse("1111-11-11");
    }

    //load the file into the tasklist
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

                } else {
                    System.out.println("valid task format please");
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println("File not found, trying to create now");
            try {
                File nf = new File("data/goon.tasks.txt");
                nf.createNewFile();
                System.out.println("new file created at " + nf.getAbsolutePath());
            } catch (IOException e1) {
                throw new GoonException("Error: Could not create tasks.txt file");
            }
        }
    }




}
