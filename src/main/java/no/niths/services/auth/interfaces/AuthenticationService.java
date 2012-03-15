package no.niths.services.auth.interfaces;

import no.niths.security.User;
/**
 * Authenticates user trying to request a resource
 */
public interface AuthenticationService {

	/**
	 * Fetch student that belongs to session token
	 * and returns a user object with roles of the student
	 * 
	 * @param sessionToken access token
	 * @return a user object with roles
	 */
	User authenticateSessionToken(String sessionToken);
	
	String authenticateAtGoogle(String token);
}
