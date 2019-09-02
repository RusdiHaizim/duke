import java.util.ArrayList;

class Command {
    CmdType type;
    String input;

    enum CmdType {
        EXIT, LIST, FIND, DONE, DELETE, TODO, DEADLINE, EVENT
    }

    Command () {
        type = CmdType.EXIT;
        input = "";
    }

    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        switch (this.type) {
            case EXIT:
                ui.showMessage("\nBye. Hope to see you again soon!\n");
                break;
            case LIST:
                int idx = 1;
                if (tasks.data.size() > 0) {
                    System.out.println("Here are the tasks in your list:");
                    for (Task task : tasks.data) {
                        System.out.print("    " + idx++ + ".");
                        String before = task.toString().substring(0, 3);
                        String after = task.toString().substring(3);
                        System.out.print(before);
                        System.out.print("[" + task.getStatusIcon() + "] ");
                        System.out.print(after + "\n");
                    }
                } else {
                    ui.showError("Empty List!");
                }
                break;
            case FIND:
                input = input.substring(5);
                if (tasks.data.size() > 0) {
                    System.out.println("Here are the matching tasks in your list:");
                    for (int i = 0; i < tasks.data.size(); ++i) {
                        if (tasks.data.get(i).toString().contains(input)) {
                            int temp = i + 1;
                            System.out.print(temp + ".");
                            System.out.print(tasks.data.get(i).toString().substring(0,3));
                            System.out.print("[" + tasks.data.get(i).getStatusIcon() + "] ");
                            System.out.println(tasks.data.get(i).toString().substring(3));
                        }
                    }
                } else {
                    ui.showError("Empty List! Nothing to find.");
                }
                break;
            case DONE:
                input = input.substring(5);
                try {
                    int num = Integer.parseInt(input);
                    --num;
                    boolean isInsideData = false;
                    for (int i = 0; i < tasks.data.size(); ++i) {
                        if (i == num) {
                            if (tasks.data.get(i).isDone) {
                                System.out.println(tasks.data.get(i).toString().substring(3) + " is already done!");
                                isInsideData = true;
                                continue;
                            }
                            tasks.data.get(i).markAsDone();
                            storage.write(tasks.data);
                            System.out.println("Nice! I've marked this task as done: ");
                            System.out.println("    [✓] " + tasks.data.get(i).toString().substring(3));
                            isInsideData = true;
                            break;
                        }
                    }
                    if (!isInsideData) {
                        ui.showError("Task number is out of bounds! [Done]");
                    }
                } catch (NumberFormatException e) {
                    throw new DukeException("Not a valid Task Number!");
                }
                break;
            case DELETE:
                input = input.substring(7);
                try {
                    int num = Integer.parseInt(input);
                    --num;
                    for (int i = 0; i < tasks.data.size(); ++i) {
                        if (i == num) {
                            System.out.println("Noted. I've removed this task: ");
                            System.out.print("  " + tasks.data.get(i).toString().substring(0, 3));
                            System.out.print("[" + tasks.data.get(i).getStatusIcon() + "] ");
                            System.out.println(tasks.data.get(i).toString().substring(3));
                            tasks.data.remove(i);
                            storage.write(tasks.data);
                            System.out.println("Now you have " + tasks.data.size() + " tasks in the list.");
                            break;
                        }
                    }
                } catch (NumberFormatException e) {
                    throw new DukeException("Task number is out of bounds or invalid! [Delete]");
                }
                break;
            case TODO:
                runTodo(tasks.data, input, 0);
                storage.write(tasks.data);
                break;
            case DEADLINE:
                runDeadline(tasks.data, input, 0);
                storage.write(tasks.data);
                break;
            case EVENT:
                runEvent(tasks.data, input, 0);
                storage.write(tasks.data);
                break;
            default:
                throw new DukeException("Invalid Command Type");
        }
    }


    boolean isExit() {
        return this.type == CmdType.EXIT;
    }

    static void runTodo(ArrayList<Task> data, String input, int state) {
        input = input.substring(5);
        Task tempTask = new ToDo(input);
        if (state == 2)
            tempTask.markAsDone();
        data.add(tempTask);
        if (state == 0) {
            System.out.println("Got it. I've added this task: ");
            System.out.println("    [T][" + tempTask.getStatusIcon() + "] " + input);
            System.out.println("Now you have " + data.size() + " tasks in the list.");
        }
    }
    static void runDeadline(ArrayList<Task> data, String input, int state) {
        input = input.substring(9);
        int startOfBy = input.indexOf("/");
        String tt1 = input.substring(0, startOfBy - 1);
        String tt2 = input.substring(startOfBy + 4);
        Task tempTask = new Deadline(tt1, tt2);
        if (state == 2)
            tempTask.markAsDone();
        data.add(tempTask);
        if (state == 0) {
            System.out.println("Got it. I've added this task: ");
            System.out.println("    [D][" + tempTask.getStatusIcon() + "] " + tt1 + " (by: " + tt2 + ")");
            System.out.println("Now you have " + data.size() + " tasks in the list.");
        }
    }
    static void runEvent(ArrayList<Task> data, String input, int state) {
        input = input.substring(6);
        int startOfAt = input.indexOf("/");
        String tt1 = input.substring(0, startOfAt - 1);
        String tt2 = input.substring(startOfAt + 4);
        Task tempTask = new Event(tt1, tt2);
        if (state == 2)
            tempTask.markAsDone();
        data.add(tempTask);
        if (state == 0) {
            System.out.println("Got it. I've added this task: ");
            System.out.println("    [E][" + tempTask.getStatusIcon() + "] " + tt1 + " (at: " + tt2 + ")");
            System.out.println("Now you have " + data.size() + " tasks in the list.");
        }
    }
}