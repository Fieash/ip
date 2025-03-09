package goon;

import goon.tasks.Contact;
import goon.tasks.Task;
import goon.tasks.ToDo;
import goon.tasks.Event;
import goon.tasks.Deadline;
import goon.tasks.TaskList;

import java.time.LocalDate;

/**
 * Creates Parser, takes in input, outputs command
 */
public class Parser {

    /**
     * Creates parser
     */
    public Parser() {}

    /**
     * marks the task in the list as done or undone
     * @param mark index of the task to mark
     * @param taskList containing the tasks the user has created
     * @param storage object that interacts with the file store
     * @param isMark boolean True to indicate marking, false to indicate unmarking
     */
    private void markTask(int mark, TaskList taskList, Storage storage, boolean isMark) {
        //update the tasklist with the marked Task
        Task taskToMark = taskList.getTask(mark - 1);
        if (isMark) {
            taskList.set(mark - 1, taskToMark.markAsDone());
        } else {
            taskList.set(mark - 1, taskToMark.unmark());
        }
        try {
            storage.storeTaskList(taskList);
        } catch (GoonException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * deletes the task from the tasklist
     * @param delete index to delete from the tasklist
     * @param taskList containing the tasks the user has created
     * @param storage object that interacts with the file store
     * @return string indicating the task has been deleted
     */
    private String deleteTaskParser(int delete, TaskList taskList, Storage storage) {
        //update the tasklist with the deleted task
        String output = taskList.deleteTask(delete);
        try {
            storage.storeTaskList(taskList);
        } catch (GoonException e) {
            System.out.println(e.getMessage());
        }
        return output;
    }

    /**
     * gets the command from the user's input (helper function)
     * @param input string of the user's input
     * @return String command
     */
    private String getCommand(String input) {
        if (input.isEmpty()) {
            return "invalid";
        }
        if (input.startsWith("bye")) {
            return "bye";
        }
        return input.split(" ")[0].toLowerCase();
    }

    public String parseCommand(String input, TaskList taskList, Storage storage) {
        String output = "";
        String command = getCommand(input);

        switch (command) {
            case "invalid":
                return "gooner entered an invalid command";
            case "bye":
                System.exit(0);
            case "list":
                return Ui.displayAllTasks(taskList);
            case "mark":
                if (!Ui.markCheck(input.length(), 5)) {
                    return "\tPlease enter a valid task to mark";
                }
                int mark = input.charAt(5) - '0';
                output += ("\tNice! I've marked this task as done:\n");
                Task taskToMark = taskList.getTask(mark - 1);
                taskList.set(mark - 1, taskToMark.markAsDone());
                output += taskToMark;
                markTask(mark, taskList, storage,true);
                return output;

            case "unmark":
                if (!Ui.markCheck(input.length(), 6)) {
                    return "\tPlease enter a valid task to unmark";
                }
                int unmark = input.charAt(7) - '0';
                output += ("\tOK, I've marked this task as not done yet:");
                Task taskToUnmark = taskList.getTask(unmark - 1);
                taskList.set(unmark - 1, taskToUnmark.unmark());
                output += taskToUnmark;
                markTask(unmark, taskList, storage,false);
                return output;

            case "todo":
                if (!Ui.descriptionCheck(input.length(), 6, "ToDo")) {
                    return "please enter a valid description for todo";
                }
                ToDo newTodo = new ToDo(input.substring(5));
                output += taskList.addTask(newTodo);
                try {
                    storage.addTaskToFile(newTodo);
                }catch (GoonException e) {
                    output += "\t" + e.getMessage(); //gracefully catch inability to addTaskToFile()
                }
                return output;

            case "event":
                if (!Ui.descriptionCheck(input.length(), 7, "Event")) {
                    return "please enter a valid description for event";
                }
                String eventDescription = input.split("/from")[0].substring(6);
                String from = input.split("/from")[1].split("/to")[0];
                String to = input.split("/to")[1];
                Event newEvent = new Event(eventDescription, from, to);
                output += taskList.addTask(newEvent);
                try {
                    storage.addTaskToFile(newEvent);
                }catch (GoonException e) {
                    output += "\t" + e.getMessage(); //gracefully catch inability to addTaskToFile()
                }
                return output;

            case "deadline":
                if (!Ui.descriptionCheck(input.length(), 11, "Deadline")) {
                    return "please enter a valid description for deadline";
                }
                String deadlineDescription = input.split("/by")[0].substring(9);
                String by = input.split("/by")[1];
                LocalDate parsedDate = Ui.parseDate(by);
                Deadline newDeadline = new Deadline(deadlineDescription, parsedDate);
                output += taskList.addTask(newDeadline);
                try {
                    storage.addTaskToFile(newDeadline);
                }catch (GoonException e) {
                    output += "\t" + e.getMessage(); //gracefully catch inability to addTaskToFile()
                }
                return output;

            case "delete":
                if (!Ui.markCheck(input.length(), 8)) {
                    return "please enter a valid task to delete";
                }
                int deleteIndex = input.charAt(7) - '0';
                output += deleteTaskParser(deleteIndex, taskList, storage);
                return output;

            case "find":
                if (!Ui.descriptionCheck(input.length(), 6, "Find")) {
                    return "please enter a valid description for find";
                }
                String findString = input.split(" ")[1];
                taskList.findTask(findString);
                return output;

            case "contact":
                if (!Ui.descriptionCheck(input.length(), 9, "Contact")) {
                    return "please enter a valid description for contact";
                }
                String contactDescription = input.split("/name")[0].substring(8);
                String name = input.split("/name")[1].split("/phone")[0];
                String phone = input.split("/phone")[1];
                Contact newContact = new Contact(contactDescription, name, phone);
                output += taskList.addTask(newContact);
                try {
                    storage.addTaskToFile(newContact);
                }catch (GoonException e) {
                    output += "\t" + e.getMessage(); //gracefully catch inability to addTaskToFile()
                }
                return output;

            default:
                System.out.println("Gooner, you better wake up and enter a valid command >:-(");
                return "Gooner, you better wake up and enter a valid command >:-(";

        }
    }


}
