package no.niths.services.auth.interfaces;

import org.springframework.security.core.AuthenticationException;

/**
 * 
 * Service for generating and verifying tokens. Tokens are used in HTTP headers
 * when accessing the API from an application.
 * 
 * Tokens are encrypted and decrypted with {@link http://www.jasypt.org/} 
 * Passwords are from persistence.properties in res/main/resources
 *
 */

public interface TokenGeneratorService {
	
	/**
	 * Generates a token based on the userId
	 * 
	 * @param userId
	 *            id of the user
	 */
	String generateToken(Long id);
	
	/**
	 * Verifies the format of the token
	 * 
	 * @param token
	 *            to verify
	 * @throws AuthenticationException
	 */
	void verifyTokenFormat(String token);

}
