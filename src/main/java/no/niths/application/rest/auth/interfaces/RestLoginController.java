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
 * For the URL too RestLogin add /auth
 * after the {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}
 */
public interface RestLoginController {

	/**
	 * Authorize the user. Use the returned session token for future requests
     *
     * Too login add /login
     * too the URL
     *
     * Use the POST method
	 * 
	 * @param token for authentication via Google
	 * @return encrypted session token valid for
     *   (See AppNames.SESSION_VALID_TIME)
	 */
	Student login(SessionToken token, HttpServletRequest req,
			HttpServletResponse res);

	/**
     * Logs out the student
     *
     * Too logout add /logout/{studentId}
     * too the URL
     *
     * Use the POST method
     * 
     * @param studentId is of the student top log out
     */
	void logout(Long studentId);
	
}
