package no.niths.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.niths.services.interfaces.AuthenticationService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.filter.OncePerRequestFilter;

public class UserAuthFilter extends OncePerRequestFilter {

	Logger logger = org.slf4j.LoggerFactory.getLogger(UserAuthFilter.class);

	@Autowired
	private AuthenticationService authService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest req,
			HttpServletResponse res, FilterChain chain)
			throws ServletException, IOException {

		logger.info("Checking for token and calling Google to verify");
		try {
			String token = req.getHeader("token");
			if (token != null) {
				logger.info("Token was provided");
				User u = authService.login(token);
				setCurrentAuthenticatedUser(u);
			} else {
				logger.info("A token was not provided");
			}
		
		//If token is provided, but not correct			
		} catch (HttpClientErrorException httpe) {			
			logger.warn("Not a valid token");
		}
		chain.doFilter(req, res);
	}
	
	//TODO: Set user as anonymous if not authenticated
	//		prolly best to do so in authservice
	//		Not return null, rather a ano user
	private void setCurrentAuthenticatedUser(User u){
		if(u == null){
			logger.debug("User was not valid, auth anyway");
			u = new User();
		}		
		Authentication auth = 
				new UsernamePasswordAuthenticationToken(u, null, u.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);					
	}
	

}
