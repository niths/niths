package no.niths.security;

import no.niths.application.rest.exception.UnvalidTokenException;
import no.niths.services.auth.interfaces.UserDetailService;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * Authenticates requests
 *
 */
public class RequestAuthenticationProvider implements AuthenticationProvider {

	Logger logger = org.slf4j.LoggerFactory
			.getLogger(RequestAuthenticationProvider.class);

	@Autowired
	private UserDetailService userDetailService;

	/**
	 * Fetches the Student belonging to the session token and
	 * populates the authentication object with the student roles
	 * 
	 * @param authentication the current authentication object
	 * @return a new authentication object
	 * @throws AuthenticationException when authentication fails
	 */
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		logger.debug("Authentication manager handling the authentication object");
		
		try {
			//Get the session token from the authentication object
			AuthenticationSessionToken signedToken = (AuthenticationSessionToken) authentication;
			logger.debug("Session-token:" + signedToken.getToken());
			//Get a user that holds the student matching the session token
			User user = (User) userDetailService.loadUserByUsername(signedToken.getToken());
			//Create new authentication object with the studentid and the roles
			Authentication auth = new UsernamePasswordAuthenticationToken(user, null,user.getAuthorities());
			return auth;
		} catch (ClassCastException cce) {
			logger.warn("Could not cast the authentication object");
			throw new UnvalidTokenException(
					"Can not cast the authentication object");
		}
	}

	/**
	 * Returns true if the authentication class is supported
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(AuthenticationSessionToken.class);

	}

	public UserDetailService getUserDetailService() {
		return userDetailService;
	}

	public void setUserDetailService(UserDetailService userDetailService) {
		this.userDetailService = userDetailService;
	}

}
