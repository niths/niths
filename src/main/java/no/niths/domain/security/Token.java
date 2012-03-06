package no.niths.domain.security;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * Wrapper class for token
 *
 */
@XmlRootElement
public class Token {
	
	private String token;
	
	public Token(){
		token = "Not provided";
	}
	
	public Token(String token){
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

}
