package no.niths.common;

import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

public class PasswordGenerator {

	public static void main(String[] args) {
		MessageDigestPasswordEncoder passwordEncoder = new MessageDigestPasswordEncoder("SHA-512");
		
		passwordEncoder.encodePassword("admin","salt");
		
		
		
//		System.out.println(passwordEncoder.getMessageDigest());
	}
}
