package no.niths.application.rest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import no.niths.application.rest.auth.RestLoginControllerImpl;
import no.niths.application.rest.auth.interfaces.RestLoginController;
import no.niths.security.SessionToken;
import no.niths.services.auth.interfaces.AuthenticationService;
//import no.niths.services.auth.interfaces.RestLoginService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RestLoginControllerTest {
	
	@Mock
	private AuthenticationService service;
//	@Mock
//	private RestLoginService service;
	
	@InjectMocks
	private RestLoginController controller = new RestLoginControllerImpl();
	
	@Test
	public void testLogin(){
		SessionToken token = new SessionToken("token");
		when(service.authenticateAtGoogle("token")).thenReturn(token);
		assertEquals(token, controller.login(token));
		
		SessionToken t = new SessionToken();
		assertEquals(t.getToken(), controller.login(null).getToken());
	}
	

}
