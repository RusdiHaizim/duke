import java.util.ArrayList;

class TaskList{
    ArrayList<Task> data;

    TaskList(ArrayList<Task> taskList) {
        data = new ArrayList<>(taskList);
    }

    TaskList() {
        this.data = new ArrayList<>();
    }
}
