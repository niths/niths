package no.niths.services.auth.interfaces;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
/**
 * Class responsible for feching the user holding the request  
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
	 * @param sessionToken the string to verify
	 * @return UserDetails the details of the student with belonging session token
	 * @throws UsernameNotFoundException when no student is found
	 * 
	 */
	UserDetails loadStudentBySessionToken(String sessionToken)
			throws UsernameNotFoundException;
	
	/**
	 * Calls on authentication service to authenticate the developer
	 * 
	 * @param developerToken the string to verify
	 * @return id of the developer
	 * @throws UsernameNotFoundException if no developer is found
	 * 
	 */
	Long loadDeveloperIdFromDeveloperToken(String developerToken) throws UsernameNotFoundException;
	
	/**
	 * Calls on authenticationservice to authenticate the application
	 * 
	 * @param applicationToken string to verify
	 * @return id of the belonging app
	 * @throws UsernameNotFoundException of no app is found
	 */
	Long loadApplicationIdFromApplicationToken(String applicationToken) throws UsernameNotFoundException;
}
