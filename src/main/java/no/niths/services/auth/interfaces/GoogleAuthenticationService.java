package no.niths.services.auth.interfaces;

public interface GoogleAuthenticationService {
	
	public String authenticateAndGetEmail(String token);

}
