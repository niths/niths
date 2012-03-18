package no.niths.application.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason="Unvalid token")
public final class UnvalidTokenException extends AuthenticationException {

    public UnvalidTokenException(String msg) {
		super(msg);
	}

	private static final long serialVersionUID = 344699966872664622L;

   

}