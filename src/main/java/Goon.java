import java.util.ArrayList;
import java.util.Scanner;

public class Goon {
    public static void formattedPrint(String input) {
        System.out.println("\t____________________________________________________________\n"
                + "\t"+ input + "\n\t____________________________________________________________\n");
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
                System.out.println("\t____________________________________________________________\n" +
                        "Here are the tasks in your list:");
                int printCounter = 1;
                for(Task t : listInputs){
                    System.out.println("\t"+ printCounter + "." + t);
                    printCounter++;
                }
                System.out.println("\t____________________________________________________________\n");
            } else if(input.length() > 5 && input.startsWith("mark")){
                int mark = input.charAt(5) - '0';
                System.out.println("\t____________________________________________________________\n" +
                        "Nice! I've marked this task as done:");
                Task taskToMark = listInputs.get(mark-1);
                listInputs.set(mark-1, taskToMark.markAsDone());
                System.out.println("\t"+ taskToMark.toString());
                System.out.println("\t____________________________________________________________\n");
            } else if (input.length() > 7 && input.startsWith("unmark")) {
                int mark = input.charAt(7) - '0';
                System.out.println("\t____________________________________________________________\n" +
                        "OK, I've marked this task as not done yet:");
                Task taskToMark = listInputs.get(mark-1);
                listInputs.set(mark-1, taskToMark.unmarkAsDone());
                System.out.println("\t"+ taskToMark.toString());
                System.out.println("\t____________________________________________________________\n");
            } else {
                formattedPrint("added: "+ input);
                listInputs.add(new Task(input));
                counter++;
            }
            input = scanner.nextLine();
        }

        formattedPrint("Bye. Hope to see you again soon!");
    }

}
