package no.niths.services.interfaces.auth;

import no.niths.security.User;
/**
 * Authenticates user trying to request a resource
 */
public interface RequestAuthenticationService {

	/**
	 * Fetch student that belongs to session token
	 * and returns a user object with roles of the student
	 * 
	 * @param sessionToken access token
	 * @return a user object with roles
	 */
	User authenticate(String sessionToken);
}
