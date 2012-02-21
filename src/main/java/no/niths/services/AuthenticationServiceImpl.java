package no.niths.services;

import no.niths.domain.Student;
import no.niths.infrastructure.interfaces.StudentRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.legacyprofile.LegacyGoogleProfile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthenticationServiceImpl {

	private static final Logger logger = LoggerFactory
			.getLogger(AuthenticationServiceImpl.class);
	
	@Autowired
	private StudentRepository studentRepo;
	
	public String login(String googleToken){
		
		Google google = new GoogleTemplate(googleToken);
		LegacyGoogleProfile profile = google.userOperations().getUserProfile();
		String userEmail = profile.getEmail();
		
		logger.debug("User logging in: " + userEmail);
		if(userEmail != null && userEmail.endsWith("nith.no")){
			logger.debug("Name: " + profile.getName() + " " + profile.getLastName());
			logger.debug("Gender: " + profile.getGender());
			
			Student s = studentRepo.getStudentByEmail(userEmail);
			if(s == null){
				Student temp = new Student();
				temp.setEmail(userEmail);
				logger.debug("User not registrated, creating user");
				studentRepo.create(temp);
			}

			return "logged in: " + userEmail;
		}else{
			return "User not a student @nith (Log in with your nith mail)";
		}

	}
}
