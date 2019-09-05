package Duke.Tasks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {
    private String by;
    private Date dateNow;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
        sdf.setLenient(false);
        try {
            dateNow = sdf.parse(by);
        } catch (ParseException e) {
            //throw new DukeException("Tasks.Task does not have dd/MM/yyyy HHmm date-time format!");
        }
    }

    @Override
    public String toString() {
        return "[D]" + this.description + " (by: " + by + ")";
    }

    @Override
    public String getDateTime() {
        return dateNow.toString();
    }

    @Override
    public String getExtra() {
        return this.by;
    }

}
