package no.niths.application.rest.auth;

import javax.servlet.http.HttpServletResponse;

import no.niths.application.rest.RESTConstants;
import no.niths.application.rest.auth.interfaces.RestLoginController;
import no.niths.common.AppConstants;
import no.niths.security.SessionToken;
import no.niths.services.auth.interfaces.AuthenticationService;

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

	/**
	 * Authorize the user. Use the returned session token for future requests
	 * 
	 * @param token The token issued from google
	 * @return encrypted session token valid for (See AppConstants.SESSION_VALID_TIME)
	 * 
	 */
	@Override
	@RequestMapping(value = { "login/{token:.+}" }, method = RequestMethod.POST, headers = RESTConstants.ACCEPT_HEADER)
	@ResponseBody
	public SessionToken login(@PathVariable String token) {
		logger.info("A user wants to be authenticated with token: " + token);
		if(token != null){
			return service.authenticateAtGoogle(token);
		}
		return new SessionToken();
	}
	
	
	
	@ExceptionHandler(HttpClientErrorException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public void notAuthorized(HttpClientErrorException e, HttpServletResponse res) {
		res.setHeader("Error", "Token not valid");
	}

}
