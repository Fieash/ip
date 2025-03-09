package goon.tasks;

public class ToDo extends Task{
    /**
     * Creates ToDo
     * @param description of the Todo object
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Converts the ToDo into a string format suitable for the text file storage
     * @return String file format representation
     */
    @Override
    public String toFileFormat() {
        return "\nT" + super.toFileFormat();
    }

    /**
     * Converts the ToDo into a string format suitable for printing
     * @return String representation of the ToDo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
