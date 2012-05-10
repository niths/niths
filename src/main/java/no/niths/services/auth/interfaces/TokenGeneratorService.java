package no.niths.services.auth.interfaces;


/**
 * 
 * Service for generating and verifying tokens. Tokens are used in HTTP headers
 * when accessing the API from an application.
 * 
 * Tokens are encrypted and decrypted with {@link http://www.jasypt.org/} 
 * Passwords are from persistence.properties in res/main/resources
 *
 * Token structure: <random num sequence> | <token secret> | <date_time_issued>
 *
 * For information about the random num sequece @see {@link http://en.wikipedia.org/wiki/Universally_unique_identifier}
 */

public interface TokenGeneratorService {
    
    /**
     * Generates a token based on the userId
     * 
     * @param userId id of the user
     */
    String generateToken(Long userId);
    
    /**
     * Verifies the format of the provided token
     * 
     * @param token the string to verify
     * @param checkTime true if we want to verify the token timestamp
     * @return token secret. Usually the domain id.
     */
    Long verifyTokenFormat(String token, boolean checkTime);

}
