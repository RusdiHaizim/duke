package Duke.Commands;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;
import Duke.Tasks.Task;

public class ListCommand extends Command {
    public ListCommand() {
        type = CmdType.LIST;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int idx = 1;
        if (tasks.data.size() > 0) {
            ui.showMessage("Here are the tasks in your list:");
            for (Task task : tasks.data) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("    ").append(idx++).append(".");
                String before = task.toString().substring(0, 3);
                String after = task.toString().substring(3);
                stringBuilder.append(before);
                stringBuilder.append("[").append(task.getStatusIcon()).append("] ");
                stringBuilder.append(after);
                ui.showMessage(stringBuilder.toString());
            }
        } else {
            ui.showError("Empty List!");
        }
    }
}
