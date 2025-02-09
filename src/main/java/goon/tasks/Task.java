package goon.tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

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

    public Task unmarkAsDone() {
        isDone = false;
        return this;
    }

    public String toFileFormat() {
        String fileString = " | ";
        if (isDone) {
            return fileString + "1 | " + description;
        }
        return fileString + "0 | " + description;
    }

    @Override
    public String toString() {
        return "["+ getStatusIcon() + "] " + description;
    }
}