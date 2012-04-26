package no.niths.application.rest.auth.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.niths.domain.school.Student;
import no.niths.security.SessionToken;

/**
 * 
 * Login controller for the API
 * <p>
 * To log in, a access token from Google must be provided
 * </p>
 * 
 */
public interface RestLoginController {

	/**
	 * Authenticates the user through Google
	 * 
	 * @param token for authentication via Google
	 * @return Session token to use for login after authentication
	 */
	Student login(SessionToken token, HttpServletRequest req,
			HttpServletResponse res);

	/**
     * Logs out the student
     * 
     * @param studentId is of the student top log out
     */
	void logout(Long studentId);
	
}
