package no.niths.services.auth;

import java.util.List;

import no.niths.domain.Student;
import no.niths.domain.security.Role;
import no.niths.infrastructure.interfaces.StudentRepository;
import no.niths.security.User;
import no.niths.services.interfaces.auth.RequestAuthenticationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * Authenticates user trying to request a resource
 */
@Service
@Transactional
public class RequestAuthenticationServiceImpl implements RequestAuthenticationService {

	private static final Logger logger = LoggerFactory
			.getLogger(RequestAuthenticationServiceImpl.class);
	
	@Autowired
	private StudentRepository studentRepo;
	
	/**
	 * Fetch student that belongs to session token
	 * and returns a user object with roles of the student
	 * 
	 * @param sessionToken access token
	 * @return a user object with roles
	 */
	@Override
	public User authenticate(String sessionToken) {
		logger.debug("User trying to log in with session token: " + sessionToken);
		
		//TODO: Check if token has expired (Check if is in valid format, then if expired)
		//		If not, proceed... 
		//		Else, throw token expired ex?
		
		
		Student wantAccess = studentRepo.getStudentBySessionToken(sessionToken);
		User authenticatedUser = new User(); //ROLE_USER
		
		if(wantAccess != null){
			//For access to authenticated users email if any future implementations needs this.			
			authenticatedUser.setUserName(wantAccess.getEmail());
			
			//Checking roles of student and adding them to User wrapper object
			List<Role> roles = wantAccess.getRoles();			
			if(!(roles.isEmpty())){
				String loggerText = "Student logging in has role(s): ";
				for (Role role: roles){
					loggerText += role.getRoleName() + " ";
					authenticatedUser.addRoleName(role.getRoleName());
				}
				logger.debug(loggerText);
			}			
		}
		
		return authenticatedUser;
	}

}
