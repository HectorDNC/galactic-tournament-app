package tech.galactictournament.config.exceptions;


import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ErrorMessage {
    private String exception;
    private String message;
    private String path;

    public ErrorMessage(Exception exception, String path) {
        this.exception = exception.getClass().getSimpleName();
        this.message = resolveMessage(exception);
        this.path = path;
    }

    private String resolveMessage(Exception exception) {
        String exceptionMessage = exception.getMessage();

        if (exceptionMessage != null && !exceptionMessage.isBlank()) {
            return exceptionMessage;
        }

        Throwable cause = exception.getCause();
        if (cause != null && cause.getMessage() != null && !cause.getMessage().isBlank()) {
            return cause.getMessage();
        }

        return exception.getClass().getSimpleName();
    }
}
