package Duke.Commands;

import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

public class DoneCommand extends Command {
    public DoneCommand(String str) {
        type = CmdType.DONE;
        input = str;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        input = input.substring(5);
        try {
            int num = Integer.parseInt(input);
            --num;
            boolean isInsideData = false;
            for (int i = 0; i < tasks.data.size(); ++i) {
                if (i == num) {
                    if (tasks.data.get(i).isDone()) {
                        ui.showMessage(tasks.data.get(i).toString().substring(3) + " is already done!");
                        isInsideData = true;
                        continue;
                    }
                    tasks.data.get(i).markAsDone();
                    storage.write(tasks.data);
                    ui.showMessage("Nice! I've marked this task as done: ");
                    ui.showMessage("    [✓] " + tasks.data.get(i).toString().substring(3));
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
    }
}
