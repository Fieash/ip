package goon.tasks;

public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Creates Event
     * @param description of the Event object
     * @param from start date or time of the event
     * @param to end date or time of the event
     */
    public Event(String description, String from, String to){
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Converts the Event into a string format suitable for the text file storage
     * @return String file format representation
     */
    @Override
    public String toFileFormat() {
        return "\nE" + super.toFileFormat() + "/from" + from + "/to" + to;
    }

    /**
     * Converts the Event into a string format suitable for printing
     * @return String representation of the Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + from + ", to:" + to + ")";
    }
}
