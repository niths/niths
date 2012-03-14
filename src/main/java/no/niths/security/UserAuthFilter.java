package no.niths.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.niths.services.auth.interfaces.RequestAuthenticationService;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.filter.OncePerRequestFilter;
/**
 * Security filter that checks for session token and validates user
 *
 */
public class UserAuthFilter extends OncePerRequestFilter {

	Logger logger = org.slf4j.LoggerFactory.getLogger(UserAuthFilter.class);
	
	@Autowired
	private RequestAuthenticationService service;
	
	/**
	 * Handles the verification process
	 * Checks for "session-token" in the HTTP request header
	 * and authenticates the user
	 * 
	 * @param req the HttpServletRequest
	 * @param res the HttpServletResponse
	 * @param chain the Security filter chain
	 * @throws ServletException, IOException
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest req,
			HttpServletResponse res, FilterChain chain)
			throws ServletException, IOException {
		
		//////////////////////////
		//FOR EASIER TEST: Add roles if needed
		///////////////////////////
		logger.debug("TEST MODE AUTH!");
		
		User uu = new User("rosben09@nith.no");
		uu.setStudentId(new Long(3));
		uu.addRoleName("ROLE_STUDENT");
		setCurrentAuthenticatedUser(uu);
		
		chain.doFilter(req, res);
		///////////////////////////////
		//TEST MODE: OUTCOMMENT ALL BELOW
		////////////////////////////

		
		
//		logger.info("Checking for session token");
//		try {
//			String token = req.getHeader("session-token");
//			if (token != null) {
//				logger.info("Token was provided");
//				User u = service.authenticate(token);
//				setCurrentAuthenticatedUser(u);
//			} else {
//				logger.info("A token was not provided");
//			}
//		
//		//If token is provided, but not correct			
//		} catch (HttpClientErrorException httpe) {			
//			logger.warn("Not a valid token");
//		}
//		//Continue the security filter chain
//		chain.doFilter(req, res);
	}

	//Sets the current authenticated user
	//If user did not pass security check, user is anonymous
	private void setCurrentAuthenticatedUser(User u){
		if(u == null){
			u = new User(); //Role = ROLE_ANONYMOUS
		}		
		Authentication auth = 
				new UsernamePasswordAuthenticationToken(u, null, u.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);					
	}
	

}
