package no.niths.application.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason="Unvalid email")
public final class UnvalidEmailException extends RuntimeException {

    private static final long serialVersionUID = 344699966872664622L;

    public UnvalidEmailException() {
        super();
    }

    public UnvalidEmailException(
            final String message,
            final Throwable cause) {
        super(message, cause);
    }
    public UnvalidEmailException(final String message) {
        super(message);
    }
    public UnvalidEmailException(final Throwable cause) {
        super(cause);
    }

}