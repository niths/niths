package no.niths.services.auth.interfaces;

public interface StringCryptationService {
	String encrypt(String txt);
	String decrypt(String txt);
}
