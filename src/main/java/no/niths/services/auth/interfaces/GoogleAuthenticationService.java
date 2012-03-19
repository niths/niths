package no.niths.services.auth.interfaces;

import org.springframework.social.google.api.Google;

public interface GoogleAuthenticationService {
	/**
	 * Authenticates user via Google
	 * @param token the token provided by Google
	 * @return the email from the Google profile
	 */
	public String authenticateAndGetEmail(String token);
	
	/**
	 * Authenticates user via Google and return their account
	 * 
	 * @param token the token provided by Google
	 * @return the Google account
	 */
	public Google authenticateAndGetGoogleAccount(String token);

}
