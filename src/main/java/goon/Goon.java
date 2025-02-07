package goon;

import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;

import goon.tasks.*;

public class Goon {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public static void formattedPrint(String input) {
        System.out.println("\t____________________________________________________________\n"
                + "\t"+ input + "\n\t____________________________________________________________\n");
    }

    public static void printDivider(String input) {
        System.out.println("\t____________________________________________________________\n" +input);
    }

    public static int countTasks(ArrayList<Task> list) {
        int count = 0;
        for(Task t : list) {
            count++;
        }
        return count;
    }

    public static void addTask(Task newTask, ArrayList<Task> listInputs) {
        printDivider("\tGot it. I've added this task:\n\t\t" + newTask.toString());
        listInputs.add(newTask);
        System.out.println("\tNow you have " + countTasks(listInputs) + " goon.tasks in the list.");
        printDivider("");
    }

    public static void addTaskToFile(Task newTask) {
        try {
            FileWriter fw = new FileWriter("data/goon.tasks.txt", true);
            fw.append(newTask.toFileFormat());
            fw.close();
        } catch (IOException e) {
            System.out.println("G00n3r, an error occured while writing to the file.");
        }
    }

    public static void deleteTask(int taskIndex, ArrayList<Task> listInputs) {
        printDivider("\tNoted. I've removed this task:");
        Task taskToDelete = listInputs.get(taskIndex - 1);
        listInputs.remove(taskIndex - 1);
        System.out.println("\t" + taskToDelete.toString());
        System.out.println("\tNow you have " + countTasks(listInputs) + " goon.tasks in the list.");
        printDivider("");
    }

    public static boolean markCheck(int actual, int minimum, String taskType) {
        if (actual < minimum) {
            formattedPrint("Gooner, " + taskType + "ing of a task needs a number, have you attended primary school? idiot.");
            return false;
        }
        return true;
    }

    public static boolean descriptionCheck(int actual, int minimum, String taskType) {
        if (actual < minimum) {
            formattedPrint("Gooner, description of a " + taskType + " needs to have something how else would you identify the damn task???????");
            return false;
        }
        return true;
    }

