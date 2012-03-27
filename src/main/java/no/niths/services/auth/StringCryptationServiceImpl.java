package no.niths.services.auth;

import javax.annotation.PostConstruct;

import no.niths.services.auth.interfaces.StringCryptationService;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
	
	public String encrypt(String txt){
		return jasypt.encrypt(txt);
	}
	
	public String decrypt(String txt){
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
