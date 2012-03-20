package no.niths.security;

import no.niths.application.rest.exception.UnvalidTokenException;
import no.niths.services.auth.interfaces.UserDetailService;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;

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
	 * Fetches the Student belonging to the session token and populates the
	 * authentication object with the student roles
	 * 
	 * @param authentication
	 *            the current authentication object
	 * @return a new authentication object
	 * @throws AuthenticationException
	 *             when authentication fails
	 */
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		logger.debug("Authentication manager handling the authentication object");
		try {
//			Authentication auth = new UsernamePasswordAuthenticationToken(null, null,null);
			
			RequestAuthenticationInfo authInfo = (RequestAuthenticationInfo) authentication;
			RequestHolderDetails userInfo = new RequestHolderDetails();
			Long devId = null;
			
			//First check if developer token was provided,
			//if so, get the developer id.
			//The authentication provider handles the process and will
			//throw an exception if no matching developer is found
			if (authInfo.getDeveloperToken() != null) {
				devId = userDetailService.loadDeveloperIdFromDeveloperToken(authInfo.getDeveloperToken());
			} 
			
			//If a session token was provided, get the student and his roles
			//wrapped in a RequestHolderDetails object
			if (authInfo.getSessionToken() != null) {
				logger.debug("Authentication provider found Session-token: " + authInfo.getSessionToken());

				// Get a user that holds the student matching the session token
				userInfo = (RequestHolderDetails) userDetailService
						.loadStudentBySessionToken(authInfo.getSessionToken());
				
			}
			
			if(devId != null){
				userInfo.setDeveloperId(devId);
			}
			authInfo = new RequestAuthenticationInfo(userInfo, userInfo.getAuthorities());

			return authInfo;

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
