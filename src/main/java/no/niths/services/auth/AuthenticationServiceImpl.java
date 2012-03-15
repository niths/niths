package no.niths.services.auth;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import no.niths.common.AppConstants;
import no.niths.common.ValidationHelper;
import no.niths.domain.Student;
import no.niths.domain.security.Role;
import no.niths.infrastructure.interfaces.StudentRepository;
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
		String userEmail = googleService.authenticateAndGetEmail(token);
		
		//Check if user has valid email ("nith.no")
		if(isUserValid(userEmail)){
			
			Student authenticatedStudent = studentRepo.getStudentByEmail(userEmail);
			
			if(authenticatedStudent == null){ //First time user, persist!
				authenticatedStudent = new Student(userEmail);
				Long id = studentRepo.create(authenticatedStudent);
				authenticatedStudent.setId(id); 
			}
			
			//Generate "session token" that student uses from now on
			generatedToken = generateToken(authenticatedStudent.getId());
			authenticatedStudent.setSessionToken(generatedToken);
			studentRepo.update(authenticatedStudent);
		}
		
		return generatedToken;
	}

	/**
	 * Fetch student that belongs to session token and returns a user object
	 * with roles of the student
	 * 
	 * @param sessionToken
	 *            access token
	 * @return a user object with roles
	 */
	@Override
	public User authenticate(String sessionToken) {
		logger.debug("User trying to log in with session token: "
				+ sessionToken);
		User authenticatedUser = new User(); // ROLE_USER

		if (sessionToken != null && verifySessionToken(sessionToken)) {
			Student wantAccess = studentRepo
					.getStudentBySessionToken(sessionToken);

			if (wantAccess != null) { // User with session token found
				//Add some security info to the user
				authenticatedUser.setUserName(wantAccess.getEmail());
				authenticatedUser.setStudentId(wantAccess.getId());
				// Checking roles of student and adding them to User wrapper
				// object
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
