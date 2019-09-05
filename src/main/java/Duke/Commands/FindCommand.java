package Duke.Commands;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

public class FindCommand extends Command {
    public FindCommand (String str) {
        type = CmdType.FIND;
        input = str;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        input = input.substring(5);
        if (tasks.data.size() > 0) {
            boolean isInside = false;
            boolean isStarting = true;
            for (int i = 0; i < tasks.data.size(); ++i) {
                if (tasks.data.get(i).toString().contains(input)) {
                    if (isStarting) {
                        isStarting = false;
                        ui.showMessage("Here are the matching tasks in your list:");
                    }
                    int temp = i + 1;
                    String stringBuilder = temp + "." +
                            tasks.data.get(i).toString().substring(0, 3) +
                            "[" + tasks.data.get(i).getStatusIcon() + "] " +
                            tasks.data.get(i).toString().substring(3);
                    ui.showMessage(stringBuilder);
                    isInside = true;
                }
            }
            if (!isInside) {
                ui.showError("Keyword not in List");
            }
        } else {
            ui.showError("Empty List!");
        }
    }
}
