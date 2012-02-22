package no.niths.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.niths.services.AuthenticationServiceImpl;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.filter.OncePerRequestFilter;

public class UserAuthFilter extends OncePerRequestFilter {

	Logger logger = org.slf4j.LoggerFactory.getLogger(UserAuthFilter.class);

	@Autowired
	private AuthenticationServiceImpl authService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest req,
			HttpServletResponse res, FilterChain chain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("Checking for token and calling Google to verify");
		try {
			String token = req.getHeader("token");
			if (token != null) {
				logger.info("Calling the service with a token");
				
				logger.info("Response from service: "
						+ authService.login(token));
				
				//TODO: SET USER AS AUTHENTICATED
				//Its done in the catch block now
			
			} else {
				logger.info("A token was not provided");
			}
		
		//If token is provided, but not correct			
		} catch (HttpClientErrorException httpe) {
			//Creates a user and log it in (for test purposes)
			User u = new User();
			Authentication auth = 
					new UsernamePasswordAuthenticationToken(u, null, u.getAuthorities());
			//Set user as authenticated
			SecurityContextHolder.getContext().setAuthentication(auth);
			
			logger.warn("Not a valid token");
		}
		// Will we continue the security chain?
		chain.doFilter(req, res);
	}
	

}
