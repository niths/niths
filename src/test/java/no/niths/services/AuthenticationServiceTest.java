package no.niths.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import no.niths.application.rest.exception.UnvalidEmailException;
import no.niths.application.rest.exception.UnvalidTokenException;
import no.niths.common.constants.SecurityConstants;
import no.niths.domain.school.Student;
import no.niths.security.RequestHolderDetails;
import no.niths.security.SessionToken;
import no.niths.services.auth.AuthenticationServiceImpl;
import no.niths.services.auth.interfaces.GoogleAuthenticationService;
import no.niths.services.auth.interfaces.TokenGeneratorService;
import no.niths.services.school.interfaces.StudentService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationServiceTest {

	@Mock
	private StudentService studService;
	
	@Mock
	private GoogleAuthenticationService authService;
	
//	@Mock
//	private DeveloperService devService;
//	
//	@Mock
//	private MailSenderService mailService;
	
	@Mock
	private TokenGeneratorService tokenService;
	
	@InjectMocks
	private AuthenticationServiceImpl service = new AuthenticationServiceImpl();
	
	private String token = "token";
	private String unValidemail = "mail@mail.com";
	private String validemail = "maiwswwwl@nith.no";
	
	/**
	 * Authenticate at google
	 */
	@Test(expected=UnvalidEmailException.class)
	public void testEmailunValid(){
		when(authService.authenticateAndGetEmail(token)).thenReturn(token);
		service.authenticateAtGoogle(token);
	}
	@Test(expected=UnvalidEmailException.class)
	public void testEmailunValid2(){
		when(authService.authenticateAndGetEmail(token)).thenReturn(unValidemail);
		service.authenticateAtGoogle(token);
	}
	@Test//(expected=UnvalidEmailException.class)
	public void testEmailValid(){
		when(authService.authenticateAndGetEmail(token)).thenReturn(validemail);
		Student s= new Student(validemail);
		when(studService.create(s)).thenReturn(new Long(1));
		when(tokenService.generateToken(new Long(1))).thenReturn("dddd");
		SessionToken st = service.authenticateAtGoogle(token).getSessionToken();
		assertEquals("dddd", st.getToken());
	}
	
	/**
	 * Authentice session token
	 */
	@Test(expected=UnvalidTokenException.class)
	public void testStudentWithNoLastLogon(){
		Student s = new Student(validemail);
		s.setId(new Long(2));
		when(studService.getById(s.getId())).thenReturn(s);
		service.authenticateSessionToken(token);
	}
	
	@Test(expected=UnvalidTokenException.class)
	public void expiredToken(){
		Student s = new Student(validemail);
		long now = System.currentTimeMillis() - (SecurityConstants.MAX_SESSION_VALID_TIME * 2);
		s.setLastLogon(now);
		s.setId(new Long(2));
		when(studService.getById(s.getId())).thenReturn(s);
		@SuppressWarnings("unused")
		RequestHolderDetails u = service.authenticateSessionToken(token);
	}
	
	
}
