package no.niths.services.auth;

import java.util.GregorianCalendar;
import java.util.List;

import no.niths.application.rest.exception.ExpiredTokenException;
import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.exception.UnvalidEmailException;
import no.niths.application.rest.exception.UnvalidTokenException;
import no.niths.common.AppConstants;
import no.niths.common.SecurityConstants;
import no.niths.domain.Application;
import no.niths.domain.Developer;
import no.niths.domain.Student;
import no.niths.domain.security.Role;
import no.niths.security.ApplicationToken;
import no.niths.security.DeveloperToken;
import no.niths.security.SessionToken;
import no.niths.security.RequestHolderDetails;
import no.niths.services.auth.interfaces.AuthenticationService;
import no.niths.services.auth.interfaces.GoogleAuthenticationService;
import no.niths.services.auth.interfaces.TokenGeneratorService;
import no.niths.services.interfaces.ApplicationService;
import no.niths.services.interfaces.DeveloperService;
import no.niths.services.interfaces.MailSenderService;
import no.niths.services.interfaces.StudentService;

import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

/**
 * Authenticates user trying to request a resource
 * 
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	private static final Logger logger = LoggerFactory
			.getLogger(AuthenticationServiceImpl.class);

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private DeveloperService developerService;

	@Autowired
	private GoogleAuthenticationService googleService;
	
	@Autowired
	private TokenGeneratorService tokenService;
	
	@Autowired
	private ApplicationService appService;
	
	@Autowired
	private MailSenderService mailService;

	/**
	 * Authenticates a student via Google. If authentication succeeds, student
	 * is either fetched from DB or if the student is a first time user, he/she
	 * gets persisted.
	 * <p>
	 * Returns a session token valid for {@value AppConstants.SESSION_VALID_TIME}
	 * minutes Use this session token for future requests against the API
	 * <p>
	 * How to use:
	 * <pre>
	 * {@code
	 * Place in header:
	 * Session-token: ojejcndiu23io2hjUILHDSDW21.wqi8h2
	 * Accept: Application/xml
	 * }
	 * </pre>
	 * @param googleToken	the string to authenticate. If null, or not correct
	 * 						a 401 will be in the response.
	 * 
	 * @return SessionToken the string to use in future requests against the 
	 * 						API. It is valid for {@value SecurityConstants.SESSION_VALID_TIME} ms.
	 * 						Max concurrent session is {@value SecurityConstants.MAX_SESSION_VALID_TIME} ms.
	 * 						
	 */
	@Override
	public SessionToken authenticateAtGoogle(String googleToken) {
		SessionToken sessionToken = new SessionToken(); // Wrapper class
		// Authenticate user from Google, and then check to see if the email is
		// valid
		String userEmail = googleService.authenticateAndGetEmail(googleToken);
		isUserValid(userEmail);
		Student authenticatedStudent = getStudent(userEmail);
		// Generate "session token" that the app will use from now on
		String generatedToken = tokenService.generateToken(authenticatedStudent.getId());
		
//		String generatedToken = generateToken(authenticatedStudent.getId());
		// Add the generated token to the student
		authenticatedStudent.setSessionToken(generatedToken);
		// Update last login time
		authenticatedStudent.setLastLogon(getCurrentTime());

		studentService.update(authenticatedStudent);
		sessionToken.setToken(generatedToken);

		return sessionToken;
	}

	/**
	 * Authenticates the session token from a request. 
	 * <p>
	 * Uses a TokenGeneratorService to verify the format of the token and any
	 * errors will throw an AuthenticationException with an "Error" header.
	 * If format is verified we then fetches belonging student from DB.
	 * <p>
	 * We then create a User wrapper object with roles copied from the student and
	 * return that user to the class responsible for doing the actual authentication.
	 * 
	 * @param sessionToken 	the string to verify. If not correct, an Authentication
	 * 						exception will occur with an "Error header" explaining
	 * 						the issue.
	 * @return a user object with roles from student belonging to the session
	 *         token
	 */
	@Override
	public RequestHolderDetails authenticateSessionToken(String sessionToken)
			throws AuthenticationException {
		logger.debug("Will autheticate: " + sessionToken);

		// First check the format of the token		
		tokenService.verifyTokenFormat(sessionToken, true);

		// Fetch student owning the session token
		Student wantAccess = studentService
				.getStudentBySessionToken(sessionToken);
		// Then we verify the last login time of the student
		if (wantAccess == null) {
			logger.debug("No student has that session-token");
			throw new UnvalidTokenException(
					"Token does not belong to a student");
		}
		if(wantAccess.getLastLogon() == null){
			throw new UnvalidTokenException("Can not find last login");
		}
		verifyLastLogonTime(wantAccess.getLastLogon());

		/**
		 * The information added here is used in the @Security annotations
		 * 
		 * This enables us to fine grain the security checks like this:¨
		 * <pre>
		 * {@code
		 * @PreAuthorize(hasRole('ROLE_STUDENT') and principal.studentId== #id)
		 * 		public void methodName(Long id){...}
		 * principal = authenticatedUser, #id = methodparam(must match the name!)
		 * }
		 * </pre>
		 */
		RequestHolderDetails authenticatedUser = new RequestHolderDetails(); // ROLE_ANONYMOUS --> Wrapper
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

		return authenticatedUser;
	}
	
	/**
	 * Register a developer and generates a developer token that the
	 * developer uses in future requests
	 * 
	 * @param developer the developer to persist
	 * @return DeveloperToken the token and a confirmation message 
	 */
	@Override
	public DeveloperToken registerDeveloper(Developer dev) {
		//Verify developer email
		isEmailValid(dev.getEmail());
		//Passed checks! We persist the dev to get the id
		developerService.create(dev);
		logger.debug("Developer created! id: " + dev.getId());
		
		//We then generate a dev token and update the dev
		DeveloperToken devToken = new DeveloperToken();
		devToken.setToken(tokenService.generateToken(dev.getId()));
		dev.setDeveloperToken(devToken.getToken());
		developerService.update(dev);
		logger.debug("Developer[" + dev.getId() + "] has been given token: " + devToken.getToken());
		
		if (!mailService.sendDeveloperRegistratedConfirmation(dev)) {
			devToken.setMessage("Failed to send an email, but now worries! \n"
					+ "To enable your new developer account paste this into a browser\n" +
					AppConstants.NITHS_BASE_DOMAIN + "register/enable/" + devToken.getToken());
		}
		
		return devToken;
	}
	
	/**
	 * Registers an application to the matching developer
	 * 
	 * @param app the application to add
	 * @param devId id of the dev to add application to
	 * @return an application token to use in furture requests
	 * 
	 */
	@Override
	public ApplicationToken registerApplication(Application app, Long devId){
		Developer dev = developerService.getById(devId);
		if(dev == null){
			logger.warn("No developer found for: " + devId);
			throw new ObjectNotFoundException("No developer found");
		}
		
		ApplicationToken appToken = new ApplicationToken(tokenService.generateToken(devId));
		app.setEnabled(true);
		app.setApplicationToken(appToken.getToken());
		dev.getApps().add(app);
		developerService.update(dev);
		mailService.sendDeveloperAddedAppConfirmation(dev, app);
		return appToken;
	}
	
	/**
	 * Verifies the format and fetches matching developer from DB
	 * 
	 * @param devToken token to verify
	 * @return the developer id
	 */
	@Override
	public Long authenticateDeveloperToken(String devToken) throws AuthenticationException{
		tokenService.verifyTokenFormat(devToken, false);
		Developer dev = developerService.getDeveloperByDeveloperToken(devToken, true);
		if(dev == null){
			throw new UnvalidTokenException("No developer found for token or dev is not enabled");
		}

		return dev.getId();
	}
	
	/**
	 * Verifies the format of the application token and returns the app id
	 * Returns null if no app is found or app is not enabled
	 * 
	 * @param appToken string verify
	 * @return id of the belonging app
	 * @throws AuthenticationException
	 */
	@Override
	public Long authenticateApplicationToken(String appToken) throws AuthenticationException{
		tokenService.verifyTokenFormat(appToken, false);
		Application app = appService.getByApplicationToken(appToken);
		if(app == null){
			throw new UnvalidTokenException("No app found or app is not enabled");
		}
		return app.getId();
	}
	
	
	/**
	 * Enables a developer, needed to be able to do requests towards the API
	 * 
	 * Developer must exist in the DB, or else enabling will fail...
	 * 
	 * @param developerToken string return from registerDeveloper(Dev)
	 * @return the developer object, null if not found
	 */
	public Developer enableDeveloper(String developerToken) throws AuthenticationException{
		logger.debug("Trying to enable developer with token: " + developerToken);
		Developer dev = developerService.getDeveloperByDeveloperToken(developerToken, false);
		if(dev == null){
			throw new UnvalidTokenException("No developer found with that token");
		}
		//Generate a new token
		dev.setDeveloperToken(tokenService.generateToken(dev.getId()));
		dev.setEnabled(true);
		developerService.update(dev);
		
		return dev;
	}

	/**
	 * Verifies the last login time against the {@value SecurityConstants.SESSION_VALID_TIME} 
	 * 
	 * @param lastLogon long time student had last login in ms
	 * @throws AuthenticationException when session token has expired
	 */
	private void verifyLastLogonTime(long lastLogon)
			throws AuthenticationException {
		logger.debug("Verifying last login time...");
		if (!(System.currentTimeMillis() - lastLogon <= SecurityConstants.SESSION_VALID_TIME)) {
			logger.debug("Token expired");
			throw new ExpiredTokenException("Session-token has expired");
		}
		logger.debug("Verified");
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
			logger.debug("Student is a first time user, persisting.");
			student = new Student(userEmail);
			Long id = studentService.create(student);
			student.setId(id);
		}
		return student;
	}

	/**
	 * Check if the email of the user is valid(nith.no) and passes bean validation
	 * @param email the string to check
	 * @throws UnvalidEmailException
	 */
	private void isUserValid(String email) {
		isEmailValid(email);
		if (!email.endsWith(AppConstants.VALID_EMAIL_DOMAIN)) {
			logger.debug("email is unvalid: " + email);
			throw new UnvalidEmailException("Unvalid email, must end with " + AppConstants.VALID_EMAIL_DOMAIN);
		}
		logger.debug("Email valid: " + email);
	}
	
	private void isEmailValid(String email){
		EmailValidator validator = EmailValidator.getInstance();
		if(!validator.isValid(email)){
			throw new UnvalidEmailException("Unvalid email, did you forget @?");
		}
	}

	//Private helper
	private long getCurrentTime() {
		return new GregorianCalendar().getTimeInMillis();
	}

}
