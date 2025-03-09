package goon;

public class GoonException extends Exception {
    public GoonException() {}

    /**
     * Creates GoonException
     * @param message error message to be printed
     */
    public GoonException(String message) {
        super(message);
    }
}
