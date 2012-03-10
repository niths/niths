package no.niths.services.auth;

import java.util.GregorianCalendar;
import java.util.UUID;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.legacyprofile.LegacyGoogleProfile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import no.niths.common.AppConstants;
import no.niths.domain.Student;
import no.niths.infrastructure.interfaces.StudentRepository;
import no.niths.services.interfaces.auth.GoogleAuthenticationService;
import no.niths.services.interfaces.auth.RestLoginService;

@Service
@Transactional
/**
 * Authenticates user via Google
 */
public class RestLoginServiceImpl implements RestLoginService{

	private static final Logger logger = LoggerFactory
			.getLogger(RestLoginServiceImpl.class);
	
	@Autowired
	private StudentRepository studRepo;
	
	@Autowired
	private GoogleAuthenticationService googleService;
	
	@Value("${jasypt.password}")
	private String encryptionPassword;
	
	/**
	 * Authenticates user against Google.
	 * Returns a session token valid for (see AppConstants.SESSION_VALID_TIME) minutes
	 * Use this session token for future requests against the API
	 * 
	 * @param token the token issued from google
	 * @return session token to use in future request
	 */
	@Override
	public String login(String token) {
		String generatedToken = "Not a valid token provided"; //Token parameter was not valid!
		//Authenticate via Google
//		Google google = new GoogleTemplate(token);
//		LegacyGoogleProfile profile = google.userOperations().getUserProfile();
//		String userEmail = profile.getEmail();
		
		String userEmail = googleService.authenticateAndGetEmail(token);
		
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
	
	//Generates a random token and encrypts it with Jasypt (http://www.jasypt.org/)
	private String generateToken(Long userId){
		//Create a token consisting of 128bit random string + user id + current time
		long tokenIssued = new GregorianCalendar().getTimeInMillis();
		String generatedToken = UUID.randomUUID().toString().toUpperCase()
				+ "|" + Long.toString(userId) + "|" + Long.toString(tokenIssued);
		//Encrypt the token
		StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
		jasypt.setPassword(encryptionPassword);
		String encryptedToked = jasypt.encrypt(generatedToken);
		
		logger.debug("Generated token before encryption: " + generatedToken);
		logger.debug("Generated token after encryption: " + encryptedToked);
		
		return encryptedToked;
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
