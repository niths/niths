package no.niths.infrastructure;

import no.niths.user.SecurityContext;
import no.niths.user.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.legacyprofile.LegacyGoogleProfile;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl {

	private static final Logger logger = LoggerFactory
			.getLogger(AuthenticationServiceImpl.class);
	
	public String login(String googleToken){
		
		Google google = new GoogleTemplate(googleToken);
		LegacyGoogleProfile profile = google.userOperations().getUserProfile();
		String userEmail = profile.getEmail();
		logger.debug("User logging in: " + userEmail);
		if(userEmail.endsWith("nith.no")){
			logger.debug("Name: " + profile.getName());

			SecurityContext.setCurrentUser(new User(userEmail));
			
			//TODO: fetch user, persist if non existing
			return "logged in: " + userEmail;
		}else{
			return "User not a student @nith (Log in with your nith mail)";
		}

	}
}
