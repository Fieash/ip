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
        ArrayList<String> listInputs = new ArrayList<String>();
        int counter = 1;

        while (!input.equals("bye")) {
            if(input.equals("list")) {
                System.out.println("\t____________________________________________________________\n" +
                        "Here are the tasks in your list:");
                for(String s : listInputs){
                    System.out.println("\t" + s);
                }
                System.out.println("\t____________________________________________________________\n");
            } else if(input.length() > 4 && input.substring(0, 4).equals("mark")){
                int mark = input.charAt(5) - '0';
                System.out.println("\t____________________________________________________________\n" +
                        "Nice! I've marked this task as done:");
                String stringToMark = listInputs.get(mark-1);
                stringToMark = stringToMark.substring(0,3) + "x" + stringToMark.substring(4);
                listInputs.set(mark-1, stringToMark);
                System.out.println("\t"+ listInputs.get(mark-1).substring(2));
                System.out.println("\t____________________________________________________________\n");
            }else {
                formattedPrint("added: "+ input);
                listInputs.add(counter + ".[ ] " + input);
                counter++;
            }
            input = scanner.nextLine();
        }

        formattedPrint("Bye. Hope to see you again soon!");
    }

}
