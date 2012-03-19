package no.niths.services;

import static org.junit.Assert.assertEquals;

import java.util.GregorianCalendar;
import java.util.UUID;

import no.niths.application.rest.exception.ExpiredTokenException;
import no.niths.application.rest.exception.UnvalidTokenException;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.APIEvent;
import no.niths.services.auth.TokenGeneratorServiceImpl;
import no.niths.services.auth.interfaces.TokenGeneratorService;
import no.niths.services.interfaces.APIEventService;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class TokenGeneratorServiceTest {

	private static final Logger logger = LoggerFactory
			.getLogger(TokenGeneratorServiceTest.class);
	
	String password = "pass";
	
	@Autowired
	private TokenGeneratorServiceImpl tokenService;
	
	@Test
	public void testGenerateAndVerify(){
		tokenService.setPassword(password);
		String token = tokenService.generateToken(new Long(21));
		String token2 = tokenService.generateToken(new Long(22));
		tokenService.verifyTokenFormat(token);	
	}
	
	@Test(expected=ExpiredTokenException.class)
	public void testExpiredoken(){
		String unvalid = generateUnvalidToken(new Long(23));
		tokenService.verifyTokenFormat(unvalid);
	}
	
	@Test(expected=UnvalidTokenException.class)
	public void testUnvalidToken(){
		tokenService.verifyTokenFormat("aaaaijde876tda76fd6wafdw");
	}
	@Test(expected=UnvalidTokenException.class)
	public void testUnvalidToken2(){
		tokenService.verifyTokenFormat(null);
	}
	@Test(expected=UnvalidTokenException.class)
	public void testUnvalidToken3(){
		String token = tokenService.generateToken(null);
	}
	
	private String generateUnvalidToken(Long userId) {
		
		long tokenIssued = new GregorianCalendar().getTimeInMillis() - 9999000;
		String generatedToken = UUID.randomUUID().toString().toUpperCase()
				+ "|" + Long.toString(userId) + "|"
				+ Long.toString(tokenIssued);
		// Encrypt the token
		StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
		jasypt.setPassword(password);
		String encryptedToked = jasypt.encrypt(generatedToken);

		logger.debug("Generated token before encryption: " + generatedToken);
		logger.debug("Generated token after encryption: " + encryptedToked);

		return encryptedToked;
	}
	
}
