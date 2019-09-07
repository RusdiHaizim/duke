package Duke.Tasks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    private String at;
    private Date dateNow;

    public Event(String description, String at) {
        super(description);
        this.at = at;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
        sdf.setLenient(false);
        try {
            dateNow = sdf.parse(at);
        } catch (ParseException e) {
            //throw new DukeException("Tasks.Task does not have dd/MM/yyyy HHmm date-time format!");
        }
    }

    @Override
    public String toString() {
        return "[E]" + description + " (at: " + at + ")";
    }

    @Override
    public String getDateTime() {
        return dateNow.toString();
    }

    @Override
    public String getExtra() {
        return this.at;
    }
}
