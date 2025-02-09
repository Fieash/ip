package goon.tasks;

import goon.GoonException;
import goon.tasks.Deadline;
import goon.tasks.Event;
import goon.tasks.Task;
import goon.tasks.TaskList;
import goon.tasks.ToDo;
import goon.Ui;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> tasks;

    /**
     * Creates the TaskList object and initializes the empty ArrayList
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the size of the tasks ArrayList
     * @return int size of the tasks ArrayList
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Clears all the Tasks in tasks ArrayList
     */
    public void clear() {
        this.tasks.clear();
    }

    /**
     * Updates the Task in the tasks ArrayList by the index
     * @param index of the ArrayList to update
     * @param task updated Task object to replace in the ArrayList
     */
    public void set(int index, Task task) {
        this.tasks.set(index, task);
    }

    /**
     * Gets the task at a specified index
     * @param index of task to get
     * @return Task that is present at the specified index
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Adds a new task to the tasks ArrayList
     * @param newTask Task object to add to end of tasks ArrayList
     */
    public void addTask(Task newTask) {
        printDivider("\tGot it. I've added this task:\n\t\t" + newTask.toString());
        this.tasks.add(newTask);
        System.out.println("\tNow you have " + this.size() + " tasks in the list.");
        printDivider("");
    }

    public void deleteTask(int taskIndex) {
        printDivider("\tNoted. I've removed this task:");
        Task taskToDelete = this.getTask(taskIndex - 1);
        this.tasks.remove(taskIndex - 1);
        System.out.println("\t" + taskToDelete.toString());
        System.out.println("\tNow you have " + this.size() + "tasks in the list.");
        printDivider("");
    }

    /**
     * Adds a task to the file storage
     * @param newTask task to be added to the file
     * @throws GoonException when we are unable to write to the file
     */
    public static void addTaskToFile(Task newTask) throws GoonException {
        try {
            FileWriter fileWriter = new FileWriter("data/tasks.txt", true);
            fileWriter.append(newTask.toFileFormat());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("G00n3r, an error occured while writing to the file.");
            throw new GoonException("at addTaskToFile");
        }
    }

    /**
     * Prints a standard 60 char long '_' divider, append input behind
     * @param input to append on the nextline of the divider
     */
    public static void printDivider(String input) {
        System.out.println("\t____________________________________________________________\n" +input);
    }

    /**
     * Lists all the tasks in the tasks ArrayList
     */
    public void listTasks() {
        int printCounter = 1;
        for (Task t : tasks) {
            System.out.println("\t" + printCounter + "." + t);
            printCounter++;
        }
        printDivider("");
    }

}
