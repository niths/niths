package no.niths.services.auth;

import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.legacyprofile.LegacyGoogleProfile;
import org.springframework.stereotype.Service;

import no.niths.services.interfaces.auth.GoogleAuthenticationService;

@Service
public class GoogleAuthenticationServiceImpl implements GoogleAuthenticationService{

	@Override
	public String authenticateAndGetEmail(String token) {
		Google google = new GoogleTemplate(token);
		LegacyGoogleProfile profile = google.userOperations().getUserProfile();
		return profile.getEmail();
	}

}
