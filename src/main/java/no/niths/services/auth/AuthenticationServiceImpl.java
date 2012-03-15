package no.niths.services.auth;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import no.niths.common.AppConstants;
import no.niths.common.SecurityConstants;
import no.niths.common.ValidationHelper;
import no.niths.domain.Student;
import no.niths.domain.security.Role;
import no.niths.security.SessionToken;
import no.niths.security.User;
import no.niths.services.auth.interfaces.AuthenticationService;
import no.niths.services.auth.interfaces.GoogleAuthenticationService;
import no.niths.services.interfaces.StudentService;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Authenticates user trying to request a resource
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	private static final Logger logger = LoggerFactory
			.getLogger(AuthenticationServiceImpl.class);
	
	@Autowired
	private StudentService studentService;

	@Autowired
	private GoogleAuthenticationService googleService;

	@Value("${jasypt.password}")
	private String cryptionPassword;

	/**
	 * Authenticates a student via Google. If authentication succeeds, student
	 * is either fetched from DB or if the student is a first time user, he/she
	 * gets persisted.
	 * 
	 * Returns a session token valid for (see AppConstants.SESSION_VALID_TIME)
	 * minutes Use this session token for future requests against the API
	 * 
	 * @param googleToken
	 *            the token issued from google
	 * @return session token to use in future request
	 */
	@Override
	public SessionToken authenticateAtGoogle(String googleToken) {
		SessionToken sessionToken = new SessionToken(); // Wrapper class
		// Authenticate user from Google, and then check to see if the email is
		// valid
		String userEmail = googleService.authenticateAndGetEmail(googleToken);
		if (isUserValid(userEmail)) {
			Student authenticatedStudent = getStudent(userEmail);
			// Generate "session token" that the app will use from now on
			String generatedToken = generateToken(authenticatedStudent.getId());
			// Add the generated token to the student
			authenticatedStudent.setSessionToken(generatedToken);
			// Update last login time
			authenticatedStudent.setLastLogon(getCurrentTime());

			studentService.update(authenticatedStudent);
			sessionToken.setToken(generatedToken);
		}
		return sessionToken;
	}

	/**
	 * Authenticates the session token from a request Verifies the format of the
	 * token, then fetches belonging student from DB. We then create a User
	 * wrapper object with roles from the student and return that object to the
	 * class responsible for setting the authenticated user.
	 * 
	 * @param sessionToken
	 *            the token to verify
	 * @return a user object with roles from student belonging to the session
	 *         token
	 */
	@Override
	public User authenticateSessionToken(String sessionToken) {
		logger.debug("Request with session token: " + sessionToken);
		User authenticatedUser = new User(); // ROLE_ANONYMOUS --> Wrapper
		// First check the format of the token
		if (verifySessionTokenFormat(sessionToken)) {
			//Fetch student owning the session token
			Student wantAccess = studentService.getStudentBySessionToken(sessionToken);
			//Then we verify the last login time of the student
			if (wantAccess != null && verifyLastLogonTime(wantAccess.getLastLogon())) {
				
				//The information added here is used in the @Security annotations
				//This enables us to fine grain the security checks like this:
				//@PreAuthorize(hasRole('ROLE_STUDENT') and principal.studentId == #id)
				//principal = authenticatedUser, #id = methodparam(must match the name!)
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
				// Update last login time
				wantAccess.setLastLogon(getCurrentTime());
				studentService.update(wantAccess);
			}
		}
		return authenticatedUser;
	}

	private boolean verifyLastLogonTime(long lastLogon) {
		logger.debug("Verifying last login time...");
		if (System.currentTimeMillis() - lastLogon <= SecurityConstants.SESSION_VALID_TIME) {
			logger.debug("Session token was valid");
			return true;
		}
		logger.debug("token was not valid");
		return false;
	}

	// Verifies the session token from the HTTP request
	private boolean verifySessionTokenFormat(String token) {
		if (token != null) {
			logger.info("Verifying format of token: " + token);
			StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
			jasypt.setPassword(cryptionPassword);
			String decryptedToken = jasypt.decrypt(token);

			logger.debug("Token after decryption: " + decryptedToken);
			String[] splittet = decryptedToken.split("[|]");
			if (splittet.length == 3) {
				try {
					long issuedAt = Long.parseLong(splittet[2]);
					if (System.currentTimeMillis() - issuedAt <= SecurityConstants.MAX_SESSION_VALID_TIME) {
						return true;
					}
				} catch (NumberFormatException e) {
					// Do nothing...
				}
			}
		}
		return false; // Not valid token
	}

	// Generates a random token and encrypts it with Jasypt
	// (http://www.jasypt.org/)
	private String generateToken(Long userId) {
		// Create a token consisting of 128bit random string + user id + current
		// time
		long tokenIssued = getCurrentTime();
		String generatedToken = UUID.randomUUID().toString().toUpperCase()
				+ "|" + Long.toString(userId) + "|"
				+ Long.toString(tokenIssued);
		// Encrypt the token
		StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
		jasypt.setPassword(cryptionPassword);
		String encryptedToked = jasypt.encrypt(generatedToken);

		logger.debug("Generated token before encryption: " + generatedToken);
		logger.debug("Generated token after encryption: " + encryptedToked);

		return encryptedToked;
	}

	/**
	 * Fetches student from DB. If no student matches the email, a new student
	 * will be created and persisted
	 * 
	 * @param userEmail
	 *            the email to the student
	 * @return Student with the email, existing or newly created
	 */
	private Student getStudent(String userEmail) {
		// Fetches the student from DB, if first time user, he/she gets
		// persisted
		Student student = studentService.getStudentByEmail(userEmail);
		if (student == null) { // First time user, persist!
			student = new Student(userEmail);
			Long id = studentService.create(student);
			student.setId(id);
		}
		return student;
	}

	// Check if the email of the user is valid(nith.no) and passes bean
	// validation
	private boolean isUserValid(String email) {
		if (email != null
				&& email.endsWith(AppConstants.VALID_EMAIL_DOMAIN)
				&& ValidationHelper
						.hasObjectValidAttributes(new Student(email))) {
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

	private long getCurrentTime() {
		return new GregorianCalendar().getTimeInMillis();
	}

}
