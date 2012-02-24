package no.niths.security;

import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.legacyprofile.LegacyGoogleProfile;
import org.springframework.stereotype.Component;

@Component
public class GoogleProfileFetcher {
	
	public LegacyGoogleProfile getProfile(String token){
		Google google = new GoogleTemplate(token);
		LegacyGoogleProfile profile = google.userOperations().getUserProfile();
		return profile;
	}

}
