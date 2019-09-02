class Parser {
    static Command parse(String input) throws DukeException {
        Command command = new Command();
        command.input = input;
        if (input.equals("bye")) {
            command.type = Command.CmdType.EXIT;
        } else if (input.equals("list")) {
            command.type = Command.CmdType.LIST;
        } else if (input.length() > 4 && input.substring(0, 4).equals("find")) {
            command.type = Command.CmdType.FIND;
        } else if (input.length() > 4 && input.substring(0, 4).equals("done")) {
            command.type = Command.CmdType.DONE;
        } else if (input.length() > 6 && input.substring(0,6).equals("delete")) {
            command.type = Command.CmdType.DELETE;
        } else if (input.length() >= 4 && input.substring(0, 4).equals("todo")){
            if (input.length() == 4)
                throw new DukeException("     ☹ OOPS!!! The description of a todo cannot be empty.");
            command.type = Command.CmdType.TODO;
        } else if (input.length() >= 5 && input.substring(0, 5).equals("event")) {
            if (input.length() == 5)
                throw new DukeException("     ☹ OOPS!!! The description of a event cannot be empty.");
            command.type = Command.CmdType.EVENT;
        } else if (input.length() >= 8 && input.substring(0, 8).equals("deadline")) {
            if (input.length() == 8)
                throw new DukeException("     ☹ OOPS!!! The description of a deadline cannot be empty.");
            command.type = Command.CmdType.DEADLINE;
        } else {
            throw new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-( || Unknown COMMAND TYPE");
        }
        return command;
    }
}
