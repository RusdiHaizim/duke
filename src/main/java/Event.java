import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    protected String at;
    protected Date dateNow;

    public Event(String description, String at) throws DukeException {
        super(description);
        this.at = at;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
        sdf.setLenient(false);
        try {
            dateNow = sdf.parse(at);
        } catch (ParseException e) {
            throw new DukeException("Task does not have dd/MM/yyyy HHmm date-time format!");
        }
    }

    @Override
    public String toString() {
        return "[E]" + this.description + " (at: " + at + ")";
    }

    @Override
    public String getDateTime() {
        return dateNow.toString();
    }
}
