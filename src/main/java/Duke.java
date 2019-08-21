
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
        System.out.println("Hello from\n" + logo);
        System.out.println(wrap("What can I do for you?"));
        System.out.println(border + "\n");

    }
}
