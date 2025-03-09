package goon.tasks;

public class Contact extends Task {
    private String name;
    private String phone;

    /**
     * Creates Contact
     * @param description of the person
     * @param name name of the person
     * @param phone number of the person
     */
    public Contact(String description, String name, String phone) {
        super(description);
        this.name = name;
        this.phone = phone;
    }

    /**
     * Converts the Contact into a string format suitable for the text file storage
     * @return String file format representation
     */
    @Override
    public String toFileFormat() {
        return "\nC" + super.toFileFormat() + "/name" + name + "/phone" + phone;
    }

    /**
     * Converts the Contact into a string format suitable for printing
     * @return String representation of the Contact
     */
    @Override
    public String toString() {
        return "[C]" + super.toString() + name + phone;
    }
}
