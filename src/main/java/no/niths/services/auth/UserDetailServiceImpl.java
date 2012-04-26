package no.niths.services.auth;

import no.niths.security.RequestHolderDetails;
import no.niths.services.auth.interfaces.AuthenticationService;
import no.niths.services.auth.interfaces.UserDetailService;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
/**
 * <p>
 * Class responsible for fetching the student, application and
 * the developer holding the HTTP request.
 * </p>
 * <p>
 * Delegates the information to @see {@link AuthenticationService}.
 * The actual verification is handled in @see {@link AuthenticationService}
 * </p>
 * 
 */
@Component
public class UserDetailServiceImpl implements UserDetailService {
	
	Logger logger = org.slf4j.LoggerFactory
			.getLogger(UserDetailServiceImpl.class);

	@Autowired
	private AuthenticationService authService;
	
	/**
	 * Implement your own version of this method to return UserDetails
	 * 
	 * See the other methods for a how to
	 * 
	 */
	@Override
	public UserDetails loadUserByUsername(String sessionToken)
			throws UsernameNotFoundException {
		
		return null;
	}
	

	/**
	 * Calls on authentication to authenticate the session token
	 * 
	 * @return UserDetails the details of the student with belonging session token
	 * @throws UsernameNotFoundException when no student is found
	 */
	@Override
	public UserDetails loadStudentBySessionToken(String sessionToken) throws UsernameNotFoundException{
		RequestHolderDetails user = authService.authenticateSessionToken(sessionToken);
		if(user == null){
			throw new UsernameNotFoundException("Could not find user with that sessiontoken");
		}
		logger.debug("Found student in UserDetailService");
		return user;
	}
	
	/**
	 * Calls on authentication service to authenticate the developer
	 * 
	 * @param developerToken the developer token
	 * @param developerKey the developer key
	 * @return id of the developer
	 * @throws UsernameNotFoundException if no developer is found
	 * 
	 */
	@Override
	public Long loadDeveloperIdFromDeveloperKey(String developerKey, String developerToken) throws UsernameNotFoundException{
		Long id = authService.authenticateDeveloperToken(developerToken, developerKey);
		if(id == null){
			throw new UsernameNotFoundException("Could not find a developer with that developer token/key");
		}
		logger.debug("Found developer in UserDetailService");
		return id;
	}
	
	/**
	 * Calls on authentication service to authenticate the application
	 * 
	 * @param applicationKey the application key
	 * @param applicationToken the application token
	 * @return id of the application
	 * @throws UsernameNotFoundException when no application is found
	 */
	@Override
	public Long loadApplicationIdFromApplicationKey(String applicationKey, String applicationToken) throws UsernameNotFoundException{
		Long id = authService.authenticateApplicationToken(applicationKey, applicationToken);
		if(id == null){
			throw new UsernameNotFoundException("Could not find a application with that token/key");
		}
		logger.debug("Found application in UserDetailService");
		return id;
	}
	

}
