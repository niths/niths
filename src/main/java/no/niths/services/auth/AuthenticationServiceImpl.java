package no.niths.services.auth;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import no.niths.common.AppConstants;
import no.niths.common.ValidationHelper;
import no.niths.domain.Student;
import no.niths.domain.security.Role;
import no.niths.infrastructure.interfaces.StudentRepository;
import no.niths.security.SessionToken;
import no.niths.security.User;
import no.niths.services.auth.interfaces.GoogleAuthenticationService;
import no.niths.services.auth.interfaces.AuthenticationService;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Authenticates user trying to request a resource
 */
@Service
@Transactional
public class AuthenticationServiceImpl implements
		AuthenticationService {

	private static final Logger logger = LoggerFactory
			.getLogger(AuthenticationServiceImpl.class);

	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private GoogleAuthenticationService googleService;

	@Value("${jasypt.password}")
	private String cryptionPassword;
	
	/**
	 * Authenticates a student via Google. If authentication succeeds,
	 * student is either fetched from DB or if the student is a first time user, 
	 * he/she gets persisted.
	 * 
	 * Returns a session token valid for (see AppConstants.SESSION_VALID_TIME) minutes
	 * Use this session token for future requests against the API
	 * 
	 * @param googleToken the token issued from google
	 * @return session token to use in future request
	 */
	@Override
	public SessionToken authenticateAtGoogle(String googleToken) {
		SessionToken sessionToken = new SessionToken(); //Wrapper class
		//Authenticate user from Google, and then check to see if the email is valid
		String userEmail = googleService.authenticateAndGetEmail(googleToken);
		if(isUserValid(userEmail)){
			//Fetches the student from DB, if first time user, he/she gets persisted
			Student authenticatedStudent = studentRepo.getStudentByEmail(userEmail);
			if(authenticatedStudent == null){ //First time user, persist!
				authenticatedStudent = new Student(userEmail);
				Long id = studentRepo.create(authenticatedStudent);
				authenticatedStudent.setId(id); 
			}
			//Generate "session token" that the app will use from now on
			String generatedToken = generateToken(authenticatedStudent.getId());
			authenticatedStudent.setSessionToken(generatedToken);
			studentRepo.update(authenticatedStudent);
			sessionToken.setToken(generatedToken);
		}
		return sessionToken;
	}

	/**
	 * Authenticates the session token from a request
	 * Verifies the format of the token, then fetches belonging student 
	 * from DB. We then create a User wrapper object with roles from the student
	 * and return that object to the class responsible for setting the
	 * authenticated user. 
	 * 
	 * @param sessionToken the token to verify
	 * @return a user object with roles from student belonging to the session token
	 */
	@Override
	public User authenticateSessionToken(String sessionToken) {
		logger.debug("Request with session token: " + sessionToken);
		User authenticatedUser = new User(); // ROLE_ANONYMOUS
		if (sessionToken != null && verifySessionToken(sessionToken)) {
			Student wantAccess = studentRepo
					.getStudentBySessionToken(sessionToken);
			if (wantAccess != null) { // User with session token found
				//Add some security info to the user
				authenticatedUser.setUserName(wantAccess.getEmail());
				authenticatedUser.setStudentId(wantAccess.getId());
				// Checking roles of student and adding them to User wrapper
				List<Role> roles = wantAccess.getRoles();
				if (!(roles.isEmpty())) {
					String loggerText = "Student logging in has role(s): ";
					for (Role role : roles) {
						loggerText += role.getRoleName() + " ";
						authenticatedUser.addRoleName(role.getRoleName());
					}
					logger.debug(loggerText);
				}
			}
		}
		return authenticatedUser;
	}

	// Verifies the session token from the HTTP request
	private boolean verifySessionToken(String token) {
		logger.info("Verifying format of token: " + token);
		StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
		jasypt.setPassword(cryptionPassword);
		String decryptedToken = jasypt.decrypt(token);

		logger.debug("Token after decryption: " + decryptedToken);
		String[] splittet = decryptedToken.split("[|]");
		if (splittet.length == 3) {
			try {
				long issuedAt = Long.parseLong(splittet[2]);
				if (System.currentTimeMillis() - issuedAt <= AppConstants.SESSION_VALID_TIME) {
					return true;
				}
			} catch (NumberFormatException e) {
				// Do nothing...
			}
		}

		return false; // Not valid token
	}
	
	//Generates a random token and encrypts it with Jasypt (http://www.jasypt.org/)
	private String generateToken(Long userId){
		//Create a token consisting of 128bit random string + user id + current time
		long tokenIssued = new GregorianCalendar().getTimeInMillis();
		String generatedToken = UUID.randomUUID().toString().toUpperCase()
				+ "|" + Long.toString(userId) + "|" + Long.toString(tokenIssued);
		//Encrypt the token
		StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
		jasypt.setPassword(cryptionPassword);
		String encryptedToked = jasypt.encrypt(generatedToken);
		
		logger.debug("Generated token before encryption: " + generatedToken);
		logger.debug("Generated token after encryption: " + encryptedToked);
		
		return encryptedToked;
	}
	
	//Check if the email of the user is valid(nith.no) and passes bean validation
	private boolean isUserValid(String email){
		if(email != null && email.endsWith(AppConstants.VALID_EMAIL_DOMAIN)
				&& ValidationHelper.hasObjectValidAttributes(new Student(email))){
			logger.debug("Email is valid: " + email);
			return true;				
		}
		logger.debug("Email is NOT valid: " + email);
		return false;
	}

	public String getCryptionPassword() {
		return cryptionPassword;
	}

	public void setCryptionPassword(String decryptionPassword) {
		this.cryptionPassword = decryptionPassword;
	}

}
