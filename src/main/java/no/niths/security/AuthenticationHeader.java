package no.niths.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class AuthenticationHeader extends UsernamePasswordAuthenticationToken{

	private static final long serialVersionUID = -2260814571469533658L;
	
	private String sessionToken;
	private String developerToken;
	private Long studentId;
	private Long developerId;
	
	public AuthenticationHeader(){
		super(null, null);
	}
	
	public AuthenticationHeader(String principal, String credentials, String sessionToken, String developerToken) {
		super(principal, credentials);
		this.setSessionToken(sessionToken);
		this.setDeveloperToken(developerToken);
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	public String getDeveloperToken() {
		return developerToken;
	}

	public void setDeveloperToken(String developerToken) {
		this.developerToken = developerToken;
	}

	public Long getDeveloperId() {
		return developerId;
	}

	public void setDeveloperId(Long developerId) {
		this.developerId = developerId;
	}

}
