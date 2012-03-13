package no.niths.common;

import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

public class PasswordGenerator {

	public static void main(String[] args) {
		MessageDigestPasswordEncoder passwordEncoder = new MessageDigestPasswordEncoder("SHA-512");
		
		passwordEncoder.encodePassword("admin","salt");
		
		
		
//		System.out.println(passwordEncoder.getMessageDigest());
	}
}
