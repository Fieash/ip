import java.util.ArrayList;
import java.util.Scanner;

public class Goon {
    public static void formattedPrint(String input) {
        System.out.println("\t____________________________________________________________\n"
                + "\t"+ input + "\n\t____________________________________________________________\n");
    }

    public static void main(String[] args) {
        System.out.println("" +
                "  ________                    __________        __   \n" +
                        " /  _____/  ____   ____   ____\\______   \\ _____/  |_ \n" +
                        "/   \\  ___ /  _ \\ /  _ \\ /    \\|    |  _//  _ \\   __\\\n" +
                        "\\    \\_\\  (  <_> |  <_> )   |  \\    |   (  <_> )  |  \n" +
                        " \\______  /\\____/ \\____/|___|  /______  /\\____/|__|  \n" +
                        "        \\/                   \\/       \\/              \n" +
                "____________________________________________________________\n" +
                " Hello! I'm GoonBot\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        ArrayList<String> listInputs = new ArrayList<String>();
        int counter = 1;

        while (!input.equals("bye")) {
            if(input.equals("list")) {
                System.out.println("\t____________________________________________________________");
                for(String s : listInputs){
                    System.out.println("\t" + s);
                }
                System.out.println("\t____________________________________________________________\n");
            }else {
                formattedPrint("added: "+ input);
                listInputs.add(counter + ". " + input);
                counter++;
            }

            input = scanner.nextLine();
        }

        formattedPrint("Bye. Hope to see you again soon!");
    }

}
