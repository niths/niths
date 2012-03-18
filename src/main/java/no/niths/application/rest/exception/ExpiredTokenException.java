package no.niths.application.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public final class ExpiredTokenException extends AuthenticationException {

    public ExpiredTokenException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 344699966872664622L;

}