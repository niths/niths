package no.niths.application.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason="Expired token")
public final class ExpiredTokenException extends RuntimeException {

    private static final long serialVersionUID = 344699966872664622L;

    public ExpiredTokenException() {
        super();
    }

    public ExpiredTokenException(
            final String message,
            final Throwable cause) {
        super(message, cause);
    }
    public ExpiredTokenException(final String message) {
        super(message);
    }
    public ExpiredTokenException(final Throwable cause) {
        super(cause);
    }

}