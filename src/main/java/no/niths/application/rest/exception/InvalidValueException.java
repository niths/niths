package no.niths.application.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidValueException extends  RuntimeException {

    private static final long serialVersionUID = 344699966872664622L;

    public InvalidValueException() {
        super("Invalid Value");
    }

    public InvalidValueException(
            final String message,
            final Throwable cause) {
        super(message, cause);
    }
    public InvalidValueException(final String message) {
        super(message);
    }
    public InvalidValueException(final Throwable cause) {
        super(cause);
    }
}

