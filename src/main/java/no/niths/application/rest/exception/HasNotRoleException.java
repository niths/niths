package no.niths.application.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public final class HasNotRoleException extends RuntimeException {

    private static final long serialVersionUID = 344699966872664622L;

    public HasNotRoleException() {
        super("Has not role");
    }

    public HasNotRoleException(
            final String message,
            final Throwable cause) {
        super(message, cause);
    }
    public HasNotRoleException(final String message) {
        super(message);
    }
    public HasNotRoleException(final Throwable cause) {
        super(cause);
    }

}