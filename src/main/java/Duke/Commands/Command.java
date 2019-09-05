package Duke.Commands;

import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

public abstract class Command {
    public CmdType type;
    public String input;

    public enum CmdType {
        EXIT, LIST, FIND, DONE, DELETE, TODO, DEADLINE, EVENT
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return this.type == CmdType.EXIT;
    }
}