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
			} else {
				logger.info("A token was not provided");
			}

		} catch (HttpClientErrorException httpe) {
			logger.warn("Not a valid token");
		}
		//DOES NOT WORK
		doAutoLogin("tom", "jane");
		
		//IGNORE:
		//Set user as authenticated, no matter what...
		//TODO: Based on response from service, set user false or true
		//SecurityContextHolder.getContext().getAuthentication().setAuthenticated(true);
//		UserAuth user = new UserAuth();
//		user.setAuthenticated(true);
//		SecurityContextHolder.getContext().setAuthentication(user);
		//END IGNORE
		
		
		// Will we continue the security chain?
		// That means continue to another verification process
		//chain.doFilter(req, res);
	}
	
	private void doAutoLogin(String username, String password) {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password );
	    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

	}

}
