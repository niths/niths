package no.niths.application.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.OK)
public class ObjectInCollectionException extends RuntimeException {

	private static final long serialVersionUID = -1326132284806863871L;

	public ObjectInCollectionException() {
		super("Object in collection");
	}

	public ObjectInCollectionException(final String message,
			final Throwable cause) {
		super(message, cause);
	}

	public ObjectInCollectionException(final String message) {
		super(message);
	}

	public ObjectInCollectionException(final Throwable cause) {
		super(cause);
	}

}
