package no.niths.application.rest.auth;

import no.niths.application.rest.RESTConstants;
import no.niths.application.rest.auth.interfaces.RestLoginController;
import no.niths.common.AppConstants;
import no.niths.security.Token;
import no.niths.services.auth.interfaces.AuthenticationService;
//import no.niths.services.auth.interfaces.RestLoginService;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Google authorization controller
 *
 */
@Controller
@RequestMapping(AppConstants.AUTH)
public class RestLoginControllerImpl implements RestLoginController{
	
	Logger logger = org.slf4j.LoggerFactory
			.getLogger(RestLoginControllerImpl.class);
	
	@Autowired
	private AuthenticationService service;
//	@Autowired
//	private RestLoginService service;

	/**
	 * Authorize the user. Use the returned session token for future requests
	 * 
	 * @param token The token issued from google
	 * @return encrypted session token valid for (See AppConstants.SESSION_VALID_TIME)
	 * 
	 */
	@Override
	@RequestMapping(value = { "/{token:.+}" }, method = RequestMethod.GET, headers = RESTConstants.ACCEPT_HEADER)
	@ResponseBody
	public Token login(@PathVariable String token) {
		logger.info("A user wants to be authenticated with token: " + token);
		Token temp = new Token(); //Wrapper class for response to user
		if(token != null){
			temp.setToken(service.login(token));
		}
		return temp;
	}
	
	@ExceptionHandler(HttpClientErrorException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Token not valid")
	public void notAuthorized() {
	}

}
