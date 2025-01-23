import java.util.Scanner;

public class Goon {
    public static void main(String[] args) {
        System.out.println("" +
                " ________                      \n" +
                " /  _____/  ____   ____   ____  \n" +
                "/   \\  ___ /  _ \\ /  _ \\ /    \\ \n" +
                "\\    \\_\\  (  <_> |  <_> )   |  \\\n" +
                " \\______  /\\____/ \\____/|___|  /\n" +
                "        \\/                   \\/ \n" +
                "____________________________________________________________\n" +
                " Hello! I'm GoonBot\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            System.out.println("\n\t____________________________________________________________\n"
            + "\t" + input + "\n\t____________________________________________________________\n");
            input = scanner.nextLine();
        }
        System.out.println("\t____________________________________________________________\n " +
                "\tBye. Hope to see you again soon!" +
                "\n\t____________________________________________________________\n");
    }
}
