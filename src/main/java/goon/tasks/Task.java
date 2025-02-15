package goon.tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates Task
     * @param description of the Task object
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the marked or unmarked status of the Task as a string
     * @return String representation of the marked status of the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done
     * @return Task after it has been marked
     */
    public Task markAsDone() {
        isDone = true;
        return this;
    }

    /**
     * Gets the description of the Task object
     * @return String description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Unmarks the task as done
     * @return Task after it has been unmarked
     */
    public Task unmark() {
        isDone = false;
        return this;
    }

    /**
     * Converts the Task into a string format suitable for the text file storage
     * @return String file format representation
     */
    public String toFileFormat() {
        String fileString = " | ";
        if (isDone) {
            return fileString + "1 | " + description;
        }
        return fileString + "0 | " + description;
    }

    /**
     * Converts the Task into a string format suitable for printing
     * @return String representation of the Task
     */
    @Override
    public String toString() {
        return "["+ getStatusIcon() + "] " + description;
    }
}