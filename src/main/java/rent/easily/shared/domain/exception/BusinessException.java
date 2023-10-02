package rent.easily.shared.domain.exception;

public class BusinessException extends RuntimeException {
    private String trace;

    public BusinessException(String message, String trace) {
        super(message);
        this.trace = trace;
    }

    public String getMessage() {
        return super.getMessage();
    }

    public String getTrace() {
        return this.trace;
    }
}