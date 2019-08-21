import java.util.Scanner;

import static java.nio.CharBuffer.wrap;

public class Duke {

    private static String border = "____________________________________________________________";
    private static String logo =
              " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Program starts here.
     */
    public static void main(String[] args) {
        System.out.println(border);
        System.out.println("Hello! I'm Duke");
        System.out.println(wrap("What can I do for you?"));
        System.out.println(border + "\n");

        handleCommand();
    }

    private static void handleCommand() {
        Scanner sc = new Scanner(System.in);
        level_1(sc);
    }

    private static void level_1(Scanner sc) {
        String input = "";
        while (sc.hasNext()) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(border + "\n" + wrap("Bye. Hope to see you again soon!") + "\n" + border + "\n");
                break;
            }
            else {
                System.out.println(border + "\n" + wrap(input) + "\n" + border + "\n");
            }
        }
    }
}
