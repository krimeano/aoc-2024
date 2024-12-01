package days.my_lib;

public class NoDayException extends Exception {
    public NoDayException() {
        super();
    }

    public NoDayException(String message) {
        super(message);
    }
}
