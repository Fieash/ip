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
        String input = scanner.nextLine();
        ArrayList<Task> listInputs = new ArrayList<Task>();
        int counter = 1;

        while (!input.equals("bye")) {
            if(input.equals("list")) {
                printDivider("\tHere are the tasks in your list:");
                int printCounter = 1;
                for(Task t : listInputs){
                    System.out.println("\t"+ printCounter + "." + t);
                    printCounter++;
                }
                printDivider("");

            } else if(input.length() > 5 && input.startsWith("mark")){ //marking tasks
                int mark = input.charAt(5) - '0';
                printDivider("\tNice! I've marked this task as done:");
                Task taskToMark = listInputs.get(mark-1);
                listInputs.set(mark-1, taskToMark.markAsDone());
                System.out.println("\t"+ taskToMark.toString());
                printDivider("");

            } else if (input.length() > 7 && input.startsWith("unmark")) { //unmarking tasks
                int mark = input.charAt(7) - '0';
                printDivider("\tOK, I've marked this task as not done yet:");
                Task taskToMark = listInputs.get(mark-1);
                listInputs.set(mark-1, taskToMark.unmarkAsDone());
                System.out.println("\t"+ taskToMark.toString());
                printDivider("");

            } else if(input.length() > 5 && input.startsWith("todo")) { //adding "ToDo" task
                ToDo newTodo = new ToDo(input.substring(5));
                addTask(newTodo, listInputs);

            } else if(input.length() > 6 && input.startsWith("event")) { //adding event
                String desc = input.split("/from")[0].substring(6);
                String from = input.split("/from")[1].split("/to")[0];
                String to = input.split("/to")[1];
                Event newEvent = new Event(desc, from, to);
                addTask(newEvent, listInputs);

            } else if (input.length() > 9 && input.startsWith("deadline")) { //adding deadline
                String desc = input.split("/by")[0].substring(9);
                String by = input.split("/by")[1];
                Deadline newDead = new Deadline(desc, by);
                addTask(newDead, listInputs);
            }else {
                formattedPrint("added: "+ input);
                listInputs.add(new Task(input));
                counter++;
            }
            input = scanner.nextLine();
        }

        formattedPrint("Bye. Hope to see you again soon!");
    }

}
