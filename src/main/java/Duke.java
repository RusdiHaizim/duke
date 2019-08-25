import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import static java.nio.CharBuffer.wrap;

public class Duke {
    protected static List<String> dataList = new ArrayList<String>();
    protected static List<Task> data = new ArrayList<Task>();
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
        //level_2(sc);
        processReq(sc);
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

    private static void processReq(Scanner sc) {
        while (sc.hasNext()) {
            input = sc.nextLine();
            try {
                System.out.println(border);
                if (input.equals("bye")) {
                    System.out.println(wrap("\nBye. Hope to see you again soon!\n"));
                    break;
                } else if (input.equals("list")) {
                    int idx = 1;
                    if (data.size() > 0) {
                        System.out.println("Here are the tasks in your list:");
                        for (Task task : data) {
                            System.out.print(idx++ + ".");
                            String before = task.toString().substring(0, 3);
                            String after = task.toString().substring(3);
                            System.out.print(before);
                            System.out.print("[" + task.getStatusIcon() + "] ");
                            System.out.print(after + "\n");
                        }
                    } else {
                        System.out.println("Empty List!");
                    }
                } else if (input.contains("done")) {
                    input = input.substring(5);
                    try {
                        int num = Integer.parseInt(input);
                        --num;
                        boolean isInsideData = false;
                        for (int i = 0; i < data.size(); ++i) {
                            if (i == num) {
                                if (data.get(i).isDone) {
                                    System.out.println(border);
                                    System.out.println(data.get(i).description + " is already done!");
                                    System.out.println(border);
                                    isInsideData = true;
                                    continue;
                                }
                                data.get(i).markAsDone();
                                System.out.println("Nice! I've marked this task as done: ");
                                System.out.println("    [✓] " + data.get(i).toString().substring(3));
                                isInsideData = true;
                                break;
                            }
                        }
                        if (!isInsideData) {
                            System.out.println(border);
                            System.out.println("Task number is out of bounds!");
                            System.out.println(border);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Not a valid Task Number!");
                    }
                } else if (commandType(input) == 1) {
                    input = input.substring(5);
                    System.out.println("Got it. I've added this task: ");
                    data.add(new ToDo(input));
                    System.out.println("    [T][✗] " + input);
                    System.out.println("Now you have " + data.size() + " tasks in the list.");
                } else if (commandType(input) == 2) {
                    input = input.substring(9);
                    System.out.println("Got it. I've added this task: ");
                    StringTokenizer stringTokenizer = new StringTokenizer(input, "/");
                    String tt1 = stringTokenizer.nextToken();
                    tt1 = tt1.substring(0, tt1.length()-1);
                    String tt2 = stringTokenizer.nextToken();
                    tt2 = tt2.substring(3);
                    data.add(new Deadline(tt1, tt2));
                    System.out.println("    [D][✗] " + tt1 + " (by: " + tt2 + ")");
                    System.out.println("Now you have " + data.size() + " tasks in the list.");
                } else if (commandType(input) == 3) {
                    input = input.substring(6);
                    System.out.println("Got it. I've added this task: ");
                    StringTokenizer st1 = new StringTokenizer(input, "/");
                    String tt1 = st1.nextToken();
                    tt1 = tt1.substring(0, tt1.length()-1);
                    String tt2 = st1.nextToken();
                    tt2 = tt2.substring(3);
                    data.add(new Event(tt1, tt2));
                    System.out.println("    [E][✗] " + tt1 + " (at: " + tt2 + ")");
                    System.out.println("Now you have " + data.size() + " tasks in the list.");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());

            }
            System.out.println(border);
        }
    }

    private static int commandType(String str) throws DukeException{
        if (str.length() >= 4 && str.substring(0, 4).equals("todo")) {
            if (str.length() == 4)
                throw new DukeException("     ☹ OOPS!!! The description of a todo cannot be empty.");
            return 1;
        } else if (str.length() >=5 && str.substring(0, 5).equals("event")) {
            if (str.length() == 5)
                throw new DukeException("     ☹ OOPS!!! The description of a event cannot be empty.");
            return 3;
        } else if (str.length() >= 8 && str.substring(0, 8).equals("deadline")) {
            if (str.length() == 8)
                throw new DukeException("     ☹ OOPS!!! The description of a deadline cannot be empty.");
            return 2;
        } else {
            throw new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-( || Unknown COMMAND TYPE");
        }
    }
}
