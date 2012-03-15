package no.niths.application.rest.auth.interfaces;

import no.niths.security.SessionToken;

/**
 * Login controller for Google login
 * 
 */
public interface RestLoginController {

	/**
	 * Authenticates the user through Google
	 * 
	 * @param token for authentication via Google
	 * @return Session token to use for login after authentication
	 */
	public SessionToken login(String token);
	
}
