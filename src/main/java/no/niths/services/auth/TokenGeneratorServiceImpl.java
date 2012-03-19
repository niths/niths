package no.niths.services.auth;

import java.util.GregorianCalendar;
import java.util.UUID;

import no.niths.application.rest.exception.ExpiredTokenException;
import no.niths.application.rest.exception.UnvalidTokenException;
import no.niths.common.SecurityConstants;
import no.niths.services.auth.interfaces.TokenGeneratorService;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
/**
 * 
 * Service for generating and verifying tokens. Tokens are used in HTTP headers
 * when accessing the API from an application.
 * 
 * Tokens are encrypted and decrypted with {@link http://www.jasypt.org/} 
 * Passwords are from persistence.properties in res/main/resources
 *
 */
@Service
public class TokenGeneratorServiceImpl implements TokenGeneratorService {

	private static final Logger logger = LoggerFactory
			.getLogger(TokenGeneratorServiceImpl.class);

	@Value("${jasypt.password}")
	private String password;

	/**
	 * Generates a token based on the userId
	 * 
	 * @param userId
	 *            id of the user
	 */
	@Override
	public String generateToken(Long userId) {
		if (userId == null) {
			throw new UnvalidTokenException("User id is missing");
		}

		long tokenIssued = getCurrentTime();
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

	/**
	 * Verifies the format of the token
	 * 
	 * @param token
	 *            to verify
	 * @throws AuthenticationException
	 */
	@Override
	public void verifyTokenFormat(String token) throws AuthenticationException {
		logger.debug("Verifying session token format...");
		if (token == null) {
			throw new UnvalidTokenException("Token can not be null");
		}
		try {
			logger.debug("Token before encryption: " + token);

			StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
			jasypt.setPassword(password);
			String decryptedToken = jasypt.decrypt(token);

			logger.debug("Token after decryption: " + decryptedToken);

			String[] splittet = decryptedToken.split("[|]");
			if (splittet.length == 3) {
				long issuedAt = Long.parseLong(splittet[2]);
				if (System.currentTimeMillis() - issuedAt > SecurityConstants.MAX_SESSION_VALID_TIME) {
					logger.debug("Token expired");
					throw new ExpiredTokenException("Session-token has expired");
				}
			}
		} catch (EncryptionOperationNotPossibleException ee) {
			throw new UnvalidTokenException("Token not in a valid format");
		} catch (NumberFormatException nfe) {
			throw new UnvalidTokenException("Token not in a valid format");
		}
		logger.debug("Verified");
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// Private helper
	private long getCurrentTime() {
		return new GregorianCalendar().getTimeInMillis();
	}

}
