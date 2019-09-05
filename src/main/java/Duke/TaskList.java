package Duke;



import Duke.Tasks.Task;

import java.util.ArrayList;

public class TaskList{
    public ArrayList<Task> data;

    public TaskList(ArrayList<Task> taskList) {
        data = new ArrayList<>(taskList);
    }

    public TaskList() {
        this.data = new ArrayList<>();
    }
}
