public class ToDo extends Task{
    public ToDo(String description){
        super(description);
    }

    @Override
    public String toFileFormat() {
        return "\nT" + super.toFileFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
