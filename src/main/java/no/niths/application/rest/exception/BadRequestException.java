package no.niths.application.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public final class BadRequestException extends RuntimeException{

    private static final long serialVersionUID = 2962462423679006339L;

    public BadRequestException(){
        super();
    }

    public BadRequestException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public BadRequestException(final String message) {
        super(message);
    }

    public BadRequestException(final Throwable cause) {
        super(cause);
    }
}