    public static void parseSavedTasks(ArrayList<Task> listInputs) {
        //todo
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

    public Goon(){
//        ui = new goon.Ui();
        storage = new Storage("data/goon.tasks.txt");
//        try {
//            goon.tasks = new goon.tasks.TaskList(storage.load());
//        } catch (Exception e) {
//            goon.tasks = new goon.tasks.TaskList();
//        }
    }

    public static void main(String[] args) {


        // init the array
//        ArrayList<Task> listInputs = new ArrayList<Task>();
//        int counter = 1;


//        // attempt to read file
//        try {
//            File savedTasks = new File("data/goon.tasks.txt");
//            Scanner readTasks = new Scanner(savedTasks);
//            while (readTasks.hasNextLine()) {
//                String line = readTasks.nextLine();
//                String[] tasks = line.split("\\|");
//                System.out.println(goon.tasks[0] + "." + goon.tasks[1] + "." + goon.tasks[2]);
//
//                if(tasks[0].contains("T")) { //todo case
//                    ToDo newTodo = new ToDo(tasks[2].substring(1));
//                    addTask(newTodo, listInputs);
//
//                } else if (tasks[0].contains("E")) { //event case
//                    String desc = tasks[2].split("/from")[0].substring(1);
//                    String from = tasks[2].split("/from")[1].split("/to")[0];
//                    String to = tasks[2].split("/to")[1];
//                    Event newEvent = new Event(desc, from, to);
//                    addTask(newEvent, listInputs);
//                } else if (tasks[0].contains("D")) { //deadline case
//                    String desc = tasks[2].split("/by")[0].substring(1);
//                    String by = tasks[2].split("/by")[1];
//                    LocalDate parsedDate = parseDate(by);
//                    Deadline newDead = new Deadline(desc, parsedDate);
//                    addTask(newDead, listInputs);
//                } else {
//                    System.out.println("valid task format please");
//                }
//            }
//
//        } catch (FileNotFoundException e) {
//            System.out.println("Error: Could not find the goon.tasks.txt file.");
//            try {
//                File nf = new File("data/goon.tasks.txt");
//                nf.createNewFile();
//                System.out.println("new file created at "+ nf.getAbsolutePath());
//            } catch (IOException e1) {
//                System.out.println("Error: Could not create new file.");
//            }
//        }



        TaskList taskList = new TaskList();
        Storage storage = new Storage("data/tasks.txt");
        try{
            storage.load(taskList);
        } catch (GoonException goonException) {
            System.out.println(goonException.getMessage());
        }
        Ui ui = new Ui();
        ui.run(taskList, storage);



        Scanner scanner = new Scanner(System.in); //scan for more inputs


//        try {
//            while (true) {
//                String input = scanner.nextLine();
//                if (input.equalsIgnoreCase("bye")){
//                    formattedPrint("Bye. Hope to see you again soon!");
//                    System.exit(0);
//                }
//                else if (input.equals("list")) {
//                    printDivider("\tHere are the tasks in your list:");
//                    int printCounter = 1;
//                    for (Task t : listInputs) {
//                        System.out.println("\t" + printCounter + "." + t);
//                        printCounter++;
//                    }
//                    printDivider("");
//
//                } else if (input.startsWith("mark")) { //marking tasks
//                    if(!markCheck(input.length(), 5, "mark")) {
//                        continue;
//                    }
//                    int mark = input.charAt(5) - '0';
//                    printDivider("\tNice! I've marked this task as done:");
//                    Task taskToMark = listInputs.get(mark - 1);
//                    listInputs.set(mark - 1, taskToMark.markAsDone());
//                    System.out.println("\t" + taskToMark.toString());
//                    printDivider("");
//
//                } else if (input.startsWith("unmark")) { //unmarking tasks
//                    if(!markCheck(input.length(), 6, "mark")) {
//                        continue;
//                    }
//                    int unmark = input.charAt(7) - '0';
//                    printDivider("\tOK, I've marked this task as not done yet:");
//                    Task taskToUnmark = listInputs.get(unmark - 1);
//                    listInputs.set(unmark - 1, taskToUnmark.unmarkAsDone());
//                    System.out.println("\t" + taskToUnmark.toString());
//                    printDivider("");
//
//                } else if (input.startsWith("todo")) { //adding "Tasks.ToDo" task
//                    if(!descriptionCheck(input.length(),6, "Tasks.ToDo")){
//                        continue;
//                    }
//                    ToDo newTodo = new ToDo(input.substring(5));
//                    addTask(newTodo, listInputs);
//                    addTaskToFile(newTodo);
//
//                } else if (input.startsWith("event")) { //adding event
//                    if(!descriptionCheck(input.length(),7, "Tasks.Event")){
//                        continue;
//                    }
//                    String desc = input.split("/from")[0].substring(6);
//                    String from = input.split("/from")[1].split("/to")[0];
//                    String to = input.split("/to")[1];
//                    Event newEvent = new Event(desc, from, to);
//                    addTask(newEvent, listInputs);
//                    addTaskToFile(newEvent);
//
//                } else if (input.startsWith("deadline")) { //adding deadline
//                    if(!descriptionCheck(input.length(),11, "Tasks.Deadline")){
//                        continue;
//                    }
//                    String desc = input.split("/by")[0].substring(9);
//                    String by = input.split("/by")[1];
//                    LocalDate parsedDate = parseDate(by);
//                    Deadline newDead = new Deadline(desc, parsedDate);
//                    addTask(newDead, listInputs);
//                    addTaskToFile(newDead);
//
//                } else if (input.startsWith("delete")) {
//                    if(!markCheck(input.length(),8, "Delete")){ continue; }
//                    int deleteIndex = input.charAt(7) - '0';
//                    deleteTask(deleteIndex, listInputs);
//
//                } else {
//                    System.out.println("Gooner, you better wake up and enter a valid command >:-(");
//                }
//            }
//        } catch (IndexOutOfBoundsException e) {
//            System.out.println("Gooner, enter something within bounds idiot.");
//        } catch (Exception e) {
//            System.out.println("Uh oh this catchall shouldn't happen :(" + e.getMessage());
//        }


    }
}
