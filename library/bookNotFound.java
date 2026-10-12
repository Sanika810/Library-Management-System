package library;

public class bookNotFound extends Exception {
    public bookNotFound(String message) {
        super(message);
    }
}