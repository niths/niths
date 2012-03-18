package no.niths.services.auth.interfaces;

import no.niths.security.SessionToken;
import no.niths.security.User;
/**
 * Authenticates user trying to request a resource
 */
public interface AuthenticationService {
	
	/**
	 * Authenticates the session token from a request Verifies the format of the
	 * token, then fetches belonging student from DB. We then create a User
	 * wrapper object with roles from the student and return that object to the
	 * class responsible for setting the authenticated user.
	 * 
	 * @param sessionToken
	 *            the token to verify
	 * @return a user object with roles from student belonging to the session
	 *         token
	 */
	User authenticateSessionToken(String sessionToken);
	
	/**
	 * Authenticates a student via Google. If authentication succeeds, student
	 * is either fetched from DB or if the student is a first time user, he/she
	 * gets persisted.
	 * 
	 * Returns a session token valid for (see AppConstants.SESSION_VALID_TIME)
	 * minutes Use this session token for future requests against the API
	 * 
	 * @param googleToken
	 *            the token issued from google
	 * @return session token to use in future request
	 */
	SessionToken authenticateAtGoogle(String token);
}
