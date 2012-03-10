package no.niths.application.rest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import no.niths.application.rest.auth.RestLoginControllerImpl;
import no.niths.application.rest.interfaces.auth.RestLoginController;
import no.niths.domain.security.Token;
import no.niths.services.interfaces.auth.RestLoginService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RestLoginControllerTest {
	
	@Mock
	private RestLoginService service;
	
	@InjectMocks
	private RestLoginController controller = new RestLoginControllerImpl();
	
	@Test
	public void testLogin(){
		when(service.login("token")).thenReturn("returned");
		assertEquals("returned", controller.login("token").getToken());
		
		Token t = new Token();
		assertEquals(t.getToken(), controller.login(null).getToken());
	}
	

}
