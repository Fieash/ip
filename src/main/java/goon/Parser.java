package goon;

import goon.tasks.*;

import java.time.LocalDate;

/**
 * Creates Parser, takes in input, outputs command
 */
public class Parser {

    public Parser() {}

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
                if(!Ui.markCheck(input.length(), 5)) {
                    return "\tPlease enter a valid task to mark";
                }
                int mark = input.charAt(5) - '0';
                output += ("\tNice! I've marked this task as done:");
                Task taskToMark = taskList.getTask(mark - 1);
                taskList.set(mark - 1, taskToMark.markAsDone());
                output += taskToMark;
                return output;
            case "unmark":
                if(!Ui.markCheck(input.length(), 6)) {
                    return "\tPlease enter a valid task to unmark";
                }
                int unmark = input.charAt(7) - '0';
                output += ("\tOK, I've marked this task as not done yet:");
                Task taskToUnmark = taskList.getTask(unmark - 1);
                taskList.set(unmark - 1, taskToUnmark.unmark());
                output += taskToUnmark;
                return output;
            case "todo":
                if(!Ui.descriptionCheck(input.length(),6, "ToDo")){
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
                if(!Ui.descriptionCheck(input.length(),7, "Event")){
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
                if(!Ui.descriptionCheck(input.length(),11, "Deadline")){
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
                if(!Ui.markCheck(input.length(),8)){
                    return "please enter a valid task to delete";
                }
                int deleteIndex = input.charAt(7) - '0';
                output += taskList.deleteTask(deleteIndex);
            case "find":
                if(!Ui.descriptionCheck(input.length(),6, "Find")){
                    return "please enter a valid description for find";
                }
                String findString = input.split(" ")[1];
                taskList.findTask(findString);
                return output;
            default:
                System.out.println("Gooner, you better wake up and enter a valid command >:-(");
                return "Gooner, you better wake up and enter a valid command >:-(";

        }
    }


}
