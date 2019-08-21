import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.nio.CharBuffer.wrap;

public class Duke {
    protected static List<String> dataList = new ArrayList<String>();
    private static String border = "____________________________________________________________";
    private static String logo =
              " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static String input;

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
        //level_1(sc);
        level_2(sc);
    }

    private static void level_1(Scanner sc) {
        while (sc.hasNext()) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(border + "\n" + wrap("Bye. Hope to see you again soon!") + "\n" + border + "\n");
                break;
            } else {
                System.out.println(border + "\n" + wrap(input) + "\n" + border + "\n");
            }
        }
    }

    private static void level_2(Scanner sc) {
        while (sc.hasNext()) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(border + "\n" + wrap("Bye. Hope to see you again soon!") + "\n" + border + "\n");
                break;
            } else if (input.equals("list")) {
                int idx = 1;
                System.out.println(border);
                if (dataList.size() > 0) {
                    for (String task : dataList) {
                        System.out.print(idx++ + ". ");
                        System.out.print(task + "\n");
                    }
                } else {
                    System.out.println("Empty List!");
                }
                System.out.println(border);
            } else {
                dataList.add(input);
                System.out.println(border + "\nadded: " + input + "\n" + border);
            }
        }
    }
}
