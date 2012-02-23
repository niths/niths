package no.niths.services;

import no.niths.common.AppConstants;
import no.niths.domain.Student;
import no.niths.infrastructure.interfaces.StudentRepository;
import no.niths.security.User;
import no.niths.services.interfaces.AuthenticationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.legacyprofile.LegacyGoogleProfile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * Service that handles login with Google
 *
 */
@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

	private static final Logger logger = LoggerFactory
			.getLogger(AuthenticationServiceImpl.class);
	
	@Autowired
	private StudentRepository studentRepo;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public User login(String googleToken) {
		logger.debug("Trying to log in with token: " + googleToken);
		Google google = new GoogleTemplate(googleToken);
		LegacyGoogleProfile profile = google.userOperations().getUserProfile();
		return getUser(profile);
	}
	
	//Get the user from DB, persists if non excisting and if valid email
	private User getUser(LegacyGoogleProfile profile){
		
		String userEmail = profile.getEmail();
		logger.debug("User trying to log in with email: " + userEmail);
		
		if(isUserValid(userEmail)){
			Student temp = studentRepo.getStudentByEmail(userEmail);
			if(temp == null){
				logger.info("Student does not exsist, creating a new student");
				temp = createStudentWithEmail(userEmail);
			}
			//TODO: Get ROLE of student
			return new User();
		}else{
			return null;
		}
	}
	
	//Persist a student with an email
	private Student createStudentWithEmail(String email){
		Student temp = new Student();
		temp.setEmail(email);
		studentRepo.create(temp);
		return temp;
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
