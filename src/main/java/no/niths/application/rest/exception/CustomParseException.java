package no.niths.application.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class CustomParseException extends RuntimeException {

    private static final long serialVersionUID = -5857893002398236159L;

    public CustomParseException() {
        super();
    }

    public CustomParseException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public CustomParseException(final String message) {
        super(message);
    }

    public CustomParseException(final Throwable cause) {
        super(cause);
    }

}
