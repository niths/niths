package no.niths.application.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class LoanException extends RuntimeException{

	private static final long serialVersionUID = -2320201931656249577L;

	public LoanException(){
        super();
    }

    public LoanException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public LoanException(final String message) {
        super(message);
    }

    public LoanException(final Throwable cause) {
        super(cause);
    }
}

