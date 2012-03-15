package no.niths.services.auth.interfaces;

public interface GoogleAuthenticationService {
	/**
	 * Authenticates user via Google
	 * @param token the token provided by Google
	 * @return the email from the Google profile
	 */
	public String authenticateAndGetEmail(String token);

}
