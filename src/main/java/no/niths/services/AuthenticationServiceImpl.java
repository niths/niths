package no.niths.services;

import java.util.List;

import no.niths.common.AppConstants;
import no.niths.domain.Student;
import no.niths.domain.security.Role;
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
		return getUser(profile, googleToken);
	}
	
	//Get the user from DB, persists if non existing and if valid email
	private User getUser(LegacyGoogleProfile profile, String token){
		
		String userEmail = profile.getEmail();
		logger.debug("User trying to log in with email: " + userEmail);
		User user = new User(userEmail);
		
		if(isUserValid(userEmail)){
			user.setGoogleToken(token);
			Student temp = studentRepo.getStudentByEmail(userEmail);
			if(temp == null){
				logger.info("Student does not exsist, creating a new student");
				temp = createStudentWithEmail(userEmail);
			}
			//Getting the role(s) of the student
			List<Role> roles = temp.getRoles();
			if(!(roles.isEmpty())){
				String loggerText = "Student logging in has role(s): ";
				for (Role role: roles){
					loggerText += role.getRoleName() + " ";
					user.addRoleName(role.getRoleName());
				}
				logger.info(loggerText);
			}
		}
		return user;
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
