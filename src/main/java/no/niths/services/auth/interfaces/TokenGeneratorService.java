package no.niths.services.auth.interfaces;


public interface TokenGeneratorService {
	
	String generateToken(Long id);
	
	void verifyTokenFormat(String token);

}
