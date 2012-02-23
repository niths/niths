package no.niths.services;

import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.security.User;
import no.niths.services.interfaces.AuthenticationService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class AuthenticationServiceTest {
	
	@Autowired
	AuthenticationService service;
	
	@Test(expected = HttpClientErrorException.class)
	public void testLoginWithUnValidToken(){
		
		User u = service.login("NOT_VALID_TOKEN");
		
	}

}
