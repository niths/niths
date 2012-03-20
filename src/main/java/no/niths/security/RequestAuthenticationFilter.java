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
 * 
 */
public class RequestAuthenticationFilter extends OncePerRequestFilter {

	Logger logger = org.slf4j.LoggerFactory.getLogger(RequestAuthenticationFilter.class);

	@Autowired
	private RestAuthenticationEntryPoint entryPoint;

	@Autowired
	private RequestAuthenticationProvider authProvider;


	/**
	 * Handles the verification process Checks for "session-token" in the HTTP
	 * request header and authenticates the user
	 * 
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
		
		//Checking if Basic Authentication has been set, 
		//if not, we check for a Session-Token header
		Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
		if (currentAuth == null) { //If basic auth has been populated, do not check for session token
			String header = req.getHeader("Session-token"); // TODO: set as constant

			if (header != null) {
				logger.debug("Session-token header found: " + header);
				try {
					//Create a authorization request with the header(session-token)
					AuthenticationSessionToken authRequest = new AuthenticationSessionToken(
							null, null, header);
					logger.debug("Starting authentication process...");
					//Let the authentication provider authenticate the request
					//Will throw AuthenticationException, so it is important that
					//every exception extends AuthenticationException
					Authentication authResult = authProvider.authenticate(authRequest);
					logger.debug("Authentication success");
					//Set the result as the authentication object
					SecurityContextHolder.getContext().setAuthentication(authResult);
				} catch (AuthenticationException ae) {
					logger.debug("Authentication failed for session-token: "
							+ header);
					// Login failed, clear authentication object
					SecurityContextHolder.getContext().setAuthentication(null);
					// We send the error to the entry point
					entryPoint.commence(req, res, ae);

				}
			}
		}
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
