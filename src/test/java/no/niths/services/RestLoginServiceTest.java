package no.niths.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.mockito.Mockito.when;
import no.niths.domain.Student;
import no.niths.infrastructure.interfaces.StudentRepository;
import no.niths.services.auth.RestLoginServiceImpl;
import no.niths.services.auth.interfaces.GoogleAuthenticationService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RestLoginServiceTest {
	
	@Mock
	private StudentRepository studRepo;
	
	@Mock
	private GoogleAuthenticationService authService;
	
	@InjectMocks
	private RestLoginServiceImpl loginService = new RestLoginServiceImpl();
	
	@Test
	public void testLogin(){
		Student s = new Student("mail@nith.no");
		s.setId(new Long(1));
		loginService.setEncryptionPassword("password");

		//Valid email
		when(authService.authenticateAndGetEmail("token")).thenReturn("mail@nith.no");
		when(studRepo.getStudentByEmail("mail@nith.no")).thenReturn(s);
				
		String genToken = loginService.login("token");
		assertNotSame("Not a valid token provided", genToken);
		
		//Not a valid email
		s.setEmail("mail@mail.no");
		when(authService.authenticateAndGetEmail("token")).thenReturn("mail@mail.no");
		when(studRepo.getStudentByEmail("mail@mail.no")).thenReturn(s);
		
		genToken = loginService.login("token");
		assertEquals("Not a valid token provided", genToken);
		
		//Not a registrated user
		s.setEmail("mail@nith.no");
		when(authService.authenticateAndGetEmail("token")).thenReturn("mail@nith.no");
		when(studRepo.getStudentByEmail("mail@nith.no")).thenReturn(null);
		when(studRepo.create(new Student("mail@nith.no"))).thenReturn(new Long(1));
		
		genToken = loginService.login("token");
		assertNotSame("Not a valid token provided", genToken);
	}
}
