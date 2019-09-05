package Duke.Tasks;


public abstract class Task {
    String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    //"\u2713" : "\u2718"
    public String getStatusIcon() {
        return (isDone ? "✓" : "✗"); //return tick or X symbols
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDateTime() {
        return null;
    }

    public String getExtra() {
        return null;
    }
}
