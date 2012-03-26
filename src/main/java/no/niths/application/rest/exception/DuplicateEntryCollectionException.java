package no.niths.application.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public final class DuplicateEntryCollectionException extends RuntimeException {

    private static final long serialVersionUID = 344699966872664622L;

    public DuplicateEntryCollectionException() {
        super("Already in collection");
    }

    public DuplicateEntryCollectionException(
            final String message,
            final Throwable cause) {
        super(message, cause);
    }
    public DuplicateEntryCollectionException(final String message) {
        super(message);
    }
    public DuplicateEntryCollectionException(final Throwable cause) {
        super(cause);
    }

}