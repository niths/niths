package no.niths.services.interfaces.auth;

public interface GoogleAuthenticationService {
	
	public String authenticateAndGetEmail(String token);

}
