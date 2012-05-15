package no.niths.services.auth.interfaces;

import no.niths.domain.development.Application;
import no.niths.domain.development.Developer;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * <p>
 * Class responsible for fetching the student, application and the developer
 * holding the HTTP request.
 * </p>
 * <p>
 * Delegates the information to @see {@link AuthenticationService}. The actual
 * verification is handled in @see {@link AuthenticationService}
 * </p>
 * 
 */
public interface UserDetailService extends UserDetailsService {

	/**
	 * Implement your own version of this method to return UserDetails
	 * 
	 * See the other methods for a how to
	 * 
	 */
	@Override
	public UserDetails loadUserByUsername(String sessionToken)
			throws UsernameNotFoundException;

	/**
	 * Calls on the authentication service to get the authenticated user.
	 * 
	 * 
	 * @param sessionToken
	 *            the string to verify
	 * @return UserDetails the details of the student with belonging session
	 *         token
	 * @throws UsernameNotFoundException
	 *             when no student is found
	 * 
	 */
	UserDetails loadStudentBySessionToken(String sessionToken)
			throws UsernameNotFoundException;


	/**
	 * Calls on authentication service to authenticate the developer
	 * 
	 * @param developerToken
	 *            the developer token
	 * @param developerKey
	 *            the developer key
	 * @return id of the developer
	 * @throws UsernameNotFoundException
	 *             if no developer is found
	 * 
	 */
	Developer loadDeveloperFromDeveloperToken(String developerToken)
			throws UsernameNotFoundException;

	/**
	 * Calls on authentication service to authenticate the application
	 * 
	 * @param applicationKey
	 *            the application key
	 * @param applicationToken
	 *            the application token
	 * @return id of the application
	 * @throws UsernameNotFoundException
	 *             when no application is found
	 */
	Application loadApplicationFromApplicationToken(String appToken)
			throws UsernameNotFoundException;
}
