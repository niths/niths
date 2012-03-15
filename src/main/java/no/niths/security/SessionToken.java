package no.niths.security;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * Wrapper class for token
 *
 */
@XmlRootElement
public class SessionToken {
	
	private String token;
	
	public SessionToken(){
		token = "Not a valid token provided";
	}
	
	public SessionToken(String token){
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

}
