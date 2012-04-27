package no.niths.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Security filter that checks for session token and validates user
 * <p>
 * This filter runs after BasicAuthenticationFilter @see BasicAuthenticationFilter
 * and checks for developer key+token, key+application token and session-token
 * </p>
 * <p>
 * If no developer key+token and no application key+token is provided
 * in the request, the session-token will not be validated. In other words
 * Developer token+key and application token+key must be provided to access
 * restricted resources.
 * </p>
 * <p>
 * This class does not handle the actual verification, it only extracts the
 * tokens from the http request and passes them to the class responsible for
 * the verification @see {@link RequestAuthenticationProvider}
 * </p>
 * <p>
 * For an understanding of how Spring security works, I recommend the book:
 * "Spring security 3" from Peter Mularien {@link http://www.springsecuritybook.com/}
 * </p>
 * 
 * 
 */
public class RequestAuthenticationFilter extends OncePerRequestFilter {

	Logger logger = org.slf4j.LoggerFactory
			.getLogger(RequestAuthenticationFilter.class);

	@Autowired
	private RestAuthenticationEntryPoint entryPoint;

	@Autowired
	private RequestAuthenticationProvider authProvider;

	/**
	 * Handles the verification process.
	 * <p>
	 * Checks for authentication headers and based
	 * on the information authenticates the user
	 * <p>
	 * @param req
	 *            the HttpServletRequest
	 * @param res
	 *            the HttpServletResponse
	 * @param chain
	 *            the Security filter chain
	 * @throws ServletException
	 *             , IOException
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest req,
			HttpServletResponse res, FilterChain chain)
			throws ServletException, IOException {

		logger.debug("Incoming request, firing security filter;");
		// Checking if Basic Authentication has been set,
		// if not, we check for a Session-Token header
		Authentication currentAuth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (currentAuth == null) { // If basic auth has been populated,
									// do not check for session token
			logger.debug("No Basic Authentication header found");
			logger.debug("Starting request authentication process...");
			// Wrapper
			RequestAuthenticationInfo authInfo = new RequestAuthenticationInfo(
					new RequestHolderDetails());

			// Get the authorization headers
			String developerKey = req.getHeader("Developer-key");
			String developerToken = req.getHeader("Developer-token");
			String applicationKey = req.getHeader("Application-key");
			String applicationToken = req.getHeader("Application-token");

			logger.debug("HTTP headers have been processed.");
			
			if (developerKey != null && developerToken != null 
					&& applicationToken != null && applicationKey != null) {
				
				logger.debug("Developer key found: " + developerKey);
				logger.debug("Developer token found: " + developerToken);
				logger.debug("Application key found: " + applicationKey);
				logger.debug("Application token found: " + applicationToken);
				
				authInfo.setDeveloperKey(developerKey);
				authInfo.setDeveloperToken(developerToken);
				authInfo.setAppKey(applicationKey);
				authInfo.setAppToken(applicationToken);

				String sessionToken = req.getHeader("Session-token");
				if (sessionToken != null) {
					logger.debug("Session-token header found: " + sessionToken);
					authInfo.setSessionToken(sessionToken);
				}else{
					logger.debug("No session header found");
				}
				
				try {
					logger.debug("Calling authentication provider to authenticate the header(s)");

					// Let the authentication provider authenticate the request
					// Will throw AuthenticationException, so it is important
					// that every exception extends AuthenticationException.
					// Exceptions are catched in AbstractRestController.
					Authentication authResult = authProvider
							.authenticate(authInfo);

					logger.debug("Authentication success!");

					// Set the result as the authentication object
					SecurityContextHolder.getContext().setAuthentication(
							authResult);
				} catch (AuthenticationException ae) {

					logger.debug("Authentication failed for developer with key: " + developerKey);
					logger.debug("Authentication failed for developer with token: " + developerToken);
					logger.debug("Authentication failed for app with key: "+ applicationKey);
					logger.debug("Authentication failed for app with token: "+ applicationToken);
					
					if (sessionToken != null) {
						logger.debug("Authentication failed for session: "+ sessionToken);
					}
					
					// Login failed, clear authentication object
					SecurityContextHolder.getContext().setAuthentication(null);
					// We send the error to the entry point
					entryPoint.commence(req, res, ae);
				}
				
				
			}else{
				logger.debug("Could not find required headers(Developer/Application), authentication process ends...");
			}
		}
		
		logger.debug("Continuing spring security filter chain");
		chain.doFilter(req, res);
	}

	public RequestAuthenticationProvider getAuthProvider() {
		return authProvider;
	}

	public void setAuthProvider(RequestAuthenticationProvider authProvider) {
		this.authProvider = authProvider;
	}

	public RestAuthenticationEntryPoint getEntryPoint() {
		return entryPoint;
	}

	public void setEntryPoint(RestAuthenticationEntryPoint entryPoint) {
		this.entryPoint = entryPoint;
	}

}
