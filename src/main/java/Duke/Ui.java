package Duke;

import java.util.Scanner;

public class Ui {
    private static String border = "____________________________________________________________";

    public Ui () {

    }

    public void showWelcome() {
        System.out.println(border);
        System.out.println("Hello! I'm Duke");
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("What can I do for you?");
        System.out.println(border);
    }

    public void showLine() {
        System.out.println(border);
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void showLoadingError() {
        System.out.println("No saved files detected.");
    }

    public void showError(String e) {
        System.out.println(e);
    }

    public void showMessage(String m) {
        System.out.println(m);
    }
}
