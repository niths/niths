package no.niths.security;

import no.niths.application.rest.exception.UnvalidTokenException;
import no.niths.services.auth.interfaces.UserDetailService;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
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
		logger.debug("Authentication provider handling the authentication object");
		try {

			RequestAuthenticationInfo authInfo = (RequestAuthenticationInfo) authentication;
			RequestHolderDetails userInfo = new RequestHolderDetails();

			Long devId = null; // ID of the developer holding the request
			Long appId = null; // ID of the app holding the request
			
			if (authInfo.getDeveloperToken() == null) {
				logger.warn("No developer token found in authentication");
				throw new UnvalidTokenException("No developer token found");

			} else { // Proceed to verify the developer token

				if (authInfo.getAppToken() == null) {
					throw new UnvalidTokenException(
							"Application token not found");
				}
				logger.debug("Authentication provider found developer-token: "
						+ authInfo.getDeveloperToken());
				
				devId = userDetailService
						.loadDeveloperIdFromDeveloperToken(authInfo
								.getDeveloperToken());

				logger.debug("Authentication provider found Application-token: "
						+ authInfo.getAppToken());
				
				appId = userDetailService
						.loadApplicationIdFromApplicationToken(authInfo
								.getAppToken());

				// We found dev and app token, they have been authenticated,
				// proceed to check for a session token
				if (authInfo.getSessionToken() != null) {
					logger.debug("Authentication provider found Session-token: "
							+ authInfo.getSessionToken());

					// Get a user that holds the student matching the session
					// token
					userInfo = (RequestHolderDetails) userDetailService
							.loadStudentBySessionToken(authInfo
									.getSessionToken());
				}

				userInfo.setDeveloperId(devId);
				userInfo.setAppId(appId);

				authInfo = new RequestAuthenticationInfo(userInfo,
						userInfo.getAuthorities());
				logger.debug("Authication provider has finished successfully");
				logger.debug("Sending a RequestAuthenticationInfo object back to the request filter");
				return authInfo;
			}

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
		return authentication.equals(RequestAuthenticationInfo.class);

	}

	public UserDetailService getUserDetailService() {
		return userDetailService;
	}

	public void setUserDetailService(UserDetailService userDetailService) {
		this.userDetailService = userDetailService;
	}

}
