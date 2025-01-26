import java.util.ArrayList;
import java.util.Scanner;

public class Goon {
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
        int taskCount = countTasks(listInputs);
        System.out.println("\tNow you have " + taskCount + " tasks in the list.");
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

    public static void main(String[] args) {
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
        Scanner scanner = new Scanner(System.in);
//        String input = scanner.nextLine();

        ArrayList<Task> listInputs = new ArrayList<Task>();
        int counter = 1;

        try {
            while (true) {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("bye")){
                    formattedPrint("Bye. Hope to see you again soon!");
                    System.exit(0);
                }
                else if (input.equals("list")) {
                    printDivider("\tHere are the tasks in your list:");
                    int printCounter = 1;
                    for (Task t : listInputs) {
                        System.out.println("\t" + printCounter + "." + t);
                        printCounter++;
                    }
                    printDivider("");

                } else if (input.startsWith("mark")) { //marking tasks
                    if(!markCheck(input.length(), 5, "mark")) {
                        continue;
                    }
                    int mark = input.charAt(5) - '0';
                    printDivider("\tNice! I've marked this task as done:");
                    Task taskToMark = listInputs.get(mark - 1);
                    listInputs.set(mark - 1, taskToMark.markAsDone());
                    System.out.println("\t" + taskToMark.toString());
                    printDivider("");

                } else if (input.startsWith("unmark")) { //unmarking tasks
                    if(!markCheck(input.length(), 6, "mark")) {
                        continue;
                    }
                    int unmark = input.charAt(7) - '0';
                    printDivider("\tOK, I've marked this task as not done yet:");
                    Task taskToUnmark = listInputs.get(unmark - 1);
                    listInputs.set(unmark - 1, taskToUnmark.unmarkAsDone());
                    System.out.println("\t" + taskToUnmark.toString());
                    printDivider("");

                } else if (input.startsWith("todo")) { //adding "ToDo" task
                    if(!descriptionCheck(input.length(),6, "ToDo")){
                        continue;
                    }
                    ToDo newTodo = new ToDo(input.substring(5));
                    addTask(newTodo, listInputs);

                } else if (input.startsWith("event")) { //adding event
                    if(!descriptionCheck(input.length(),7, "Event")){
                        continue;
                    }
                    String desc = input.split("/from")[0].substring(6);
                    String from = input.split("/from")[1].split("/to")[0];
                    String to = input.split("/to")[1];
                    Event newEvent = new Event(desc, from, to);
                    addTask(newEvent, listInputs);

                } else if (input.length() > 9 && input.startsWith("deadline")) { //adding deadline
                    if(!descriptionCheck(input.length(),11, "Deadline")){
                        continue;
                    }
                    String desc = input.split("/by")[0].substring(9);
                    String by = input.split("/by")[1];
                    Deadline newDead = new Deadline(desc, by);
                    addTask(newDead, listInputs);
                } else {
//                formattedPrint("added: "+ input);
//                listInputs.add(new Task(input));
//                counter++;
                    System.out.println("Gooner, you better wake up and enter a valid command >:-(");
                }
//                input = scanner.nextLine();
            }
        } catch (Exception e) {
            System.out.println("Uh oh this catchall shouldn't happen :(" + e.getMessage());
        }


    }

}
