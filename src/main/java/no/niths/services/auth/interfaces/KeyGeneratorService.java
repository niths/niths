package no.niths.services.auth.interfaces;

/**
 *
 * Service for generating and developer and application keys
 *
 */
public interface KeyGeneratorService {
    
    /**
     * Generates a Developer key
     * 
     * @return the key as a String
     */
    String generateDeveloperKey();
    
    /**
     * Generates an Application key
     * 
     * @return the key as a String
     */
    String generateApplicationKey();

}
