package lk.ijse.dep8.note.service.exception;

public class UnauthorizedAccessException extends RuntimeException{
    public UnauthorizedAccessException(String message) {
        super(message);
    }

    public UnauthorizedAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthorizedAccessException(Throwable cause) {
        super(cause);
    }
}
