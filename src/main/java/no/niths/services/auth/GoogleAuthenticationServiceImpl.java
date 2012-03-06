package no.niths.services.auth;

import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.legacyprofile.LegacyGoogleProfile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import no.niths.common.AppConstants;
import no.niths.domain.Student;
import no.niths.infrastructure.interfaces.StudentRepository;
import no.niths.services.interfaces.auth.GoogleAuthenticationService;

@Service
@Transactional
/**
 * Authenticates user via Google
 */
public class GoogleAuthenticationServiceImpl implements GoogleAuthenticationService{

	private static final Logger logger = LoggerFactory
			.getLogger(GoogleAuthenticationServiceImpl.class);
	
	@Autowired
	private StudentRepository studRepo;
	
	@Override
	public String login(String token) {
		String generatedToken = "Not a valid token provided"; //Token parameter was not valid!
		//Authenticate via Google
		Google google = new GoogleTemplate(token);
		LegacyGoogleProfile profile = google.userOperations().getUserProfile();
		String userEmail = profile.getEmail();
		//Check if user has valid email ("nith.no")
		if(isUserValid(userEmail)){
			
			Student authenticatedStudent = studRepo.getStudentByEmail(userEmail);
			
			if(authenticatedStudent == null){ //First time user, persist!
				//TODO: Catch errors if email not valid
				authenticatedStudent = new Student(userEmail);
				Long id = studRepo.create(authenticatedStudent);
				authenticatedStudent.setId(id); //Unneeded? TODO: test!
			}
			
			//Generate "session token" that student uses from now on
			generatedToken = generateToken(authenticatedStudent.getId());
			authenticatedStudent.setSessionToken(generatedToken);
			studRepo.update(authenticatedStudent);//Unneeded? TODO: test!
		}
		
		return generatedToken;
	}
	//Generates a token
	//TODO: hash, and generate a real token
	private String generateToken(Long userId){
		long tokenIssued = new GregorianCalendar().getTimeInMillis();
		return "t-id:" + Long.toString(userId) + "-time:" + Long.toString(tokenIssued);
	}
	
	//Check if the email of the user is valid
	private boolean isUserValid(String email){
		if(email != null && email.endsWith(AppConstants.VALID_EMAIL_DOMAIN)){
			logger.debug("Email is valid: " + email);
			return true;
		}
		logger.debug("Email is NOT valid: " + email);
		return false;
	}

}
