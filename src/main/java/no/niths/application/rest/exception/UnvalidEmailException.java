package no.niths.application.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public final class UnvalidEmailException extends AuthenticationException{

    public UnvalidEmailException(String msg) {
        super(msg);
        // TODO Auto-generated constructor stub
    }

    private static final long serialVersionUID = 344699966872664622L;


}