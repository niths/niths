package no.niths.application.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        value = HttpStatus.NO_CONTENT,
        reason = "The given object has a null identifier")
public class IdentifierNullException extends RuntimeException {

    private static final long serialVersionUID = 4462886765420431421L;

    public IdentifierNullException() {
        super();
    }

    public IdentifierNullException(
            final String message,
            final Throwable cause) {
        super(message, cause);
    }

    public IdentifierNullException(final String message) {
        super(message);
    }

    public IdentifierNullException(final Throwable cause) {
        super(cause);
    }
}