package no.niths.services.auth.interfaces;

import org.springframework.social.google.api.Google;
/**
 * Authenticates a user through Google and are able to fetch their profiles
 *
 */
public interface GoogleAuthenticationService {
    /**
     * Authenticates a user through Google and returns the profiles email
     * @param token the token provided by Google
     * @return the email from the Google profile as a string
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
