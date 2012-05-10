package no.niths.services.auth.interfaces;

import org.jasypt.exceptions.EncryptionOperationNotPossibleException;

/**
 * Service for handling en- and decryption of strings
 *
 */
public interface StringCryptationService {
    /**
     * Returns an encrypted String
     * 
     * @param txt the string to encrypt
     * @return the encrypted string
     * @throws EncryptionOperationNotPossibleException
     */
    String encrypt(String txt) throws EncryptionOperationNotPossibleException;
    
    /**
     * Returns a decrypted string
     * 
     * @param txt string to decrypt
     * @return the decrypted string
     * @throws EncryptionOperationNotPossibleException
     */
    String decrypt(String txt) throws EncryptionOperationNotPossibleException;
}
