package no.niths.services.auth;

import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.legacyprofile.LegacyGoogleProfile;
import org.springframework.stereotype.Service;

import no.niths.services.auth.interfaces.GoogleAuthenticationService;
/**
 * Authenticates a user through Google and are able to fetch their profiles
 * 
 */
@Service
public class GoogleAuthenticationServiceImpl implements GoogleAuthenticationService{

	/**
	 * Authenticates a user through Google and returns the profiles email
	 * 
	 * @param the token provided by Google
	 * @return string containg the users email
	 */
	@Override
	public String authenticateAndGetEmail(String token) {
		Google google = new GoogleTemplate(token);
		LegacyGoogleProfile profile = google.userOperations().getUserProfile();
		return profile.getEmail();
	}

	/**
	 * Authenticates user via Google and return their account
	 * 
	 * @param token the token provided by Google
	 * @return the Google account
	 */
	@Override
	public Google authenticateAndGetGoogleAccount(String token) {
		return new GoogleTemplate(token);
	}

}
