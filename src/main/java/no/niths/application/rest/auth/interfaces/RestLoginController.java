package no.niths.application.rest.auth.interfaces;

import no.niths.security.SessionToken;

/**
 * Login controller for the API
 * 
 */
public interface RestLoginController {

	/**
	 * Authenticates the user through Google
	 * 
	 * @param token for authentication via Google
	 * @return Session token to use for login after authentication
	 */
	SessionToken login(String token);
	
}
