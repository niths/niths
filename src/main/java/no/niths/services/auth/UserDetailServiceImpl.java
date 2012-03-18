package no.niths.services.auth;

import no.niths.security.User;
import no.niths.services.auth.interfaces.AuthenticationService;
import no.niths.services.auth.interfaces.UserDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
/**
 * Class responsible for feching the user holding the request  
 * 
 */
@Component
public class UserDetailServiceImpl implements UserDetailService {

	@Autowired
	private AuthenticationService authService;
	
	/**
	 * Calls on authentication to authenticate the session token
	 * 
	 * @return UserDetails the details of the student with belonging session token
	 * @throws UsernameNotFoundException when no student is found
	 */
	@Override
	public UserDetails loadUserByUsername(String sessionToken)
			throws UsernameNotFoundException {
		
		User user = authService.authenticateSessionToken(sessionToken);
		if(user == null){
			throw new UsernameNotFoundException("Could not find user with that sessiontoken");
		}
		
		return user;
	}
	

}
