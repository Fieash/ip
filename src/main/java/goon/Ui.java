package goon;

import goon.tasks.Deadline;
import goon.tasks.Event;
import goon.tasks.Task;
import goon.tasks.TaskList;
import goon.tasks.ToDo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the main IO operations of the program
 * Main command loop is here
 */
public class Ui {
    public Ui(){

    }

    /**
     * Displays the introduction message
     */
    public void displayIntro() {
        System.out.println(
            "  ________                    __________        __   \n" +
            " /  _____/  ____   ____   ____\\______   \\ _____/  |_ \n" +
            "/   \\  ___ /  _ \\ /  _ \\ /    \\|    |  _//  _ \\   __\\\n" +
            "\\    \\_\\  (  <_> |  <_> )   |  \\    |   (  <_> )  |  \n" +
            " \\______  /\\____/ \\____/|___|  /______  /\\____/|__|  \n" +
            "        \\/                   \\/       \\/              \n" +
            "____________________________________________________________\n" +
            " Good morning Gooner, this is GoonBot\n" +
            " How may I increase your levels of goon today?\n" +
            "____________________________________________________________\n");
    }

    /**
     * Checks if the task can be marked
     * @param actual length
     * @param minimum length required
     * @return boolean of whether the task has been successfully marked
     */
    public static boolean markCheck(int actual, int minimum) {
        if (actual < minimum) {
            printDivider("Gooner, marking or unmarking of a task needs a number.");
            printDivider("");
            return false;
        }
        return true;
    }

    /**
     * Checks if there is a valid description for the Task
     * @param actual length of description
     * @param minimum length of description required
     * @param taskType of task being checked
     * @return boolean on whether the task has a valid description
     */
    public static boolean descriptionCheck(int actual, int minimum, String taskType) {
        if (actual < minimum) {
            printDivider("Gooner, description of a " + taskType + " needs to have something how else would you identify the damn task???????");
            printDivider("");
            return false;
        }
        return true;
    }

    /**
     * Displays all tasks in the TaskList
     * @param taskList array to read from
     */
    public void displayAllTasks(TaskList taskList) {
        printDivider("\tHere are the tasks in your list:");
        taskList.listTasks();
    }

    /**
     * Parses the string input into a LocalDate format
     * @param input string to parse as a date
     * @return LocalDate representation of the string input
     */
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

    /**
     * Executes the main loop of the program
     * @param taskList array that will be used
     * @param storage object that will handle writing to file
     */
    public void run (TaskList taskList, Storage storage){
        displayIntro();

        try {
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("bye")){
                    printDivider("\tBye. Hope to see you again soon!");
                    printDivider("");
                    System.exit(0);
                }
                else if (input.equals("list")) {
                    displayAllTasks(taskList);

                } else if (input.startsWith("mark")) { //marking tasks
                    if(!markCheck(input.length(), 5)) {
                        continue;
                    }
                    int mark = input.charAt(5) - '0';
                    printDivider("\tNice! I've marked this task as done:");
                    Task taskToMark = taskList.getTask(mark - 1);
                    taskList.set(mark - 1, taskToMark.markAsDone());
                    System.out.println("\t" + taskToMark.toString());
                    printDivider("");

                } else if (input.startsWith("unmark")) { //unmarking tasks
                    if(!markCheck(input.length(), 6)) {
                        continue;
                    }
                    int unmark = input.charAt(7) - '0';
                    printDivider("\tOK, I've marked this task as not done yet:");
                    Task taskToUnmark = taskList.getTask(unmark - 1);
                    taskList.set(unmark - 1, taskToUnmark.unmarkAsDone());
                    System.out.println("\t" + taskToUnmark.toString());
                    printDivider("");

                } else if (input.startsWith("todo")) { //adding "Tasks.ToDo" task
                    if(!descriptionCheck(input.length(),6, "ToDo")){
                        continue;
                    }
                    ToDo newTodo = new ToDo(input.substring(5));
                    taskList.addTask(newTodo);
                    storage.addTaskToFile(newTodo);

                } else if (input.startsWith("event")) { //adding event
                    if(!descriptionCheck(input.length(),7, "Event")){
                        continue;
                    }
                    String desc = input.split("/from")[0].substring(6);
                    String from = input.split("/from")[1].split("/to")[0];
                    String to = input.split("/to")[1];
                    Event newEvent = new Event(desc, from, to);
                    taskList.addTask(newEvent);
                    storage.addTaskToFile(newEvent);

                } else if (input.startsWith("deadline")) { //adding deadline
                    if(!descriptionCheck(input.length(),11, "Deadline")){
                        continue;
                    }
                    String desc = input.split("/by")[0].substring(9);
                    String by = input.split("/by")[1];
                    LocalDate parsedDate = parseDate(by);
                    Deadline newDeadline = new Deadline(desc, parsedDate);
                    taskList.addTask(newDeadline);
                    storage.addTaskToFile(newDeadline);

                } else if (input.startsWith("delete")) {
                    if(!markCheck(input.length(),8)){ continue; }
                    int deleteIndex = input.charAt(7) - '0';
                    taskList.deleteTask(deleteIndex);

                } else if (input.startsWith("find")) {
                    if(!descriptionCheck(input.length(),6, "Find")){
                        continue;
                    }
                    String findString = input.split(" ")[1];
                    taskList.findTask(findString);

                } else {
                    System.out.println("Gooner, you better wake up and enter a valid command >:-(");
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Gooner, enter something within bounds idiot.");
        } catch (Exception e) {
            System.out.println("Uh oh this catchall shouldn't happen :(" + e.getMessage());
        }
    }

    /**
     * Standard length divider to be printed
     * @param input string to the appended to the end of the divider
     */
    public static void printDivider(String input) {
        System.out.println("\t____________________________________________________________\n" + input);
    }
}
