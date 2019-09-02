import java.util.Scanner;

class Ui {
    private static String border = "____________________________________________________________";

    Ui () {

    }

    void showWelcome() {
        System.out.println(border);
        System.out.println("Hello! I'm Duke");
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("What can I do for you?");
    }

    void showLine() {
        System.out.println(border);
    }

    String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    void showLoadingError() {
        System.out.println("No saved files detected.");
    }

    void showError(String e) {
        System.out.println(e);
    }

    void showMessage(String m) {
        System.out.println(m);
    }
}
