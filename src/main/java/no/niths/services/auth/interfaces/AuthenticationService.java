package no.niths.services.auth.interfaces;

import no.niths.domain.Application;
import no.niths.domain.Developer;
import no.niths.security.ApplicationToken;
import no.niths.security.DeveloperToken;
import no.niths.security.RequestHolderDetails;
import no.niths.security.SessionToken;

import org.springframework.security.core.AuthenticationException;
/**
 * Authenticates user trying to request a resource
 */
public interface AuthenticationService {
	
	/**
	 * Authenticates the session token from a request. 
	 * <p>
	 * Uses a TokenGeneratorService to verify the format of the token and any
	 * errors will throw an AuthenticationException with an "Error" header.
	 * If format is verified we then fetches belonging student from DB.
	 * <p>
	 * We then create a User wrapper object with roles copied from the student and
	 * return that user to the class responsible for doing the actual authentication.
	 * 
	 * @param sessionToken 	the string to verify. If not correct, an Authentication
	 * 						exception will occur with an "Error header" explaining
	 * 						the issue.
	 * @return a user object with roles from student belonging to the session
	 *         token
	 */
	RequestHolderDetails authenticateSessionToken(String sessionToken);
	
	/**
	 * Authenticates a student via Google. If authentication succeeds, student
	 * is either fetched from DB or if the student is a first time user, he/she
	 * gets persisted.
	 * <p>
	 * Returns a session token valid for {@value AppConstants.SESSION_VALID_TIME}
	 * minutes Use this session token for future requests against the API
	 * <p>
	 * How to use:
	 * <pre>
	 * {@code
	 * Place in header:
	 * Session-token: ojejcndiu23io2hjUILHDSDW21.wqi8h2
	 * Accept: Application/xml
	 * }
	 * </pre>
	 * @param googleToken	the string to authenticate. If null, or not correct
	 * 						a 401 will be in the response.
	 * 
	 * @return SessionToken the string to use in future requests against the 
	 * 						API. It is valid for {@value SecurityConstants.SESSION_VALID_TIME} ms.
	 * 						Max concurrent session is {@value SecurityConstants.MAX_SESSION_VALID_TIME} ms.
	 * 						
	 */
	SessionToken authenticateAtGoogle(String token);
	
	/**
	 * Register a developer and generates a developer token that the
	 * developer uses in future requests
	 * <p>
	 * @param developer the developer to persist
	 * @return DeveloperToken the token and a confirmation message 
	 */
	DeveloperToken registerDeveloper(Developer dev);
	
	/**
	 * Enables a developer
	 * <p>
	 * Developer must exist in the DB, or else enabling will fail...
	 * <p>
	 * @param developerToken string return from registerDeveloper(Dev)
	 * @return the developer object, null if not found
	 */
	Developer enableDeveloper(String developerToken);
	
	/**
	 * Verifies the format and fetches matching developer from DB
	 * <p>
	 * @param devToken token to verify
	 * @return the developer id
	 * @deprecated use authenticateDeveloperToken(String devToken, String devKey)
	 */
	@Deprecated
	Long authenticateDeveloperToken(String devToken);
	
	/**
	 * Registers an application to the matching developer
	 * <p>
	 * @param app the application to add
	 * @param devId id of the dev to add application to
	 * @return an application token to use in furture requests
	 * 
	 */
	ApplicationToken registerApplication(Application app, String developerKey);
	
	/**
	 * Verifies the format and fetches matching developer from DB
	 * <p>
	 * @param devToken token to verify
	 * @return the developer id
	 * 
	 */
	@Deprecated
	Long authenticateApplicationToken(String appToken) throws AuthenticationException;

	/**
	 * Authenticates the developer token. Verifies the format of the token and 
	 * and fetches matching student from DB based on the key. Then checks if 
	 * developer token is correct
	 * <p>
	 * @param devToken the developer token
	 * @param devKey the developer key
	 * @throws AuthenticationException if no matching student is found
	 * 
	 */
	Long authenticateDeveloperToken(String devToken, String devKey)
			throws AuthenticationException;

	/**
	 * Authenticates the application token.
	 * <p>
	 * Verifies the token format and, based on the key,
	 * fetches the matching application from DB,
	 * if it is enabled.
	 * <p>
	 * @param applicationKey the application key
	 * @param applicationToken the application token
	 * @return id of the application
	 * @throws AuthenticationException if no matching app is found
	 */
	Long authenticateApplicationToken(String applicationKey,
			String applicationToken) throws AuthenticationException;

	/**
	 * 
	 * Enables an application
	 * 
	 * @param applicationKey 
	 * @return the Application
	 * @throws AuthenticationException
	 */
	Application enableApplication(String applicationKey)
			throws AuthenticationException;
}
