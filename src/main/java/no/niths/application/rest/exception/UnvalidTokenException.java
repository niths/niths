package no.niths.application.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason="Unvalid token")
public final class UnvalidTokenException extends RuntimeException {

    private static final long serialVersionUID = 344699966872664622L;

    public UnvalidTokenException() {
        super();
    }

    public UnvalidTokenException(
            final String message,
            final Throwable cause) {
        super(message, cause);
    }
    public UnvalidTokenException(final String message) {
        super(message);
    }
    public UnvalidTokenException(final Throwable cause) {
        super(cause);
    }

}