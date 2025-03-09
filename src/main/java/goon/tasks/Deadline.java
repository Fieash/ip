package goon.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Creates deadline
     * @param description of the Deadline object
     * @param by end date of the deadline in LocalDate format
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Converts the Deadline into a string format suitable for the text file storage
     * @return String file format representation
     */
    @Override
    public String toFileFormat() {
        return "\nD" + super.toFileFormat() + "/by " + by;
    }

    /**
     * Converts the Deadline into a string format suitable for printing
     * @return String representation of the Event
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() +
                "(by:" + by.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}