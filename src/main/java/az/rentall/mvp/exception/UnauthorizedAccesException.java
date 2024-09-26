package az.rentall.mvp.exception;

public class UnauthorizedAccesException extends RuntimeException {
    public UnauthorizedAccesException(String message) {
        super(message);
    }
}
