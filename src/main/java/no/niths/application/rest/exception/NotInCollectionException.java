package no.niths.application.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public final class NotInCollectionException extends RuntimeException {

    private static final long serialVersionUID = 344699966872664622L;

    public NotInCollectionException() {
        super("Not in collection");
    }

    public NotInCollectionException(
            final String message,
            final Throwable cause) {
        super(message, cause);
    }
    public NotInCollectionException(final String message) {
        super(message);
    }
    public NotInCollectionException(final Throwable cause) {
        super(cause);
    }

}