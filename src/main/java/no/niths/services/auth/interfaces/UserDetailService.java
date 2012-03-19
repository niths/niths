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
	 * Calls on the authentication service to get the authenticated user.
	 * 
	 * 
	 * @param sessionToken the string to get belong
	 * @return UserDetails the details of the student with belonging session token
	 * @throws UsernameNotFoundException when no student is found
	 * 
	 */
	@Override
	public UserDetails loadUserByUsername(String sessionToken)
			throws UsernameNotFoundException;
}
