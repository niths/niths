package no.niths.services.auth;

import javax.annotation.PostConstruct;

import no.niths.services.auth.interfaces.StringCryptationService;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
/**
 * Service for handling en- and decryption of strings
 *
 */
@Service
public class StringCryptationServiceImpl implements StringCryptationService {
    
    private StandardPBEStringEncryptor jasypt;
    
    @Value("${jasypt.password}")
    private String password;
    
    @PostConstruct
    public void init(){
        jasypt = new StandardPBEStringEncryptor();
        jasypt.setPassword(password);
    }

    /**
     * {@inheritDoc}
     */
    public String encrypt(String txt) throws EncryptionOperationNotPossibleException{
        return jasypt.encrypt(txt);
    }

    /**
     * {@inheritDoc}
     */
    public String decrypt(String txt) throws EncryptionOperationNotPossibleException{
        return jasypt.decrypt(txt);
    }

    
    //Getters and setters
    public StandardPBEStringEncryptor getJasypt() {
        return jasypt;
    }

    public void setJasypt(StandardPBEStringEncryptor jasypt) {
        this.jasypt = jasypt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    

}
