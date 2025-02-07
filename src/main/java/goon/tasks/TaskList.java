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

    // init task list with empty arraylist
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int size() {
        return this.tasks.size();
    }

    // clear the task list
    public void clear() {
        this.tasks.clear();
    }

    public void set(int index, Task task) {
        this.tasks.set(index, task);
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

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

    public static void addTaskToFile(Task newTask) throws GoonException {
        try {
            FileWriter fw = new FileWriter("data/tasks.txt", true);
            fw.append(newTask.toFileFormat());
            fw.close();
        } catch (IOException e) {
            System.out.println("G00n3r, an error occured while writing to the file.");
            throw new GoonException("at addTaskToFile");
        }
    }

    public static void printDivider(String input) {
        System.out.println("\t____________________________________________________________\n" +input);
    }

    //list all tasks
    public void listTasks() {
        printDivider("\tHere are the tasks in your list:");
        int printCounter = 1;
        for (Task t : tasks) {
            System.out.println("\t" + printCounter + "." + t);
            printCounter++;
        }
        printDivider("");
    }

}
