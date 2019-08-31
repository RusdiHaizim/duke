import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.nio.CharBuffer.wrap;

public class Duke {
    //protected static List<String> dataList = new ArrayList<String>();
    protected static List<Task> data = new ArrayList<Task>();
    protected static List<String> savedData = new ArrayList<String>();
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
    public static void main(String[] args) throws DukeException {
        System.out.println(border);
        System.out.println("Hello! I'm Duke");
        System.out.println(wrap("What can I do for you?"));
        System.out.println(border);
        readState();
        System.out.println(border);
        handleCommand();
    }

    private static void handleCommand() {
        Scanner sc = new Scanner(System.in);
        //level_1(sc);
        //level_2(sc);
        processReq(sc);
    }

    /*
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
     */

    /*
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
     */

    private static void processReq(Scanner sc) {
        while (sc.hasNext()) {
            input = sc.nextLine();
            try {
                System.out.println(border);
                boolean isInsideData;
                if (input.equals("bye")) {
                    System.out.println(wrap("\nBye. Hope to see you again soon!\n"));
                    break;
                } else if (input.equals("list")) {
                    int idx = 1;
                    if (data.size() > 0) {
                        System.out.println("Here are the tasks in your list:");
                        for (Task task : data) {
                            System.out.print("    " + idx++ + ".");
                            String before = task.toString().substring(0, 3);
                            String after = task.toString().substring(3);
                            System.out.print(before);
                            System.out.print("[" + task.getStatusIcon() + "] ");
                            System.out.print(after + "\n");
                        }
                    } else {
                        System.out.println("Empty List!");
                    }
                } else if (input.length() > 4 && input.substring(0, 4).equals("find")) {
                    input = input.substring(5);
                    if (data.size() > 0) {
                        System.out.println("Here are the matching tasks in your list:");
                        for (int i = 0; i < data.size(); ++i) {
                            if (data.get(i).toString().contains(input)) {
                                int temp = i + 1;
                                System.out.print(temp + ".");
                                System.out.print(data.get(i).toString().substring(0,3));
                                System.out.print("[" + data.get(i).getStatusIcon() + "] ");
                                System.out.println(data.get(i).toString().substring(3));
                            }
                        }
                    } else {
                        System.out.println("Empty List! Nothing to find.");
                    }
                } else if (input.length() > 4 && input.substring(0, 4).equals("done")) {
                    input = input.substring(5);
                    try {
                        int num = Integer.parseInt(input);
                        --num;
                        isInsideData = false;
                        for (int i = 0; i < data.size(); ++i) {
                            if (i == num) {
                                if (data.get(i).isDone) {
                                    System.out.println(data.get(i).toString().substring(3) + " is already done!");
                                    isInsideData = true;
                                    continue;
                                }
                                data.get(i).markAsDone();
                                savedData.set(i, savedData.get(i).replace("✗", "✓"));
                                saveState();
                                System.out.println("Nice! I've marked this task as done: ");
                                System.out.println("    [✓] " + data.get(i).toString().substring(3));
                                isInsideData = true;
                                break;
                            }
                        }
                        if (!isInsideData) {
                            System.out.println("Task number is out of bounds! [Done]");
                        }
                    } catch (NumberFormatException e) {
                        throw new DukeException("Not a valid Task Number!");
                        //System.out.println("Error: Not a valid Task Number!");
                    }
                } else if (input.length() > 6 && input.substring(0,6).equals("delete")) {
                    input = input.substring(7);
                    try {
                        int num = Integer.parseInt(input);
                        --num;
                        for (int i = 0; i < data.size(); ++i) {
                            if (i == num) {
                                System.out.println("Noted. I've removed this task: ");

                                System.out.print("  " + data.get(i).toString().substring(0, 3));
                                System.out.print("[" + data.get(i).getStatusIcon() + "] ");
                                System.out.println(data.get(i).toString().substring(3));

                                data.remove(i);
                                savedData.remove(i);
                                saveState();

                                System.out.println("Now you have " + data.size() + " tasks in the list.");
                                break;
                            }
                        }
                    } catch (NumberFormatException e) {
                        throw new DukeException("Task number is out of bounds or invalid! [Delete]");
                    }
                } else {
                    if (commandType(input) == 1) {
                        runTodo(input, 0);
                    } else if (commandType(input) == 2) {
                        runDeadline(input, 0);
                    } else if (commandType(input) == 3) {
                        runEvent(input, 0);
                    }
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());

            }
            System.out.println(border);
        }
    }
    private static void runTodo(String input, int state) {
        input = input.substring(5);
        Task tempTask = new ToDo(input);
        if (state == 2)
            tempTask.markAsDone();
        data.add(tempTask);
        writeData(tempTask, null);
        if (state == 0) {
            System.out.println("Got it. I've added this task: ");
            System.out.println("    [T][" + tempTask.getStatusIcon() + "] " + input);
            System.out.println("Now you have " + data.size() + " tasks in the list.");
        }
    }
    private static void runDeadline(String input, int state) throws DukeException {
        input = input.substring(9);
        int startOfBy = input.indexOf("/");
        String tt1 = input.substring(0, startOfBy - 1);
        //System.out.println(tt1);
        String tt2 = input.substring(startOfBy + 4);
        //System.out.println(tt2);
        Task tempTask = new Deadline(tt1, tt2);
        if (state == 2)
            tempTask.markAsDone();
        data.add(tempTask);
        //System.out.println(tempTask.getDateTime());
        writeData(tempTask, tt2);
        if (state == 0) {
            System.out.println("Got it. I've added this task: ");
            System.out.println("    [D][" + tempTask.getStatusIcon() + "] " + tt1 + " (by: " + tt2 + ")");
            System.out.println("Now you have " + data.size() + " tasks in the list.");
        }
    }
    private static void runEvent(String input, int state) throws DukeException {
        input = input.substring(6);
        //StringTokenizer st1 = new StringTokenizer(input, "/at ");
        int startOfAt = input.indexOf("/");
        String tt1 = input.substring(0, startOfAt - 1);
        //System.out.println(tt1);
        String tt2 = input.substring(startOfAt + 4);
        //System.out.println(tt2);
        Task tempTask = new Event(tt1, tt2);
        if (state == 2)
            tempTask.markAsDone();
        data.add(tempTask);
        //System.out.println(tempTask.getDateTime());
        writeData(tempTask, tt2);
        if (state == 0) {
            System.out.println("Got it. I've added this task: ");
            System.out.println("    [E][" + tempTask.getStatusIcon() + "] " + tt1 + " (at: " + tt2 + ")");
            System.out.println("Now you have " + data.size() + " tasks in the list.");
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

    private static void writeData(Task task, String extra) {
        String st1, st2, st3, st4 = null;
        st1 = task.toString().substring(1, 2);
        if (st1.equals("D") || st1.equals("E")) {
            st4 = extra;
        }
        st2 = task.getStatusIcon();
        st3 = task.toString().substring(3);
        if (extra != null && st3.contains(extra)) {
            st3 = st3.replace(extra, "");
            st3 = st3.substring(0, st3.length() - 7);
        }
        String finalStr = st1 + "|" + st2 + "|" + st3;
        if (st4 != null) {
            finalStr += "|" + st4;
        }
        savedData.add(finalStr);
        saveState();
    }

    private static void saveState() {
        try {
            PrintWriter out = new PrintWriter("./data/saved_data.txt");
            for (String str : savedData) {
                out.println(str);
            }
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void readState() throws DukeException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./data/saved_data.txt"));
            String line = "";
            while ((line = reader.readLine()) != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(line, "|");
                int count = 1;
                String finalOutput = "";
                String currStr;
                Boolean isChecked = false;
                int cmdState = 0;

                while (stringTokenizer.hasMoreTokens()) {
                    currStr = stringTokenizer.nextToken();
                    if (count == 1) {
                        switch (currStr) {
                            case "T":
                                finalOutput = "todo ";
                                cmdState = 1;
                                break;
                            case "D":
                                finalOutput = "deadline ";
                                cmdState = 2;
                                break;
                            case "E":
                                finalOutput = "event ";
                                cmdState = 3;
                                break;
                        }
                    } else if (count == 2) {
                        if (currStr.equals("✓")) {
                            isChecked = true;
                        }
                    } else if (count == 3) {
                        finalOutput += currStr;
                    } else if (count == 4) {
                        switch (cmdState) {
                            case 2:
                                finalOutput += " /by " + currStr;
                                break;
                            case 3:
                                finalOutput += " /at " + currStr;
                                break;
                        }
                    }
                    count++;
                }
                switch (cmdState) {
                    case 1:
                        if (!isChecked)
                            runTodo(finalOutput, 1);
                        else
                            runTodo(finalOutput, 2);
                        break;
                    case 2:
                        if (!isChecked)
                            runDeadline(finalOutput, 1);
                        else
                            runDeadline(finalOutput, 2);
                        break;
                    case 3:
                        if (!isChecked)
                            runEvent(finalOutput, 1);
                        else
                            runEvent(finalOutput, 2);
                        break;
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("No saved files detected.");
        } catch (IOException e) {
            System.out.println("BALLS");
        }
    }
}