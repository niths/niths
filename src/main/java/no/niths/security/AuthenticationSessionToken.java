package no.niths.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class AuthenticationSessionToken extends UsernamePasswordAuthenticationToken{

	private static final long serialVersionUID = -2260814571469533658L;
	
	private String token;
	private Long studentId;
	
	public AuthenticationSessionToken(String principal, String credentials, String token) {
		super(principal, credentials);
		
		this.token = token;

	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

}
