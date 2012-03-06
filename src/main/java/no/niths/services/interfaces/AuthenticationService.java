package no.niths.services.interfaces;

import no.niths.security.User;

@Deprecated
public interface AuthenticationService {

	/**
	 * Logs user in at google
	 * If not a valid token is provided,
	 * user is set to anonymous.
	 * 
	 * @param googleToken token to log in with google
	 * @return the authenticated user.
	 * 
	 */
	User login(String googleToken);
	
}
