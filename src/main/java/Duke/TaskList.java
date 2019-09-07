package Duke;



import Duke.Tasks.Task;

import java.util.ArrayList;

public class TaskList{
    private ArrayList<Task> data;

    public TaskList(ArrayList<Task> taskList) {
        data = new ArrayList<>(taskList);
    }

    public TaskList() {
        this.data = new ArrayList<>();
    }

    public Task get(int position) throws DukeException {
        if (position >= data.size() || position < 0)
            throw new DukeException("Out of bounds of data!");
        return data.get(position);
    }

    public void remove(int position) throws DukeException {
        if (position >= data.size() || position < 0)
            throw new DukeException("Task is not within list size!");
        data.remove(position);
    }

    public int size() {
        return data.size();
    }

    public ArrayList<Task> getData() {
        return data;
    }
}
