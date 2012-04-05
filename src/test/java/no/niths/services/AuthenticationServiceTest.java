package no.niths.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import no.niths.application.rest.exception.ExpiredTokenException;
import no.niths.application.rest.exception.UnvalidEmailException;
import no.niths.application.rest.exception.UnvalidTokenException;
import no.niths.common.SecurityConstants;
import no.niths.domain.Developer;
import no.niths.domain.Student;
import no.niths.domain.security.Role;
import no.niths.security.DeveloperToken;
import no.niths.security.RequestHolderDetails;
import no.niths.security.SessionToken;
import no.niths.services.auth.AuthenticationServiceImpl;
import no.niths.services.auth.interfaces.GoogleAuthenticationService;
import no.niths.services.auth.interfaces.TokenGeneratorService;
import no.niths.services.interfaces.DeveloperService;
import no.niths.services.interfaces.MailSenderService;
import no.niths.services.interfaces.StudentService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationServiceTest {

	@Mock
	private StudentService studService;
	
	@Mock
	private GoogleAuthenticationService authService;
	
	@Mock
	private DeveloperService devService;
	
	@Mock
	private MailSenderService mailService;
	
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
		when(studService.getStudentBySessionToken(token)).thenReturn(s);
		service.authenticateSessionToken(token);
	}
	
	@Test(expected=ExpiredTokenException.class)
	public void expiredToken(){
		Student s = new Student(validemail);
		long now = System.currentTimeMillis() - (SecurityConstants.MAX_SESSION_VALID_TIME * 2);
		s.setLastLogon(now);
		when(studService.getStudentBySessionToken(token)).thenReturn(s);
		RequestHolderDetails u = service.authenticateSessionToken(token);
	}
	
	
	@Test//
	public void testStudentWithLastLogon(){
		Student s = new Student(validemail);
		long now = System.currentTimeMillis();
		s.setLastLogon(now);
		when(studService.getStudentBySessionToken(token)).thenReturn(s);
		
		RequestHolderDetails u = service.authenticateSessionToken(token);
		assertEquals(1, u.getAuthorities().size());
		GrantedAuthority a = u.getAuthorities().iterator().next();
		assertEquals(SecurityConstants.R_ANONYMOUS, a.getAuthority());
		
		s.getRoles().add(new Role(SecurityConstants.R_STUDENT));
		when(studService.getStudentBySessionToken(token)).thenReturn(s);
		u = service.authenticateSessionToken(token);
		assertEquals(1, u.getAuthorities().size());
		a = u.getAuthorities().iterator().next();
		assertEquals(SecurityConstants.R_STUDENT, a.getAuthority());
	}
	
}
